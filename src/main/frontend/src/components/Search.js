import React, { Component } from "react";
import { Switch, Route } from "react-router-dom";
import SearchPage from "./SearchPage";

export default class Search extends Component {
  render() {
    return (
      <Switch>
        <Route exact path="/search/:id" component={SearchPage} />
      </Switch>
    );
  }
}
