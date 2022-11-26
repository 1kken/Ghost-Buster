package frame;

import java.awt.event.*;

import javax.sound.sampled.LineEvent;
import javax.swing.*;
import java.util.LinkedList;

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
    LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    Player player = new Player();
    Enemy enemy = new Enemy();

    // PLAYER MOUSE POSITION
    public static int aimLocX = 0;
    public static int aimLocY = 0;

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
        for (Bullet bullet : bullets) {
            bullet.draw(g2);
        }
        g2.drawImage(AIM, aimLocX - 32, aimLocY - 32, null);
    }

    ////////////////////////////////////////////////////////////////////////
    /////////////// DECLARATION OF INNER CLASSES & ETC..///////////////////
    ///////////////////////////////////////////////////////////////////////
    int SHOOT = 0;

    private void createListeners() {

        // THIS IS FOR THE KEYLISTENER
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });

        // THIS IS FOR THE MOUSE LISTENER
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SHOOT = 1;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                SHOOT = 0;
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
            // spaw bullets
            if (FRAME % 5 == 0 && SHOOT == 1) {
                bullets.add(new Bullet((int) player.getCenterX(), (int) player.getCenterY()));
            }
            // updating bullets
            for (Bullet bullet : bullets) {
                bullet.update();
            }

            player.update();
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
