import React, { Component } from "react";
import { Link } from "react-router-dom";
import {
  Navbar as BSNavbar,
  Nav as BSNav,
  NavItem as BSNavItem,
  Form,
  FormControl,
  FormGroup
} from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import "./Navbar.css";
import swatifyFetch from "../swatifyFetch";

export default class Navbar extends Component {
  state = {
    me: null
  };

  handleChange(e) {
    this.setState({ searchInput: e.target.value });
  }

  handleSubmit(e) {
    e.preventDefault();
    e.stopPropagation();
    console.log("submit");
    window.location = "/search/" + this.state.searchInput;
  }

  componentDidMount() {
    swatifyFetch("/api/v1/users/me")
      .then(response => {
        if (response.status === 200) {
          return response.json();
        } else {
          return null;
        }
      })
      .then(user => {
        if (!!user) {
          this.setState({ me: user });
        }
      });
  }

  render() {
    let authenticatedArea = !!this.state.me ? (
      <BSNav>
        <LinkContainer to={"/users/" + this.state.me.id}>
          <BSNavItem className="Navbar-link">Me</BSNavItem>
        </LinkContainer>
        <LinkContainer to="/logout">
          <BSNavItem className="Navbar-link">Log out</BSNavItem>
        </LinkContainer>
      </BSNav>
    ) : (
      <LinkContainer to="/login">
        <BSNavItem className="Navbar-link">Log in</BSNavItem>
      </LinkContainer>
    );

    return (
      <BSNavbar inverse>
        <BSNavbar.Header>
          <BSNavbar.Brand>
            <Link to="/" className="Navbar-link">
              <img alt="Swatify" src="/logo.png" width="70" />
            </Link>
          </BSNavbar.Brand>
          <BSNavbar.Toggle />
        </BSNavbar.Header>
        <BSNavbar.Collapse>
          <BSNav>
            <LinkContainer to="/feed">
              <BSNavItem className="Navbar-link">Feed</BSNavItem>
            </LinkContainer>
            <LinkContainer to="/discover">
              <BSNavItem className="Navbar-link">Discover</BSNavItem>
            </LinkContainer>
            <LinkContainer to="/discuss">
              <BSNavItem className="Navbar-link">Discuss</BSNavItem>
            </LinkContainer>
            <LinkContainer to="/connect">
              <BSNavItem className="Navbar-link">Connect</BSNavItem>
            </LinkContainer>
          </BSNav>
          <BSNav pullRight>
            <div id="search">
              <Form onSubmit={this.handleSubmit.bind(this)}>
                <FormGroup>
                  <FormControl
                    type="text"
                    placeholder="Search"
                    onChange={this.handleChange.bind(this)}
                  />
                </FormGroup>
              </Form>
            </div>
            {authenticatedArea}
          </BSNav>
        </BSNavbar.Collapse>
      </BSNavbar>
    );
  }
}
