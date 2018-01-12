import React, { Component } from "react";
import "./StarHoverable.css";

/*
Borrowing from code found here:
    https://codepen.io/benjaminreid/pen/vNVwPW
*/

export default class StarHoverable extends Component {
  constructor(props) {
    super(props);
    this.state = { rating: props.curr_stars, temp_rating: 0 };
  }

  propTypes = {
    disabled: React.PropTypes.bool
  }

  getInitialState() {
    return {
      rating: this.props.rating || null,
      temp_rating: null
    };
  }

  rate(rating) {
    this.setState({
      rating: rating,
      temp_rating: rating
    });
    this.props.onClickStars(rating + 1);
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
          onClick={this.rate.bind(this, i)}
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
