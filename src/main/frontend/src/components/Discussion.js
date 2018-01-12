import React, { Component } from 'react';


class Album extends Component {
    state = {loading: true}

//    componentDidMount(){
//        swatifyFetch('/api/v1/discussions/')
//              .then(res => res.json())
//              .then(album => this.setState({ album: album.name,
//                                artist: album.artists[0].name,
//                                image: album.images[0].url} ));
//    }

    render() {
        return <div></div>;

//            <div>
//                <h1>{this.state.album}</h1>
//                <h2>{this.state.artist}</h2>
//                <img src={this.state.image} alt="" height="200" width="200"></img>
//            </div>);
    }
}

export default Discussion;