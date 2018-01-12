package edu.swarthmore.cs.cs71.swatify.models;

import com.wrapper.spotify.models.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistSearchResults extends SearchResults {
    List<Artist> results;

    public ArtistSearchResults(List<Artist> results) {
        this.results = results;
    }

    @Override
    public int numberOfResults() {
        return results.size();
    }

    @Override
    public void printResults() {
        System.out.println("ARTISTS");
        System.out.println("----------------------");

        if (this.results.isEmpty()) {
            System.out.println("No results found");
        } else {
            for (Artist artist : results) {
                System.out.printf("Name: %s\nSpotify ID:%s\nFollowers: %d\n\n",
                        artist.getName(), artist.getId(), artist.getFollowers().getTotal());
            }
        }

    }


    @Override
    public List<TrackSearchResult> getResults() {
//        return results;
        return new ArrayList<TrackSearchResult>();
    }
}
