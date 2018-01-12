import React, { Component } from "react";
import Loader from "./Loader";
import NotFound from "./NotFound";
import swatifyFetch from "../swatifyFetch";

export default class UserProfile extends Component {
  state = { loading: true, user: null };

  componentDidMount() {
    swatifyFetch("/api/v1/users/" + this.props.match.params.id)
      .then(res => res.json())
      .then(user => this.setState({ user: user, loading: false }));
  }

  render() {
    if (this.state.loading) {
      return <Loader loading={this.state.loading} />;
    } else if (this.state.user) {
      return <h3>{this.state.user.username}</h3>;
    } else {
      return <NotFound />;
    }
  }
}
