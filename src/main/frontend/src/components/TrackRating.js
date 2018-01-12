import React, { Component } from "react";
import "./StarHoverable.css";
import swatifyFetch from "../swatifyFetch";
import PropTypes from "prop-types";

export default class StarHoverable extends Component {
  constructor(props) {
    super(props);
    this.state = {
      rating: props.curr_stars,
      temp_rating: 0,
      trackId: this.props.trackId
    };
  }

  getInitialState() {
    return {
      rating: this.props.rating || null,
      temp_rating: null
    };
  }

  rateTrack(rating) {
    this.setState({
      rating: rating,
      temp_rating: rating
    });
    var realRating = rating + 1;
    var realId = this.props.trackId;
    this.props.onClickStars(realRating);
    swatifyFetch("/api/v1/ratings", {
      method: "POST",
      body: JSON.stringify({
        userId: 526,
        stars: realRating,
        spotifyId: realId
      })
    })
      .then(function(data) {
        console.log("Request success: ", data);
      })
      .catch(function(error) {
        console.log("Request failure: ", error);
      });
  }

  star_over(rating) {
    this.state.temp_rating = this.state.rating;
    this.state.rating = rating;

    this.setState({
      rating: this.state.rating,
      temp_rating: this.state.temp_rating
    });
  }

  star_out() {
    this.state.rating = this.state.temp_rating;

    this.setState({ rating: this.state.rating });
  }

  render() {
    var stars = [];

    for (var i = 0; i < 5; i++) {
      var klass = "star-rating__star";

      if (this.state.rating != null && this.state.rating >= i) {
        klass += " is-selected";
      }

      stars.push(
        <label
          className={klass}
          onClick={this.rateTrack.bind(this, i)}
          onMouseOver={this.star_over.bind(this, i)}
          onMouseOut={this.star_out.bind(this, i)}
          key={i}
        >
          ★
        </label>
      );
    }

    return <div className="star-rating">{stars}</div>;
  }
}

StarHoverable.PropTypes = {
  disabled: PropTypes.bool
}
