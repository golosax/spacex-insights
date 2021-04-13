import React, {Component} from 'react';
import {Container, Table} from 'reactstrap';
import 'react-confirm-alert/src/react-confirm-alert.css';

class RocketList extends Component {

    constructor(props) {
        super(props);
        this.state = {rockets: [], isLoading: true};
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('/rockets')
            .then(response => response.json())
            .then(data => this.setState({rockets: data, isLoading: false}));
    }

    render() {
        const {rockets, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const rocketList = rockets.map(rocket => {
            return <tr>
                <td style={{whiteSpace: 'nowrap'}}>{rocket.name}</td>
                <td>{rocket.height} m</td>
                <td>{rocket.mass} kg</td>
                <td>{rocket.launches_successful}</td>
                <td>{rocket.failed_successful}</td>
                <td>
                    <div className="row">
                        {rocket.images.map(image => (
                            <div id="image_preview" class="column">
                                <a href={image} rel="noopener noreferrer" target="_blank"> <img alt={rocket.name}
                                                                                                src={image}/> </a>
                            </div>
                        ))}
                    </div>
                </td>
            </tr>
        });

        return (
            <div>
                <Container fluid>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="10%">Name</th>
                            <th width="10%">Height</th>
                            <th width="10%">Mass</th>
                            <th width="10%">Launched successfully</th>
                            <th width="10%">Launch failed</th>
                            <th width="30%">Images</th>
                        </tr>
                        </thead>
                        <tbody>
                        {rocketList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default RocketList;