package frame;

import java.awt.event.*;
import javax.swing.*;

import entities.Enemy;
import entities.Bullet;

import java.awt.*;

public class GamePanel extends JPanel implements ActionListener {
    //ENTITIES IMAGE
    Image AIM;
    // SCREEN SIZE & BACKGROUND
    Image BACKGROUND;
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 600;

    // GAME STATE VARIABLES
    Timer game_loop;
    int FRAME;

    // ENTITITES VARIABLE
    Bullet bullet;
    Enemy enemy = new Enemy();

    // PLAYER MOUSE POSITION
    public static int playerLocX = 0;
    public static int playerLocY = 0;

    // GAME CONTRUCTOR
    public GamePanel() {
        BACKGROUND = new ImageIcon(this.getClass().getResource("resource/bg.gif")).getImage();
        AIM = new ImageIcon(this.getClass().getResource("resource/aim.png")).getImage();
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        game_loop = new Timer(17, this);
        gameStart();
    }

    // GAME INITIALIZATION OR START
    void gameStart() {
        game_loop.start();
    }

    // PAINT METHOD OF EVERY COMPONENTS
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(BACKGROUND, 0, 0, null);
        enemy.draw(g2);
        g2.drawImage(AIM, playerLocX-32, playerLocY-32, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this filters out if the source is a timer
        if (e.getSource() == game_loop) {
            // ADD TO FRAME (17ms)
            FRAME += 1;
            Control.getMousePosition(this);
            if (FRAME % 3 == 0) {
                enemy.update();
            }
            repaint();
        }

        //this is a mouse listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bullet = new Bullet(e.getX(), e.getY());
                if (bullet.intersects(enemy)) {
                    JOptionPane.showMessageDialog(null, "caught");
                }
            }
        });
    }
}
