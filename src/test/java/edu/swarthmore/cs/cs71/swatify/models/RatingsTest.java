package edu.swarthmore.cs.cs71.swatify.models;

import org.junit.Assert;
import org.junit.Test;

public class RatingsTest {
    @Test
    public void createRating() throws Exception {
        User user = new User("username", "accessToken", "refreshToken");
        Rating newRating = new Rating(user, 5, "7");
        Assert.assertEquals(5, newRating.getStars());
        Assert.assertEquals("7", newRating.getSpotifyId());
        Assert.assertEquals(0, newRating.getId());
    }

    @Test
    public void changeStars() throws Exception {
        User user = new User("username", "accessToken", "refreshToken");
        Rating newRating = new Rating(user, 3, "1");
        Assert.assertEquals(3, newRating.getStars());
        newRating.setStars(5);
        Assert.assertEquals(5, newRating.getStars());
    }
}
