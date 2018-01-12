package edu.swarthmore.cs.cs71.swatify.models;

import com.wrapper.spotify.Api;
import edu.swarthmore.cs.cs71.swatify.util.SpotifyUtil;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int id;

    @Column(unique = true)
    @NotBlank
    private String username;

    @NotBlank
    private String spotifyAccessToken;

    @NotBlank
    private String spotifyRefreshToken;

    private String spotifyId;

    public User() {
    }

    public User(String username, String spotifyAccessToken, String spotifyRefreshToken) {
        this.username = username;
        this.spotifyAccessToken = spotifyAccessToken;
        this.spotifyRefreshToken = spotifyRefreshToken;
        Api api = SpotifyUtil.getApi(null);
        api.setAccessToken(spotifyAccessToken);
        api.setRefreshToken(spotifyRefreshToken);
        try {
            this.spotifyId = api.getMe().build().get().getId();
        } catch (Exception e) {
            this.spotifyId = null;
        }
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getSpotifyAccessToken() {
        return spotifyAccessToken;
    }

    public String getSpotifyRefreshToken() {
        return spotifyRefreshToken;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyAccessToken(String spotifyAccessToken) {
        this.spotifyAccessToken = spotifyAccessToken;
    }

    public void setSpotifyRefreshToken(String spotifyRefreshToken) {
        this.spotifyRefreshToken = spotifyRefreshToken;
    }
}
