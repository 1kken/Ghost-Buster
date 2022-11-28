package frame;

import java.awt.event.*;

import javax.swing.*;
import java.util.LinkedList;

import entities.enemies.Enemy;
import entities.enemies.EnemyMage;
import entities.enemies.EnemyNinja;
import entities.player.Bullet;
import entities.player.Control;
import entities.player.Player;

import java.awt.*;

public class GamePanel extends JPanel {
    // SCREEN SIZE & BACKGROUND & etc..
    Image BACKGROUND;
    Image AIM;
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 600;

    // GAME STATE VARIABLES
    Timer game_loop;
    // FRAMES ARE LIKE A TIMER BUT GLOBAL
    public int FRAME;

    // ENTITITES VARIABLE
    LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    LinkedList<Enemy> enemies = new LinkedList<Enemy>();
    Player player = new Player();

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

        // START THE GAME
        game_loop = new Timer(17, actionHandler);
        gameStart();
    }

    // PAINT METHOD FOR EVERY COMPONENTS
    public void paint(Graphics g) {
        // CAST GRAPHIC OBJECT TO GRAPHICS 2D
        Graphics2D g2 = (Graphics2D) g;
        display(g2);
    }

    ////////////////////////////////////////////////////////////////////////
    /////////////// DECLARATION OF INNER CLASSES & ETC..///////////////////
    ///////////////////////////////////////////////////////////////////////
    // SHOOTING TRIGGER
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

            // RATE OF ENEMY SPAWNS
            // MAGE TYPE
            if (FRAME % 60 == 0) {
                enemies.add(new EnemyMage());
            }
            // ASSASIN TYPE
            if (FRAME % 120 == 0) {
                enemies.add(new EnemyNinja());
            }
            
            //UPDATE ENEMY POSITION
            for (Enemy enemy : enemies) {
                enemy.update();
            }

            // spawn BULLETS
            int OFFSET = 2;
            if (FRAME % 5 == 0 && SHOOT == 1) {
                if (player.NUMOFBULLETS == 1) {
                    bullets.add(new Bullet((int) player.getCenterX(), (int) player.getCenterY(), 0));
                }
                if (player.NUMOFBULLETS == 2) {
                    bullets.add(new Bullet((int) player.getCenterX(), (int) player.getCenterY(), 0));
                    bullets.add(new Bullet((int) player.getCenterX(), (int) player.getCenterY(), -OFFSET));
                }
                if (player.NUMOFBULLETS == 3) {
                    bullets.add(new Bullet((int) player.getCenterX(), (int) player.getCenterY(), 0));
                    bullets.add(new Bullet((int) player.getCenterX(), (int) player.getCenterY(), -OFFSET));
                    bullets.add(new Bullet((int) player.getCenterX(), (int) player.getCenterY(), OFFSET));
                }
            }
            // updating bullets
            for (Bullet bullet : bullets) {
                bullet.update();
            }

            player.update();
            clear();
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
    private void gameStart() {
        game_loop.start();
    }

    // DRAWING METHOD
    private void display(Graphics2D g2) {
        g2.drawImage(BACKGROUND, getBgOffset(), -150, null);
        for (Enemy enemy : enemies) {
            enemy.draw(g2);
            ;
        }
        player.draw(g2);
        for (Bullet bullet : bullets) {
            bullet.draw(g2);
        }
        g2.drawImage(AIM, aimLocX - 32, aimLocY - 32, null);

    }

    // MEMORY CLEANING
    private void clear() {
        final int MINIMUM = -100;
        bullets.removeIf(en -> en.y < MINIMUM || en.y > SCREEN_HEIGHT + 100 || en.x < MINIMUM || en.x > SCREEN_WIDTH);
        enemies.removeIf(en -> en.x < MINIMUM || en.x > SCREEN_WIDTH);
    }

    ////////////////////////////////////////////////////////////
    //////// PARALLAX EFFECT////////////////////////////////////
    //////////////////////////////////////////////////////////
    private int getBgOffset() {
        int BGWIDTH = 1625;
        double coords = player.getX() / (SCREEN_WIDTH - player.getWidth());
        return (int) -((BGWIDTH - SCREEN_WIDTH) * coords);
    }
}
