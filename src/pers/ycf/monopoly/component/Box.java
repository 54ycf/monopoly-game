package pers.ycf.monopoly.component;

import pers.ycf.monopoly.constant.Effect;

public class Box {
    private int x;
    private int y;

    private String label; //mark the number of the box
    private Effect e = Effect.none; //default has no effect

    public Box(int x, int y, String label){
        this.x = x;
        this.y = y;
        this.label = label;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setE(Effect e) {
        this.e = e;
    }

    public Effect getE() {
        return e;
    }
}
