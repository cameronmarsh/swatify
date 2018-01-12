import React, { Component } from "react";
import TrackRating from "./TrackRating";

export default class Rating extends Component {
  constructor(props) {
    super(props);

    this.handleStarClick = this.handleStarClick.bind(this);
  }

  handleStarClick(newStars) {
    this.setState({ stars: newStars });
  }

  render() {
    return (
      <TrackRating
        onClickStars={this.handleStarClick}
        curr_stars={5}
        trackId={this.props.trackId}
      />
    );
  }
}
