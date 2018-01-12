import React, { Component } from "react";
import { Switch, Route } from "react-router-dom";
import UserProfile from "./UserProfile";

export default class Main extends Component {
  render() {
    return (
      <Switch>
        <Route exact path="/users/:id" component={UserProfile} />
      </Switch>
    );
  }
}
