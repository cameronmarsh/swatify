package edu.swarthmore.cs.cs71.swatify.models;

import com.wrapper.spotify.models.SimpleAlbum;

import java.util.ArrayList;
import java.util.List;

public class AlbumSearchResults extends SearchResults {
    private List<SimpleAlbum> results;

    public AlbumSearchResults(List<SimpleAlbum> results) {
        this.results = results;
    }

    @Override
    public int numberOfResults() {
        return this.results.size();
    }

    @Override
    public void printResults() {
        System.out.println("ALBUMS");
        System.out.println("----------------------");

        if (this.results.isEmpty()) {
            System.out.println("No results found");
        } else {
            for (SimpleAlbum album : results) {
                System.out.printf("Name: %s\nSpotify ID: %s\n\n", album.getName(), album.getId());
            }
        }

    }

    @Override
    public List<TrackSearchResult> getResults() {
//        return results;
        return new ArrayList<TrackSearchResult>();
    }
}
