import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Modal, Button, Form, FormGroup, FormControl } from "react-bootstrap";
import swatifyFetch from "../swatifyFetch";
//import "./ReviewModal.css";
//TODO finding musicWorkId

export default class PostModal extends Component {
  constructor(props) {
    super(props);

    this.state = { showModal: false, content: "", stars: 5 };
    this.open = this.open.bind(this);
    this.close = this.close.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(e) {
    this.setState({ content: e.target.value });
  }

  handleSubmit(e) {
    //TODO find user id
    swatifyFetch("/api/v1/discussions/posts/", {
      method: "post",
      headers: { "Content-Type": "Review" },
      body: {
        content: e.target.value,
        discussion: this.state.discussion,
        user_id: 7
      }
    });
    this.close();
  }

  close() {
    this.setState({ showModal: false });
    this.setState({ content: "" });
  }

  open() {
    this.setState({ showModal: true });
  }

  save() {}

  render() {
    return (
      <div>
        <Button bsStyle="primary" bsSize="large" onClick={this.open}>
          Create Post
        </Button>

        <Modal show={this.state.showModal} onHide={this.close}>
          <Form>
            <Modal.Header>
              <Modal.Title>Create New Post</Modal.Title>
            </Modal.Header>

            <Modal.Body>
              <FormGroup>
                <FormControl
                  key="post"
                  componentClass="textarea"
                  rows={6}
                  placeholder="Your post..."
                  className="form-control"
                  bsSize="large"
                  onChange={this.handleChange.bind(this)}
                />
              </FormGroup>
            </Modal.Body>

            <Modal.Footer>
              <Button onClick={this.close}>Close</Button>
              <Button
                onClick={this.handleSubmit.bind(this)}
                bsStyle="primary"
                disabled={!this.state.content}
              >
                Create
              </Button>
            </Modal.Footer>
          </Form>
        </Modal>
      </div>
    );
  }
}
