import React, { Component } from 'react';
import './Pit.css';

class Pit extends Component {

    render() {
        const pit = this.props.pit;
        const move = this.props.move;
        const movePit = () => move(pit.index);
        return (
            <button className='pit' onClick={movePit}> {pit.seeds} </button>
        );
    }
}

export default Pit;