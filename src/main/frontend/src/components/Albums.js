import React, { Component } from "react";
import { Switch, Route } from "react-router-dom";
import AlbumPage from "./AlbumPage";

export default class Albums extends Component {
  render() {
    return (
      <Switch>
        <Route exact path="/albums/:id" component={AlbumPage} />
      </Switch>
    );
  }
}
