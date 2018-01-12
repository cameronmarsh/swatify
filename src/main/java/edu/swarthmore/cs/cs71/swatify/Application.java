package edu.swarthmore.cs.cs71.swatify;

import edu.swarthmore.cs.cs71.swatify.controllers.*;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/build");

        establishRoutes();
    }

    public static void establishRoutes() {
        path("/api/v1", () -> {
            new AlbumsController();
            new ArtistsController();
            new DiscussionsController();
            new RatingsController();
            new SearchController();
            new SessionsController();
            new UsersController();

            after((req, res) -> res.type("application/json"));

            exception(IllegalArgumentException.class, (e, req, res) -> {
                res.status(400);
            });
        });
    }
}

