import React, { Component } from "react";
import { Modal, Button, Form, Glyphicon } from "react-bootstrap";
import swatifyFetch from "../swatifyFetch";

export default class DiscussionModal extends Component {
  constructor(props) {
    super(props);

    this.state = { showModal: false, title: "", userName: null };
    this.open = this.open.bind(this);
    this.close = this.close.bind(this);

    swatifyFetch("api/v1/users/me").then(res => res.json())
         .then(user =>
           this.setState({
             username: user.username,
             loading: false
           })
         );
  }

  handleChange(e) {
    console.log(this.state.username);
    this.setState({ title: e.target.value });
  }

  handleSubmit(e) {
    //TODO find user id
    var saveUsername = this.state.username;
    console.log(saveUsername);
    if (saveUsername == null) {
        // If user is not signed in, set name to anonymous
        saveUsername = "Anonymous"
    }
    swatifyFetch("/api/v1/discussions", {
      method: "post",
      headers: { "Content-Type": "Discussion" },
      body: JSON.stringify({
        userId: 7,
        title: this.state.title,
        userName: saveUsername
      })
    });
    this.close();
  }

  close() {
    this.setState({ showModal: false });
    this.setState({ title: "" });
  }

  open() {
    this.setState({ showModal: true });
  }

  render() {
    return (
      <div>
        <Button bsStyle="primary" bsSize="large" onClick={this.open}>
          <div>
            <Glyphicon className="pull-left" glyph="plus" />
            Add a Discussion
          </div>
        </Button>

        <Modal show={this.state.showModal} onHide={this.close}>
          <Form>
            <Modal.Header>
              <Modal.Title>Create New Discussion</Modal.Title>
            </Modal.Header>

            <Modal.Body>
              <input
                type="text"
                placeholder="Title"
                className="form-control"
                onChange={this.handleChange.bind(this)}
              />
            </Modal.Body>

            <Modal.Footer>
              <Button onClick={this.close}>Close</Button>
              <Button type="submit"
                onClick={this.handleSubmit.bind(this)}
                bsStyle="primary"
                disabled={!this.state.title}
              >
                Save changes
              </Button>
            </Modal.Footer>
          </Form>
        </Modal>
      </div>
    );
  }
}
