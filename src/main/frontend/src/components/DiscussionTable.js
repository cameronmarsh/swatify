import React, { Component } from "react";
import { Table } from "react-bootstrap";
import Loader from "./Loader";
import "./DiscussionTable.css";
import swatifyFetch from "../swatifyFetch";

export default class DiscussionTable extends Component {
  state = { loading: true };

  componentWillMount() {
    swatifyFetch("/api/v1/discussions")
      .then(res => res.json())
      .then(discussions =>
        this.setState({
          discussions: discussions,
          loading: false
        })
      );
  }

  renderDiscussionsList() {
    var discussions = this.state.discussions;
    return (
      <tbody>
        {discussions.map(function(discussion, index) {
          var title = discussion.title;
          var userName = discussion.userName;
          var modifyDate = discussion.modifyDate;
          return (
            <tr key={index}>
              <td className="col-md-7">{title}</td>
              <td className="col-md-3">{userName}</td>
              <td className="col-md-2">{modifyDate}</td>
            </tr>
          );
        })}
      </tbody>
    );
  }

  render() {
    if (this.state.loading === false) {
      return (
        <div>
          <Table striped={true} hover={true}>
            <thead>
              <tr>
                <th>Discussion Title</th>
              </tr>
            </thead>
            {this.renderDiscussionsList()}
          </Table>
        </div>
      );
    } else {
      return <Loader loading={this.state.loading} />;
    }
  }
}
