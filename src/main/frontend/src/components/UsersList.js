import React, { Component } from "react";
import { Link } from "react-router-dom";
import Loader from "./Loader";

export default class UserProfile extends Component {
  state = { loading: true, users: null };

  componentDidMount() {}

  render() {
    if (this.state.loading) {
      return <Loader loading={this.state.loading} />;
    } else {
      return (
        <div>
          {this.state.users.map((user, index) => (
            <Link to={"/users/" + user.id} key={index}>
              {user.username}
            </Link>
          ))}
        </div>
      );
    }
  }
}
