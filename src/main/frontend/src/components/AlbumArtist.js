import React, { Component } from "react";
import swatifyFetch from "../swatifyFetch";

export default class AlbumArtist extends Component {
  state = { loading: true, artistImage: "" };

  componentDidMount() {
    swatifyFetch("/api/v1/artists/" + this.props.artistId)
      .then(res => {
        return res.status === 200 ? res.json() : null;
      })
      .then(artist =>
        this.setState({
          artistImage: artist ? artist.images[0].url : "",
          loading: false
        })
      );
  }

  render() {
    if (this.state.loading) {
      return <div />;
    } else {
      return (
        <div id="albumArtistInfo" className="albumArtistInfo">
          <img src={this.state.artistImage} alt="" height="100" width="100" />
        </div>
      );
    }
  }
}
