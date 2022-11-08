package pers.ycf.monopoly.component;

import pers.ycf.monopoly.constant.ConsVal;
import pers.ycf.monopoly.constant.Effect;

import javax.swing.*;
import java.awt.*;

public class Player {
    private String name;
    private int x;
    private int y;
    private int curP;
    private Color color;

    public Player(String name, int x, int y, Color color, int curP) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = color;
        this.curP = curP;
    }

    public void moveOneForward(){
        if(curP < 11){
            x += ConsVal.BOX_WIDTH;
        } else if (curP < 16) {
            y += ConsVal.BOX_HEIGHT;
        } else if (curP < 27) {
            x -= ConsVal.BOX_WIDTH;
        } else {
            y -= ConsVal.BOX_HEIGHT;
        }
        curP = (++curP) % 32;
    }
    private void moveOneBackward(){
        if(curP < 11){
            x -= ConsVal.BOX_WIDTH;
        } else if (curP < 16) {
            y -= ConsVal.BOX_HEIGHT;
        } else if (curP < 27) {
            x += ConsVal.BOX_WIDTH;
        } else {
            y += ConsVal.BOX_HEIGHT;
        }
        curP = (--curP) % 32;

    }

    public void go(int steps) {
        for (int i = 0; i < steps; ++i) {
            moveOneForward();
        }
    }

    public void back(int steps) {
        for (int i = 0; i < steps; ++i) {
            moveOneBackward();
        }
    }

    public void effectDisplay(Effect effect) {
        switch (effect) {
            case go1, go2, go3 -> {
                go(effect.ordinal());
            }
            case bk1, bk2, bk3 -> {
                back(effect.ordinal()-3);
            }
        }
    }



    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public int getCurP() {
        return curP;
    }

}
