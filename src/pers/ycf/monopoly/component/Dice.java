package pers.ycf.monopoly.component;

import javax.swing.*;
import java.util.Random;

public class Dice {

    private int point = 1;

    /**
     * throw a die
     * @return the new point
     */
    public int generatePoint(){
        Random rd = new Random();
        this.point = rd.nextInt(1,7);
        return this.point;
    }

    public int getPoint() {
        return point;
    }

}
