import React, { Component } from 'react';
import './Bank.css';

class Bank extends Component {

    render() {
        const bank = this.props.pit;
        return (
            <span className='bank'> {bank.seeds} </span>
        );
    }
}

export default Bank;