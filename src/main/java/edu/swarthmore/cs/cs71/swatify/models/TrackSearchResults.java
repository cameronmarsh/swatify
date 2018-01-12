package edu.swarthmore.cs.cs71.swatify.models;

import com.wrapper.spotify.models.SimpleArtist;

import java.util.List;

public class TrackSearchResults extends SearchResults {
    private List<TrackSearchResult> results;

    public TrackSearchResults(List<TrackSearchResult> results) {
        this.results = results;
    }


    @Override
    public int numberOfResults() {
        return this.results.size();
    }

    @Override
    public void printResults() {
        System.out.println("TRACKS");
        System.out.println("----------------------");

        if (this.results.isEmpty()) {
            System.out.println("No results found");
        } else {
            for (TrackSearchResult track : results) {
                System.out.printf("Name: %s\n", track.getName());
                System.out.printf("Arists: ");
                for (SimpleArtist trackArtist : track.getArtists()) {
                    System.out.printf("%s. ", trackArtist.getName());
                }
                System.out.println();
                System.out.printf("Duration: %d:%02d\n", track.getDuration() / 60000, (track.getDuration() / 1000) % 60);
                System.out.printf("Album: %s\n", track.getAlbum().getName());
                System.out.println();
            }
        }

    }

    public List<TrackSearchResult> getResults() {
        return results;
    }
}
