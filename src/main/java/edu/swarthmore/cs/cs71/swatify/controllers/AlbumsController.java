package edu.swarthmore.cs.cs71.swatify.controllers;

import com.wrapper.spotify.Api;
import edu.swarthmore.cs.cs71.swatify.controllers.hibernateRoutes.BaseHibernateRoute;
import edu.swarthmore.cs.cs71.swatify.controllers.spotifyRoutes.BaseSpotifyRoute;
import edu.swarthmore.cs.cs71.swatify.models.Review;
import edu.swarthmore.cs.cs71.swatify.models.User;
import org.hibernate.Session;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class AlbumsController {
    public AlbumsController() {
        path("/albums", () -> {
            path("/:id", () -> {
                get("", new BaseSpotifyRoute() {
                    @Override
                    protected Object doAction(Api api, Request request, Response response) throws Exception {
                        String albumId = request.params("id");
                        return api.getAlbum(albumId).build().get();
                    }
                });

                path("/reviews", () -> {
                    post("", new BaseHibernateRoute() {
                        @Override
                        protected Object doAction(Session session, Request request, Response response) {
                            JSONObject jsonBody = new JSONObject(request.body());
                            User user = session.get(User.class, jsonBody.getString("userId"));
                            return new Review(jsonBody.getString("content"),
                                    user,
                                    jsonBody.getString("albumId"),
                                    Integer.parseInt(jsonBody.getString("stars")));
                        }
                    });
                });
            });
        });
    }
}
