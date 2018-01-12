import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import swatifyFetch from "../swatifyFetch";
import Loader from "./Loader";

class SpotifyCallback extends Component {
  state = {
    loading: true,
    succeeded: false
  };

  componentDidMount() {
    var queryString = this.props.location.search;
    var url = "/api/v1/spotify-auth/callback" + queryString;
    return swatifyFetch(url)
      .then(res => {
        return res.status === 200 ? res.json() : null;
      })
      .then(authenticatedUser => {
        if (authenticatedUser) {
          this.setState({ loading: false, succeeded: true });
        } else {
          this.setState({ loading: false, succeeded: false });
        }
      });
  }

  render() {
    if (this.state.loading) {
      return <Loader loading={this.state.loading} />;
    } else if (this.state.succeeded) {
      window.location.reload();
      return <Redirect to="/feed" />;
    } else {
      return (
        <div>
          <h1>Unable to connect to Spotify</h1>
          <p>Try logging in again.</p>
        </div>
      );
    }
  }
}

export default SpotifyCallback;
