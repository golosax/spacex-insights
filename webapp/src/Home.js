import React, {Component} from 'react';
import {Container} from 'reactstrap';
import RocketList from "./RocketList";

class Home extends Component {
    render() {
        return (
            <div>
                <Container fluid>
                    <h1>SpaceX rockets</h1>
                    <br/>
                    <RocketList/>
                </Container>
            </div>
        );
    }
}

export default Home;