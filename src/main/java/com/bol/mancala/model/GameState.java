package com.bol.mancala.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GameState {

    private Board board;
    private Player turn;
    private Player winner;
    private String message;

    private GameState(Board board, Player turn, Player winner, String message){
        this.board = board;
        this.turn = turn;
        this.winner = winner;
        this.message = message;
    }

    public GameState(){}

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getTurn() {
        return turn;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return Objects.equals(getBoard(), gameState.getBoard()) &&
                Objects.equals(getTurn(), gameState.getTurn()) &&
                Objects.equals(getWinner(), gameState.getWinner()) &&
                Objects.equals(getMessage(), gameState.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoard(), getTurn(), getWinner(), getMessage());
    }

    public static class Builder{
        private Board board;
        private Player turn;
        private Player winner;
        private String message;

        public Builder board(Board board){
            this.board = board;
            return this;
        }
        public Builder turn(Player turn){
            this.turn = turn;
            return this;
        }
        public Builder winner(Player winner){
            this.winner = winner;
            return this;
        }
        public Builder message(String message){
            this.message = message;
            return this;
        }

        public GameState build(){
            return new GameState(board,turn,winner,message);
        }
    }
}
