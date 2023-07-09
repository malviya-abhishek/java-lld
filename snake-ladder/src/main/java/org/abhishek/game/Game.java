package org.abhishek.game;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Game {

    Board board;
    Deque<Player> players;
    Dice dice;
    Player winner;

    public Game(int boardSize, int numberOfSnakes, int numberOfLadders, int numberOfPlayers, int numberOfDice) {
        initializeGame(boardSize, numberOfSnakes, numberOfLadders, numberOfPlayers, numberOfDice);
    }

    void initializeGame(int boardSize, int numberOfSnakes, int numberOfLadders, int numberOfPlayers, int numberOfDice){
        board = new Board(boardSize, numberOfSnakes, numberOfLadders);
        dice = new Dice(numberOfDice);
        addPlayer(numberOfPlayers);
        winner = null;
    }

    void addPlayer(int numberOfPlayers){
        players = new LinkedList<>();
        for(int i = 0 ; i < numberOfPlayers ; ++i){
            players.add(new Player("id_" + i, 0));
        }
    }

    public void startGame(){
        while (winner == null){
            Player player = findPlayerTurn();
            System.out.println("player turn is: " + player.getId() + " current position is " + player.getPosition());

            int diceRoll = dice.roll();

            int newPostion = player.getPosition() + diceRoll;
            newPostion = jumpCheck(newPostion);
            player.setPosition(newPostion);
            System.out.println("player turn is: " + player.getId() + " new position is " + player.getPosition());
            if(player.getPosition() >= board.cells.length * board.cells.length - 1){
                winner = player;
            }
        }
        System.out.println("Winner is : " + winner.getId());
    }

    Player findPlayerTurn(){
        Player player = players.removeFirst();
        players.addLast(player);
        return player;
    }

    int jumpCheck(int position){
        if(position > board.cells.length * board.cells.length - 1)
            return position;
        Cell cell = board.getCell(position);
        if(cell.getJump() != null && cell.getJump().getStart() == position){
            String jumpBy = (cell.getJump().getStart() < cell.getJump().getEnd() ? "ladder": "snake");
            System.out.println("Jump by: "+ jumpBy);
            return  cell.getJump().getEnd();
        }
        return position;
    }
}
