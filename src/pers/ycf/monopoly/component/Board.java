package pers.ycf.monopoly.component;

import pers.ycf.monopoly.constant.ConsVal;
import pers.ycf.monopoly.constant.Effect;

public class Board {
    Box[] boxes = new Box[32]; //there are 36 squares in the chessboard

    public Box[] getBoxes() {
        return boxes;
    }


    public void init(){
        int boxWidth = ConsVal.BOX_WIDTH;
        int boxHeight = ConsVal.BOX_HEIGHT;
        for (int i = 0; i < 12; i++) {
            boxes[i] = new Box(boxWidth*(i + 1),80, String.valueOf(i));
        }
        for (int i = 12; i < 16; i++) {
            boxes[i] = new Box(960,boxHeight*(i-10), String.valueOf(i));
        }
        for (int i = 16; i < 28; i++) {
            boxes[i] = new Box(960-(i-16)*boxWidth, 480, String.valueOf(i));
        }
        for (int i = 28; i < 32; i++) {
            boxes[i] = new Box(80, 480 - (i - 27) * boxHeight, String.valueOf(i));
        }
        boxes[4].setE(Effect.bk3); //add some effects
        boxes[8].setE(Effect.go2);
        boxes[11].setE(Effect.go1);
        boxes[13].setE(Effect.go3);
        boxes[19].setE(Effect.bk3);
        boxes[20].setE(Effect.bk2);
        boxes[27].setE(Effect.go1);
        boxes[29].setE(Effect.go2);
    }
}
