import React, { Component } from "react";

class Home extends Component {
  render() {
    return (
      <div>
        <h1>
          <strong>Welcome to Swatify!</strong>
        </h1>
        <h2>What is Swatify?</h2>
        <p>
          Swatify is a web application that allows people to better connect
          through music. Users create a profile on the site in order to rate
          pieces of music, post to discussion pages, follow other users, save
          links to songs, and receive unique music recommendations. There are
          many programs that provide users with music recommendations that are
          either restricted to a certain music genre (based on what has been
          previously listened to) or are not tailored to the user and are based
          on the top charts. The best music recommendations ultimately come from
          others whose music taste we trust and can relate to. This application
          allows users to discover a broader range of music at a deeper level
          than what is possible with existing music-based social networks.
        </p>
        <h2>What makes Swatify unique?</h2>
        <p>
          There are a number of features that distinguish Swatify from existing
          applications that focus on music discovery. With a focus on the human
          component of discovering new music, Swatify aims to provide users with
          a more comprehensive music recommendation system than current
          automated software allows in applications like Spotify and Pandora.
          However, these applications provide streaming and a connection to a
          larger body of music content and users. Swatify aims to give its users
          the option to their accounts with other applications in order to
          cultivate a single destination for music discussion and discovery. The
          ability to link Spotify, SoundCloud, and Bandcamp accounts, among
          others, to a Swatify profile is something currently unavailable on
          musical discussion sites like Rate Your Music. Unlike Rate Your Music,
          Swatify users will be able to interact with an Activity Feed, send
          pieces of music to other users, create playlists, and following
          specific artists. Thus, Swatify fosters a network of music sharing in
          a way unlike any existing applications. Swatify serves as an
          all-in-one interface for users to send, discover, and save music among
          a community that shares their musical taste.
        </p>
      </div>
    );
  }
}

export default Home;
