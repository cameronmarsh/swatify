package edu.swarthmore.cs.cs71.swatify.controllers;

import edu.swarthmore.cs.cs71.swatify.models.AlbumSearchResults;
import edu.swarthmore.cs.cs71.swatify.models.ArtistSearchResults;
import edu.swarthmore.cs.cs71.swatify.models.SearchResults;
import edu.swarthmore.cs.cs71.swatify.models.TrackSearchResults;
import edu.swarthmore.cs.cs71.swatify.test.ControllerTestBase;
import edu.swarthmore.cs.cs71.swatify.test.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static edu.swarthmore.cs.cs71.swatify.test.TestUtil.request;

public class SearchControllerTest extends ControllerTestBase {

    @Test
    public void shouldReturnTrackSearchResults() {
//        TrackSearchResults trackResults = SearchController.searchTracks("Before the Beginning");
//        trackResults.printResults();
    }

    @Test
    public void shouldReturnAlbumSearchResults() {
//
//        AlbumSearchResults albumResults = SearchController.searchAlbums("Aquemini");
//        albumResults.printResults();
    }

    @Test
    public void shouldReturnArtistSearchResults() {
//        ArtistSearchResults artistResults = SearchController.searchArtists("Frank Zappa");
//        artistResults.printResults();
    }

    @Test
    public void shouldGetAllResults() {
//        List<SearchResults> searchResults = SearchController.search("Yes");
//        for (SearchResults resultCategory : searchResults) {
//            resultCategory.printResults();
//        }
//
//        Assert.assertTrue(!searchResults.isEmpty());
//        Assert.assertEquals(searchResults.size(), 3);

        TestUtil.TestResponse res = request("GET", "/api/v1/search/hello");
        Assert.assertEquals(200, res.getStatus());
    }
}
