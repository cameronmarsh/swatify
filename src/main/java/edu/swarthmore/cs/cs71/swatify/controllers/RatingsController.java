package edu.swarthmore.cs.cs71.swatify.controllers;

import edu.swarthmore.cs.cs71.swatify.controllers.hibernateRoutes.BaseHibernateRoute;
import edu.swarthmore.cs.cs71.swatify.controllers.hibernateRoutes.GetObjectHibernateRoute;
import edu.swarthmore.cs.cs71.swatify.models.Rating;
import edu.swarthmore.cs.cs71.swatify.models.User;
import org.hibernate.Session;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class RatingsController {
    public RatingsController() {
        path("/ratings", () -> {
            post("", new BaseHibernateRoute() {
                @Override
                protected Object doAction(Session session, Request request, Response response) {
                    JSONObject requestBody = new JSONObject(request.body());
                    User user = session.get(User.class, requestBody.getInt("userId"));
                    Rating rating = new Rating(user, requestBody.getInt("stars"), requestBody.getString("spotifyId"));
                    session.save(rating);
                    return rating;
                }
            });

            path("/:id", () -> {
                get("", new GetObjectHibernateRoute() {
                    @Override
                    protected Class<?> getObjectClass() {
                        return Rating.class;
                    }
                });

                patch("", new BaseHibernateRoute() {
                    @Override
                    protected Object doAction(Session session, Request request, Response response) {
                        JSONObject requestBody = new JSONObject(request.body());
                        User user = session.get(User.class, requestBody.getInt("userId"));
                        Rating rating = session.get(Rating.class, Integer.parseInt(request.params("id")));
                        rating.setStars((new JSONObject(request.body())).getInt("stars"));
                        session.update(rating);
                        return rating;
                    }
                });
            });
        });
    }
}
