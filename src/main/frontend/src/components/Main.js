import React, { Component } from "react";
import { Switch, Route } from "react-router-dom";
import Feed from "./Feed";
import Discover from "./Discover";
import Discuss from "./Discuss";
import Connect from "./Connect";
import Users from "./Users";
import Artists from "./Artists";
import Albums from "./Albums";
import Search from "./Search";
import Home from "./Home";

export default class Main extends Component {
  render() {
    return (
      <Switch>
        <Route exact path="/" component={Home} />
        <Route path="/feed" component={Feed} />
        <Route path="/discover" component={Discover} />
        <Route path="/discuss" component={Discuss} />
        <Route path="/connect" component={Connect} />
        <Route path="/users" component={Users} />
        <Route path="/artists" component={Artists} />
        <Route path="/albums" component={Albums} />
        <Route path="/search" component={Search} />
      </Switch>
    );
  }
}
