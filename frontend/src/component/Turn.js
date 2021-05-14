import React, {Component} from 'react';
import './Turn.css'

class Turn extends Component {
    render() {
        const turn = this.props.player != null ? `${this.props.player.name}` : '';
        return (
            <div className='turn'>
                Turn: <span className='turn'>{turn}</span>
            </div>
        );
    }
}

export default Turn;