import React, { Component } from "react";
import "./ArtistPage.css";
import swatifyFetch from "../swatifyFetch";
import { Redirect } from "react-router-dom";
import Loader from "./Loader";

class Logout extends Component {
  state = {
    loggedOut: false
  };

  componentDidMount() {
    swatifyFetch("/api/v1/logout").then(() =>
      this.setState({ loggedOut: true })
    );
  }

  render() {
    if (this.state.loggedOut) {
      window.location.reload();
      return <Redirect to="/login" />;
    } else {
      return <Loader loading={!this.state.loggedOut} />;
    }
  }
}

export default Logout;
