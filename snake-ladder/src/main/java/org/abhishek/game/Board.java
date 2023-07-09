package org.abhishek.game;

import java.util.concurrent.ThreadLocalRandom;

public class Board {

    Cell[][] cells;

    public Board(int boardSize, int numberOfSnakes, int numberOfLadder) {
        initializeBoard(boardSize);
        addSnakesAndLadder(numberOfSnakes, numberOfLadder);
    }

    void initializeBoard(int boardSize){
        cells = new Cell[boardSize][boardSize];
        for(int i = 0 ; i < boardSize ; ++i){
            for(int j = 0 ; j < boardSize ; ++j){
                cells[i][j] = new Cell();
            }
        }
    }

    void addSnakesAndLadder(int numberOfSnakes, int numberOfLadders){
        while(numberOfSnakes > 0){
            int start = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length - 1);
            int end = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length - 1);
            if(end >= start){
                continue;
            }
            Jump snakeObj = new Jump(start, end);
            Cell cell = getCell(start);
            cell.setJump(snakeObj);
            numberOfSnakes--;
        }
        while(numberOfLadders > 0){
            int start = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length - 1);
            int end = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length - 1);
            if(start >= end){
                continue;
            }
            Jump ladderObj = new Jump(start, end);
            Cell cell = getCell(start);
            cell.setJump(ladderObj);
            numberOfLadders--;
        }
    }

    Cell getCell(int position){
        return  cells[position / cells.length][position % cells.length];
    }

}
