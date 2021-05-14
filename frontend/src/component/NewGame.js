import React, {Component} from 'react';
import './NewGame.css'

class NewGame extends Component {
    render() {
        const start = this.props.start;
        const startNewGame = () => start();
        return (
            <button className='newGame' onClick={startNewGame}>
                New Game
            </button>
        );
    }
}

export default NewGame;