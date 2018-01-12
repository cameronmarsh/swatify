import React, { Component } from "react";
import Loader from "./Loader";
import "./ArtistPage.css";
import swatifyFetch from "../swatifyFetch";
import { Redirect } from "react-router-dom";

class ArtistPage extends Component {
  state = {
    loadingCount: 3,
    artist: null,
    albums: [],
    followers: 0
  };

  componentDidMount() {
    swatifyFetch("/api/v1/artists/" + this.props.match.params.id)
      .then(res => {
        return res.status === 200 ? res.json() : null;
      })
      .then(artist =>
        this.setState({
          artist: artist,
          loadingCount: this.state.loadingCount - 1
        })
      );

    swatifyFetch("/api/v1/artists/" + this.props.match.params.id + "/albums")
      .then(response => response.json())
      .then(albums =>
        this.setState({
          albums: albums,
          loadingCount: this.state.loadingCount - 1
        })
      );

    swatifyFetch("/api/v1/artists/" + this.props.match.params.id + "/followers")
      .then(res => res.json())
      .then(followers =>
        this.setState({
          followers: followers.count,
          loadingCount: this.state.loadingCount - 1
        })
      );
  }

  render() {
    if (this.state.loadingCount > 0) {
      return <Loader loading={true} />;
    } else if (this.state.artist) {
      return (
        <div className="ArtistPage">
          <div className="Sidebar">
            <img
              src={this.state.artist.images[1].url}
              height={200}
              width={
                200 *
                this.state.artist.images[1].width /
                this.state.artist.images[1].height
              }
              alt={this.state.artist.name}
            />
            <div className="Related">
              <hr />

              <iframe
                title="widget"
                src={
                  "https://embed.spotify.com/follow/1/?uri=spotify:artist:" +
                  this.state.artist.id +
                  "&size=basic&theme=light&show-count=0"
                }
                width="200"
                height="56"
                scrolling="no"
                style={{ overflow: "hidden" }}
                frameborder="0"
                allowtransparency="true"
              />
              <p>
                <strong>Followers: </strong>
                {this.state.artist.followers.total}
              </p>
              <p>
                <strong>Popularity: </strong>
                {this.state.artist.popularity}
              </p>
              <p className="Genre">
                <strong>Genre: </strong>
                {this.state.artist.genres[0]}
              </p>
              <div className="SpotifyLink">
                <a href={this.state.artist.uri}>View this artist on Spotify</a>
              </div>
              <hr />
            </div>
          </div>
          <div className="ArtistContent">
            <h1 id="artistName">{this.state.artist.name}</h1>
            <h3>Albums</h3>
            <hr />
            <ul>
              {this.state.albums.map(function(album) {
                console.log("album");
                var albumLink = "/albums/" + album.id;
                return (
                  <li>
                    <div className="AlbumListing">
                      <img
                        alt="album"
                        src={album.images[1].url}
                        height={album.images[1].height * 0.5}
                        width={album.images[1].width * 0.5}
                      />
                      <br />
                      <a href={albumLink}>{album.name}</a>
                    </div>
                  </li>
                );
              })}
            </ul>
            <br />
            <h3>Discussions</h3>
            <hr />
          </div>
        </div>
      );
    } else {
      return <Redirect to="/login" />;
    }
  }
}

export default ArtistPage;
