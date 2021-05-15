import React, {Component} from 'react';
import './Winner.css'

class Winner extends Component {
    render() {
        const winner = this.props.name;
        return (
            <div className='winner'>
                Winner(s): <span className='winner'>{winner}</span>
            </div>
        );
    }
}

export default Winner;