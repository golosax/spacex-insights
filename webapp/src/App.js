import React, {Component} from 'react';
import Home from './Home';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/rockets' exact={true} component={Home}/>
                </Switch>
            </Router>
        )
    }
}

export default App;