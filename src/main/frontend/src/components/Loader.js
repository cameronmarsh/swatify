import React, { Component } from "react";
import { BounceLoader } from "react-spinners";
import { Row } from "react-flexbox-grid";

export default class Loader extends Component {
  render() {
    return (
      <Row center="xs" middle="xs">
        <BounceLoader
          color={"#B31E3B"}
          size={100}
          loading={this.props.loading}
        />
      </Row>
    );
  }
}
