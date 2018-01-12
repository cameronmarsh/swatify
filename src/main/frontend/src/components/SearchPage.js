import React, { Component } from "react";
import Loader from "./Loader";
import NotFound from "./NotFound";
import swatifyFetch from "../swatifyFetch";

export default class SearchPage extends Component {
  state = { loading: true, results: [], tracks: [] };

  componentDidMount() {
    swatifyFetch("/api/v1/search/" + this.props.match.params.id)
      .then(
        function(res) {
          // res instanceof Response == true.
          if (res.ok) {
            res.json().then(function(data) {
              console.log(data.entries);
            });
          } else {
            console.log(
              "Looks like the response wasn't perfect, got status",
              res.status
            );
          }
        },
        function(e) {
          console.log("Fetch failed!", e);
        }
      )
      .then(results => this.setState({ results: results, loading: false }));

    //        swatifyFetch('/api/v1/search/' + this.props.match.params.id + '/tracks')
    //            .then(response => response.json())
    //            .then(tracks => this.setState({tracks: tracks, loading: false}));
  }

  render() {
    if (this.state.loading) {
      return <Loader loading={this.state.loading} />;
    } else if (this.state.results) {
      return (
        <div>
          <h1>Search</h1>
          <hr />
          <p>Got {this.state.results.length} results</p>
        </div>
      );
    } else {
      console.log(this.state.results);
      return <NotFound />;
    }
  }
}
