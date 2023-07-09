package org.abhishek;

import org.abhishek.game.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(10, 5, 5, 3, 2);
        game.startGame();
    }
}