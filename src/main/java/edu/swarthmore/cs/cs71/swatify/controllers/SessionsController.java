package edu.swarthmore.cs.cs71.swatify.controllers;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.AuthorizationCodeCredentials;
import edu.swarthmore.cs.cs71.swatify.controllers.hibernateRoutes.BaseHibernateRoute;
import edu.swarthmore.cs.cs71.swatify.errors.ForbiddenError;
import edu.swarthmore.cs.cs71.swatify.errors.InternalServerError;
import edu.swarthmore.cs.cs71.swatify.errors.UnauthorizedError;
import edu.swarthmore.cs.cs71.swatify.models.User;
import edu.swarthmore.cs.cs71.swatify.util.GsonUtil;
import edu.swarthmore.cs.cs71.swatify.util.SpotifyUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.path;

public class SessionsController {
    public SessionsController() {
        get("/logout", (request, response) -> {
            User user = request.session().attribute("user");
            request.session().removeAttribute("user");
            return GsonUtil.toJson(user);
        });

        path("/spotify-auth", () -> {
            get("/authorize-url", ((request, response) -> {
                if (request.session().attribute("user") != null) {
                    return GsonUtil.toJson(new ForbiddenError("Already logged in"));
                }
                JSONObject jsonResponseBody = new JSONObject();
                String authorizeUrl = SpotifyUtil.getAuthorizeUrl();
                jsonResponseBody.put("authorizeUrl", authorizeUrl);
                return jsonResponseBody.toString();
            }));

            get("/callback", new BaseHibernateRoute() {
                @Override
                protected Object doAction(Session session, Request request, Response response) {
                    String code = request.queryParams("code");

                    final AuthorizationCodeCredentials credentials = new AuthorizationCodeCredentials();
                    Api api = SpotifyUtil.getApi(null);
                    final SettableFuture<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = api.authorizationCodeGrant(code).build().getAsync();

                    final String[] errorMessageArray = new String[1];
                    errorMessageArray[0] = null;

                    // Add callbacks to handle success and failure.
                    Futures.addCallback(authorizationCodeCredentialsFuture, new FutureCallback<AuthorizationCodeCredentials>() {
                        @Override
                        public void onSuccess(AuthorizationCodeCredentials authorizationCodeCredentials) {
                            api.setAccessToken(authorizationCodeCredentials.getAccessToken());
                            api.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

                            credentials.setAccessToken(authorizationCodeCredentials.getAccessToken());
                            credentials.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            errorMessageArray[0] = throwable.getMessage();
                        }
                    });

                    if (errorMessageArray[0] != null) {
                        return new InternalServerError(errorMessageArray[0]);
                    }

                    com.wrapper.spotify.models.User spotifyUser;
                    try {
                        spotifyUser = api.getMe().build().get();
                    } catch (Exception e) {
                        return new UnauthorizedError(e.getMessage());
                    }

                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<User> query = builder.createQuery(User.class);
                    Root<User> root = query.from(User.class);
                    query.select(root).where(builder.equal(root.get("spotifyId"),
                            spotifyUser.getId()));
                    Query<User> q = session.createQuery(query);
                    List<User> resultList = q.getResultList();

                    User user;
                    if (resultList.size() > 0) {
                        user = resultList.get(0);
                    } else {
                        String username = spotifyUser.getDisplayName() == null ? spotifyUser.getId() : spotifyUser.getDisplayName();
                        user = new User(username, credentials.getAccessToken(), credentials.getRefreshToken());
                        session.save(user);
                    }
                    request.session(true).attribute("user", user);

                    return user;
                }
            });
        });
    }
}
