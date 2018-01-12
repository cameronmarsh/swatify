package edu.swarthmore.cs.cs71.swatify.controllers;

import edu.swarthmore.cs.cs71.swatify.models.Rating;
import edu.swarthmore.cs.cs71.swatify.models.User;
import edu.swarthmore.cs.cs71.swatify.test.ControllerTestBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RatingsControllerTest extends ControllerTestBase {
    public static final User user = new User("username", "accessToken", "refreshToken");

    @Test
    public void createNewRating() {
        Rating rating = new Rating(user, 5, "1");
    }

    @Test
    public void getRating() {
        Rating rating = new Rating(user, 4, "3");

        Rating retrieveRating = new Rating();
        assertEquals(4, retrieveRating.getStars());
    }

    @Test
    public void updateRating() {
    }

    @Test
    public void deleteRating() {
        Rating rating = new Rating(user, 4, "4");
    }
}
