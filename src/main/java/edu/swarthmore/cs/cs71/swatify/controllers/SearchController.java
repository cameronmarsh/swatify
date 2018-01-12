package edu.swarthmore.cs.cs71.swatify.controllers;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.AlbumSearchRequest;
import com.wrapper.spotify.methods.ArtistSearchRequest;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleAlbum;
import com.wrapper.spotify.models.Track;
import edu.swarthmore.cs.cs71.swatify.controllers.spotifyRoutes.BaseSpotifyRoute;
import edu.swarthmore.cs.cs71.swatify.models.*;
import edu.swarthmore.cs.cs71.swatify.util.GsonUtil;
import edu.swarthmore.cs.cs71.swatify.util.SpotifyUtil;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class SearchController {

    public SearchController() {
        path("/search", () -> {
            path("/:id", () -> {
                get("", new BaseSpotifyRoute() {

                    //search for tracks
                    public TrackSearchResults searchTracks(Api api, String query) {
                        final TrackSearchRequest request = api.searchTracks(query).build();
                        List<TrackSearchResult> searchResults = new ArrayList<>();

                        try {
                            final Page<Track> tracks = request.get();
                            System.out.printf("Got %d results\n", tracks.getTotal());

                            List<Track> resultTracks = tracks.getItems();
                            for (Track track : resultTracks) {
                                searchResults.add(new TrackSearchResult(track));
                            }

                        } catch (Exception e) {
                            System.out.println("Something went wrong! Got error " + e);
                        }

                        return new TrackSearchResults(searchResults);
                    }


                    //search for albums
                    public AlbumSearchResults searchAlbums(Api api, String query) {
                        final AlbumSearchRequest request = api.searchAlbums(query).build();

                        try {
                            final Page<SimpleAlbum> albums = request.get();
                            System.out.printf("Got %d results\n", albums.getTotal());

                            return new AlbumSearchResults(albums.getItems());

                        } catch (Exception e) {
                            System.out.println("Something went wrong! Got error " + e);
                        }

                        return new AlbumSearchResults(new ArrayList<>());
                    }


                    //search for artists
                    public ArtistSearchResults searchArtists(Api api, String query) {
                        final ArtistSearchRequest request = api.searchArtists(query).build();

                        try {
                            final Page<Artist> artists = request.get();
                            System.out.printf("Got %d results\n", artists.getTotal());

                            return new ArtistSearchResults(artists.getItems());

                        } catch (Exception e) {
                            System.out.println("Something went wrong! Got error " + e);
                        }

                        return new ArtistSearchResults(new ArrayList<>());
                    }

                    //search for users in our database

                    //search everything
                    public List<SearchResults> search(Api api, String query) {
                        System.out.println(query);
                        List<SearchResults> results = new ArrayList<>();
                        results.add(searchAlbums(api, query));
                        results.add(searchArtists(api, query));
                        results.add(searchTracks(api, query));

                        return results;


                    }

                    @Override
                    protected Object doAction(Api api, Request request, Response response) throws Exception {
                        return search(api, request.params("id"));
                    }
                });

//                get("/tracks", new BaseSpotifyRoute() {
//                    @Override
//                    protected Object doAction(Api api, Request request, Response response) throws Exception {
//                        return null;
//                    }
//                });
//
//                get("/artists", new BaseSpotifyRoute() {
//                    @Override
//                    protected Object doAction(Api api, Request request, Response response) throws Exception {
//                        return null;
//                    }
                });
            });
}



}
