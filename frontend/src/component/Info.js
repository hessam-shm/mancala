import React, {Component} from 'react';
import './Info.css'
import Logo from "./Logo";
import NewGame from "./NewGame";
import Winner from "./Winner";
import Turn from "./Turn";
import Message from "./Message";

class Info extends Component {
    render() {
        const {turn, winner, message} = this.props.information
        const start = this.props.start;
        return (
            <div className={'info'}>
                <Logo></Logo>
                <NewGame start={start}></NewGame>
                <Winner name={winner}></Winner>
                <Turn player={turn}></Turn>
                <Message content = {message}></Message>
            </div>
        );
    }
}

export default Info;