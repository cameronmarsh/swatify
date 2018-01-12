# Swatify

We will develop a web application that allows people to better connect through music. Users will create a profile on the site in order to rate pieces of music, post to discussion pages, follow other users, save links to songs, and receive unique music recommendations among other features. There are many programs that provide users with music recommendations that are either restricted to a certain music genre (based on what has been previously listened to) or are not tailored to the user and are based on the top charts. The best music recommendations ultimately come from others whose music taste we trust and can relate to. This application will allow users to discover a broader range of music at a deeper level than what is possible with existing music-based social networks.  

## Setup

### Backend

A [Spotify API application](https://beta.developer.spotify.com/) is required to run the backend API. That application must also have <http://localhost:3000/callback> added to its redirect URIs at <https://beta.developer.spotify.com/dashboard/applications/>.

To run the application:

```sh
cp src/main/resources/secrets-template.json src/main/resources/secrets.json
```

Then fill in the client ID and client secret in `secrets.json` with the values
provided by the Spotify API.

### Frontend

**Note: Requires having npm installed.**

The frontend (in `src/main/frontend`) uses a standard React.js project structure with a separate file for each component for maintainability purposes, [ES6 features](https://github.com/lukehoban/es6features) because they're nice, and [Sass](http://sass-lang.com/guide) instead of standard CSS for the added features. However, this means that none of our front-end code can run in the browser without compilation (to ES5 for the JavaScript, and to CSS for the Sass files).

### Production Build

Build the frontend JavaScript and CSS files (into `src/main/resources/build`) so they can be statically served by the Spark server (at http://localhost:4567).

```sh
cd src/main/frontend
npm install
npm run build
```

### Development server

Run the uncompiled code on a Node.js server for development purposes. You can then access the application at http://localhost:3000. npm automatically forwards requests by the frontend to http://localhost:4567, so if the Spark development server is running at the same time then the frontend will be able to interact with the API.

```sh
cd src/main/frontend
npm install
npm run start # Run a React server, watching for changes in JavaScript.
npm run watch-css # Watch for changes to *.scss files and auto-build.
```
