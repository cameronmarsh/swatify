import React, { Component } from "react";
import { Switch, Route } from "react-router";
import { Redirect } from "react-router-dom";
import { Grid } from "react-bootstrap";
import DocumentTitle from "react-document-title";
import Login from "./Login";
import Logout from "./Logout";
import SpotifyCallback from "./SpotifyCallback";
import Main from "./Main";
import Navbar from "./Navbar";
import "./App.css";

export default class App extends Component {
  state = {
    userId: 1
  };

  render() {
    return (
      <DocumentTitle title="Swatify">
        <div className="App">
          <Navbar />
          <Grid>
            <Switch>
              <Route exact path="/login" component={Login} />
              <Route exact path="/logout" component={Logout} />
              <Route path="/callback" component={SpotifyCallback} />
              <Route
                path="/"
                render={() =>
                  this.state.userId ? (
                    <Main userId={this.state.userId} />
                  ) : (
                    <Redirect to="/login" />
                  )
                }
              />
            </Switch>
          </Grid>
        </div>
      </DocumentTitle>
    );
  }
}
