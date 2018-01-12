import React, { Component } from "react";
import { Button } from "react-bootstrap";
import { Redirect } from "react-router-dom";
import { parse, stringify } from "query-string";
import swatifyFetch from "../swatifyFetch";
import Loader from "./Loader";

export default class Login extends Component {
  state = {
    authorizeUrl: "",
    loading: true
  };

  componentDidMount() {
    swatifyFetch("/api/v1/spotify-auth/authorize-url")
      .then(response => response.json())
      .then(responseData => {
        // Set callback to frontend URL instead of original backend URL.
        let authorizeUrl;
        if ("authorizeUrl" in responseData) {
          let originalAuthorizeUrl = responseData.authorizeUrl;
          let queryParams = parse(originalAuthorizeUrl.split("?")[1]);

          queryParams.redirect_uri = window.location.origin + "/callback";
          authorizeUrl =
            originalAuthorizeUrl.split("?")[0] + "?" + stringify(queryParams);
        } else {
          authorizeUrl = "";
        }

        this.setState({
          authorizeUrl: authorizeUrl,
          loading: false
        });
      });
  }

  render() {
    if (this.state.loading) {
      return <Loader loading={this.state.loading} />;
    } else if (this.state.authorizeUrl) {
      return (
        <div>
          <h1>Log in to Swatify!</h1>
          <Button href={this.state.authorizeUrl}>Connect with Spotify</Button>
        </div>
      );
    } else {
      return <Redirect to="/feed" />;
    }
  }
}
