package edu.swarthmore.cs.cs71.swatify.controllers;

import com.wrapper.spotify.models.Album;
import edu.swarthmore.cs.cs71.swatify.test.ControllerTestBase;
import edu.swarthmore.cs.cs71.swatify.test.TestUtil;
import edu.swarthmore.cs.cs71.swatify.util.GsonUtil;
import org.junit.Test;

import static edu.swarthmore.cs.cs71.swatify.test.TestUtil.request;
import static org.junit.Assert.assertEquals;

public class AlbumsControllerTest extends ControllerTestBase {
    @Test
    public void getAnAlbum() {
        String theLifeOfPabloId = "7gsWAHLeT0w7es6FofOXk1";
        TestUtil.TestResponse res = request("GET", "/api/v1/albums/" + theLifeOfPabloId);
        assertEquals(200, res.getStatus());

        Album theLifeOfPablo = GsonUtil.fromJson(Album.class, res.json().toString());
        assertEquals(theLifeOfPabloId, theLifeOfPablo.getId());
        assertEquals("The Life Of Pablo", theLifeOfPablo.getName());
    }
}
