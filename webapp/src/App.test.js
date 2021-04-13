import React from 'react';
import ReactDOM from 'react-dom';
import {render} from '@testing-library/react';
import fetch from "jest-fetch-mock"
import App from './App';
import RocketList from "./RocketList";

test('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<App/>, div);
    ReactDOM.unmountComponentAtNode(div);
});

test('renders RocketList with empty rocket data', () => {
    const component = render(<RocketList/>);
    const customerComponent = component.getAllByText("Loading...");
    expect(customerComponent).not.toBeNull();
});