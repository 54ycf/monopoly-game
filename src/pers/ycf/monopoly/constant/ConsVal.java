package pers.ycf.monopoly.constant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConsVal {
    public static final int FRAME_X = 20;
    public static final int FRAME_Y = 20;
    public static final int FRAME_WIDTH = 1120;
    public static final int FRAME_HEIGHT = 650;
    public static final int BOX_WIDTH = 80;
    public static final int BOX_HEIGHT = 80;
    public static final int PLAYER1_START_X = 82;
    public static final int PLAYER2_START_X = 126;
    public static final int PLAYER1_START_Y = 126;
    public static final int PLAYER2_START_Y = 126;

    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 32;
    public static final int DICE_X = 600;
    public static final int DICE_Y = 250;

    public static BufferedImage[] dices;
    public static void init(){
        dices = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            try {
                dices[i] = ImageIO.read(new File(System.getProperty("user.dir") + "/images/" + (i+1) + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
