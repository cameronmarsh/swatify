import React, { Component } from "react";
import { Link } from "react-router-dom";

export default class Discover extends Component {
  render() {
    return (
      <div>
        <h1>Discover</h1>
        <Link to="/artists/17Zu03OgBVxgLxWmRUyNOJ">Check out this artist</Link>
        <Link to="/albums/7gsWAHLeT0w7es6FofOXk1"> Check out this Album</Link>
      </div>
    );
  }
}
