package frame;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

import entities.Enemy;

import java.awt.*;

public class GamePanel extends JPanel implements ActionListener {
    // SCREEN SIZE
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 600;

    // GAME STATE VARIABLES
    Timer game_loop;
    int FRAME;

    //ENTITITES VARIABLE
    Enemy enemy = new Enemy();

    //PLAYER MOUSE POSITION
    public static int playerLocX = 0;
    public static int playerLocy = 0;

    // GAME CONTRUCTOR
    public GamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        game_loop = new Timer(17, this);
        gameStart();
    }

    //GAME INITIALIZATION OR START
    void gameStart(){
        game_loop.start();
    }

    //PAINT METHOD OF EVERY COMPONENTS
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.fillRect(playerLocX - 15,playerLocy - 15, 30,30);
        enemy.draw(g2);
    }  



    @Override
    public void actionPerformed(ActionEvent e) {
        Control.getMousePosition(this);
        enemy.update();
        repaint();
    }
}
