import React, {Component} from 'react';
import './Board.css'
import Pit from "./Pit";
import Bank from "./Bank";

class Board extends Component {

    render() {
        const pits = Object.assign([],this.props.pits);
        const players = Object.assign([],this.props.players);
        const move = this.props.move;

        //works only if players are 2
        const numberOfPitsForEachPlayer = (pits.length/2)-1;
        const player1Pits = pits.slice(0,(pits.length/2)-1);
        const player1Bank = pits[(pits.length/2)-1];
        const player2Pits = pits.slice(pits.length/2,pits.length-1);
        const player2Bank = pits[pits.length-1];
        return (
            <div className={'board'}>
                <div className={'playground'}>
                    <div className={'endsection'}>
                        <Bank pit={player2Bank}></Bank>
                    </div>
                    <div className={'midsection'} flex-grow={numberOfPitsForEachPlayer}>
                        <div className={'midrow'}>
                            {player2Pits.reverse().map((pit,key) => <Pit key={key} pit={pit} move={move}></Pit>)}
                        </div>
                        <div className={'midrow'}>
                            {player1Pits.map((pit,key) => <Pit key={key} pit={pit} move={move}></Pit>)}
                        </div>
                    </div>
                    <div className={'endsection'}>
                        <Bank pit={player1Bank}></Bank>
                    </div>
                </div>
            </div>
        );
    }
}

export default Board;