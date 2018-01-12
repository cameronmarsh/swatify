package edu.swarthmore.cs.cs71.swatify.models;

import java.util.List;

public abstract class SearchResults {
    public abstract int numberOfResults();

    public abstract void printResults();

    public abstract List<TrackSearchResult> getResults();
}
