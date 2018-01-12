package edu.swarthmore.cs.cs71.swatify.controllers;

import com.wrapper.spotify.models.Artist;
import edu.swarthmore.cs.cs71.swatify.test.ControllerTestBase;
import edu.swarthmore.cs.cs71.swatify.test.TestUtil;
import edu.swarthmore.cs.cs71.swatify.util.GsonUtil;
import org.junit.Test;

import static edu.swarthmore.cs.cs71.swatify.test.TestUtil.request;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArtistsControllerTest extends ControllerTestBase {
    @Test
    public void getAnArtist() {
        String kanyeId = "5K4W6rqBFWDnAN6FQUkS6x";
        TestUtil.TestResponse res = request("GET", "/api/v1/artists/" + kanyeId);
        assertEquals(200, res.getStatus());

        Artist kanye = GsonUtil.fromJson(Artist.class, res.json().toString());
        assertEquals(kanyeId, kanye.getId());
        assertEquals(kanye.getName(), "Kanye West");
    }

    @Test
    public void getArtistsFollowers() {
        String kanyeId = "5K4W6rqBFWDnAN6FQUkS6x";
        TestUtil.TestResponse res = request("GET", "/api/v1/artists/" + kanyeId + "/followers");
        assertEquals(200, res.getStatus());

        ArtistsController.FollowerCount followerCount = GsonUtil.fromJson(ArtistsController.FollowerCount.class, res.json().toString());
        assertTrue(followerCount.getCount() > 100); // Probably safe to assume.
    }
}
