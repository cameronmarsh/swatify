import React, { Component } from "react";
import AlbumArtist from "./AlbumArtist";
import Loader from "./Loader";
import { Link } from "react-router-dom";
import { Table } from "react-bootstrap";
import Rating from "./Rating";
import ReviewModal from "./ReviewModal";
import "./AlbumPage.css";
import swatifyFetch from "../swatifyFetch";
import { Redirect } from "react-router";

class Album extends Component {
  state = { loading: true, albumName: "" };

  //need timeout for fetch so we wait for all content to be received

  componentDidMount() {
    swatifyFetch("/api/v1/albums/" + this.props.match.params.id)
      .then(res => {
        return res.status === 200 ? res.json() : null;
      })
      .then(album => {
        if (album) {
          this.setState({
            albumName: album.name,
            artistName: album.artists[0].name,
            image: album.images[0].url,
            artistId: album.artists[0].id,
            tracks: album.tracks.items,
            uri: album.uri,
            albumId: album.id
          });
        }
        this.setState({ loading: false });
      });
  }

  renderAlbumArtist() {
    return (
      <Link to={"/artists/" + this.state.artistId}>
        <AlbumArtist artistId={this.state.artistId} />
      </Link>
    );
  }

  renderTracksList() {
    var tracks = this.state.tracks;
    if (tracks) {
      return (
        <tbody>
          {tracks.map(function(track, index) {
            var numberToUse = (index + 1).toString();
            var trackDuration = (track.duration / 1000 / 60).toFixed(2);
            var givenTrackId = track.id;
            return (
              <tr key={index}>
                <td class="col-md-1"> {numberToUse} </td>
                <td class="col-md-8"> {track.name} </td>
                <td class="col-md-1"> {trackDuration} </td>
                <td class="col-md-4">
                  {" "}
                  <Rating trackId={givenTrackId} />{" "}
                </td>
              </tr>
            );
          })}
        </tbody>
      );
    } else {
      return <tbody />;
    }
  }

  render() {
    if (this.state.loading) {
      return <Loader loading={this.state.loading} />;
    } else if (this.state.albumName) {
      return (
        <div id="AlbumPage" className="Overlay">
          <div id="AlbumInfo" className="AlbumInfoAndLinkedAccounts">
            <div id="ReviewModalForAlbum" className="ReviewModalForAlbum">
              <ReviewModal />
              <iframe
                title="widget"
                src={"https://open.spotify.com/embed?uri=" + this.state.uri}
                frameborder="0"
                allowtransparency="true"
                id="albumWidgets"
              />
            </div>
            <img src={this.state.image} alt="" height="200" width="200" />
            <h3> {this.state.albumName} </h3>
            {this.renderAlbumArtist()}
            <h5> {this.state.artistName} </h5>
            <div id="tracksList" className="TrackInfo">
              <Table striped={true} hover={true}>
                <h1>TRACKLIST</h1>
                <thead> </thead>
                {this.renderTracksList()}
              </Table>
            </div>
            <h3>Discussions</h3>
          </div>
        </div>
      );
    }
    else {
      return <Redirect to="/login" />;
    }
  }
}

export default Album;
