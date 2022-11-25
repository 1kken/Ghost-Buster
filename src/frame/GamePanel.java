package frame;

import java.awt.event.*;
import javax.swing.*;

import entities.Enemy;
import entities.Player;
import entities.Bullet;

import java.awt.*;

public class GamePanel extends JPanel {
    // SCREEN SIZE & BACKGROUND & etc..
    Image BACKGROUND;
    Image AIM;
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 600;

    // GAME STATE VARIABLES
    Timer game_loop;
    int FRAME;

    // ENTITITES VARIABLE
    Bullet bullet;
    Player player = new Player();
    Enemy enemy = new Enemy();

    // PLAYER MOUSE POSITION
    public static int playerLocX = 0;
    public static int playerLocY = 0;

    // GAME CONTRUCTOR
    public GamePanel() {
        // INITIALIZE IMAGES
        initImages();

        // SET SIZE OF GAME PANEL 
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        // INITIALIZE A 
        ActionHandler actionHandler = new ActionHandler();
        createListeners();

        // this are for the game state
        game_loop = new Timer(17, actionHandler);
        gameStart();
    }

    // PAINT METHOD OF EVERY COMPONENTS
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(BACKGROUND, 0, 0, null);
        enemy.draw(g2);
        player.draw(g2);
        g2.drawImage(AIM, playerLocX - 32, playerLocY - 32, null);
    }

    ////////////////////////////////////////////////////////////////////////
    /////////////// DECLARATION OF INNER CLASSES & ETC..///////////////////
    ///////////////////////////////////////////////////////////////////////
    private void createListeners() {

        //THIS IS FOR THE KEYLISTENER
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 65) {
                    player.update(65);
                }
                if (e.getKeyCode() == 68) {
                    player.update(68);
                }
            }
        });

        // THIS IS FOR THE MOUSE LISTENER
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bullet = new Bullet(e.getX(), e.getY());
                if (bullet.intersects(enemy)) {
                    enemy = new Enemy();
                }
            }
        });
    }

    // INNER CLASS FOR THE TIMER
    public class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FRAME += 1;
            Control.getMousePosition(GamePanel.this);
            if (FRAME % 3 == 0) {
                enemy.update();
            }
            repaint();
        }
    }

    ///////////////////////////////////////////////////////////
    /////////// MISCELLANOUS///////////////////////////////////
    /////////////////////////////////////////////////////////
    private void initImages() {
        BACKGROUND = new ImageIcon(this.getClass().getResource("resource/bg.gif")).getImage();
        AIM = new ImageIcon(this.getClass().getResource("resource/aim.png")).getImage();
    }

    // GAME INITIALIZATION OR START
    void gameStart() {
        game_loop.start();
    }
}
