package org.abhishek.game;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int count;
    int min = 1;
    int max = 6;

    public Dice(int count) {
        this.count = count;
    }

    public int roll(){
        int diceUsed = 0;
        int res = 0;
        while(diceUsed < count){
            res += ThreadLocalRandom.current().nextInt(min, max);
            diceUsed++;
        }
        return  res;
    }
}
