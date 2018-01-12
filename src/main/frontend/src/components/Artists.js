import React, { Component } from "react";
import { Switch, Route } from "react-router-dom";
import ArtistPage from "./ArtistPage";

export default class Artists extends Component {
  render() {
    return (
      <Switch>
        <Route exact path="/artists/:id" component={ArtistPage} />
      </Switch>
    );
  }
}
