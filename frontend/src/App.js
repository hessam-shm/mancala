import {Helmet} from 'react-helmet';
import './App.css';
import {Component} from "react";
import Board from "./component/Board";
import Info from "./component/Info";
import {start} from "./component/functions/functions";
import {moveApi} from "./component/functions/functions";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            board: null,
            turn: null,
            winner: '',
            message: 'Lets get started'
        };
    }

    render() {
        const {board, turn, winner, message} = this.state;
        return (
            <div className="App">
                <Helmet>
                    <style>{'body { background-color: #0000a4; }'}</style>
                </Helmet>
                {board != null ? (
                    <Board pits={board.pits} players={board.players} move={this.move}></Board>
                ) : (<div className={'splashscreen'}><h1 className={'gamename'}>Mancala</h1></div> )
                }

                <Info information={{turn,winner,message}} start={this.newGame}></Info>
            </div>
        );
    }

    newGame = async () => {
        const data = await start().catch(() => console.log(data));
        if (data) {
            this.setState({
                message: data.message,
                winner: data.winner,
                turn: data.turn,
                board: data.board
            })
        }
    };

    move = async (index) => {
        const result = await moveApi(index).catch(() => console.log(result));
        if(result) {
            this.setState({
                message: result.message,
                winner: result.winner,
                turn: result.turn,
                board: result.board
            })
        }
    };
}

export default App;
