package pers.ycf.monopoly;

import pers.ycf.monopoly.component.Board;
import pers.ycf.monopoly.component.Box;
import pers.ycf.monopoly.component.Dice;
import pers.ycf.monopoly.component.Player;
import pers.ycf.monopoly.constant.ConsVal;
import pers.ycf.monopoly.constant.Effect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {
    Player[] players = new Player[2];
    Board board;
    Dice dice;
    int round;
    String label;
    private Image offScreenImage;

    public GameFrame() {
        init();
        JOptionPane.showMessageDialog(this, "游戏分为红蓝双方。点击骰子开始行动！");
        this.setBounds(ConsVal.FRAME_X, ConsVal.FRAME_Y, ConsVal.FRAME_WIDTH, ConsVal.FRAME_HEIGHT);
        addMyListener();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("大富翁");
    }

    private void init(){
        board = new Board();
        board.init();
        dice = new Dice();
        round = 0;
        players[0] = new Player("Red", ConsVal.PLAYER1_START_X, ConsVal.PLAYER1_START_Y, Color.RED,0);
        players[1] = new Player("Blue", ConsVal.PLAYER2_START_X, ConsVal.PLAYER2_START_Y, Color.BLUE,0);
        label = "hello~";
    }

    private void addMyListener(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int mouseX = e.getX();
                int mouseY = e.getY();
                if (mouseX >= ConsVal.DICE_X && mouseX <= ConsVal.DICE_X+ConsVal.dices[0].getWidth() && mouseY >= ConsVal.DICE_Y && mouseY <= ConsVal.DICE_Y+ConsVal.dices[0].getHeight() ){
                    round++;
                    Player thisOne = players[round % 2];
                    label = "现在是第" + round + "轮，" + thisOne.getName() + "的回合";

                    new Thread(() -> {
                            //throw a die
                            int flag = 18;
                            while (flag--!=0){
                                dice.generatePoint();
                                repaint();
                                try {
                                    Thread.sleep(40);
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }

                            // move a player
                            for(int i=0; i<dice.getPoint(); ++i) {
                                thisOne.moveOneForward();
                                repaint();
                                if (thisOne.getCurP() == 0) {
                                    JOptionPane.showMessageDialog(GameFrame.this, "恭喜! 玩家" + thisOne.getName() + "赢得比赛!");
                                    restart();
                                    break;
                                }
                                try {
                                    int sleepTime = i==(dice.getPoint()-1)?400:40;
                                    Thread.sleep(sleepTime);
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }

                            }
                            //if some effect on the box, then execute
//                                SwingUtilities.invokeLater( () -> { // Runnable
                                    Effect theBoxEffect = board.getBoxes()[thisOne.getCurP()].getE();
                                    if (!theBoxEffect.equals(Effect.none)) {
                                        thisOne.effectDisplay(theBoxEffect);
                                        repaint();
                                    }
//                           });
                        }).start();
                }
            }
        });
    }

    private void restart(){
        init();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(offScreenImage==null){
            offScreenImage=createImage(ConsVal.FRAME_WIDTH, ConsVal.FRAME_HEIGHT);
        }
        Graphics graphics=offScreenImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0, ConsVal.FRAME_WIDTH, ConsVal.FRAME_HEIGHT);
        graphics.setFont(new Font("console", Font.BOLD, 20));
        graphics.setColor(Color.BLACK);

        // draw board
        for (Box box : board.getBoxes()) {
            graphics.drawRect(box.getX(), box.getY(), ConsVal.BOX_WIDTH, ConsVal.BOX_HEIGHT);
            if (box.getE().equals(Effect.none)){
                graphics.drawString(box.getLabel(), box.getX()+36, box.getY()+42);
            }else {
                graphics.setColor(Color.GREEN);
                graphics.drawString(box.getE().toString(), box.getX()+20, box.getY()+42);
                graphics.setColor(Color.BLACK);
            }
        }

        //draw players
        for (Player player : players) {
            graphics.setColor(player.getColor());
            graphics.fillRect(player.getX(),player.getY(), ConsVal.PLAYER_WIDTH, ConsVal.PLAYER_HEIGHT);
        }

        //draw the die
        graphics.drawImage(ConsVal.dices[dice.getPoint() - 1], ConsVal.DICE_X, ConsVal.DICE_Y, this);
        graphics.drawString(label, ConsVal.DICE_X-100, ConsVal.DICE_Y);

        g.drawImage(offScreenImage, 0, 0, this);
    }


}
