package frame;

// JAVA UTILITIES IMPORT
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import java.awt.*;

// ENTITIES IMPORT
import entities.enemies.Enemy;
import entities.enemies.EnemyMage;
import entities.enemies.EnemyNinja;
import entities.enemies.enemyBullets.EnemyBullet;
import entities.friends.FriendGhost;
import entities.player.Bullet;
import entities.player.Control;
import entities.player.Player;

public class GamePanel extends JPanel {
    // SCREEN SIZE & BACKGROUND & etc..
    Image BACKGROUND;
    Image AIM;
    Image GROUND;
    public static final int SCREEN_WIDTH = 1366;
    public static final int SCREEN_HEIGHT = 768 ;

    // GAME STATE VARIABLES
    // FRAMES ARE LIKE A TIMER BUT GLOBAL
    Timer game_loop;
    public int FRAME;

    // ENTITITES VARIABLE
    LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    LinkedList<Enemy> enemies = new LinkedList<Enemy>();
    LinkedList<EnemyBullet> enBullets = new LinkedList<EnemyBullet>();
    LinkedList<FriendGhost> friends = new LinkedList<FriendGhost>();
    public static Player player = new Player();

    // PLAYER MOUSE POSITION
    public static int aimLocX = 0;
    public static int aimLocY = 0;

    // ====GAME CONSTRUCTOR=====//
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
        g2.setColor(Color.white);
        display(g2);
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

            // ENEMY INTITAILZAITION AND CONFIGURATION
            enemyInt();

            // PLAYER POWER UPS CONFIGURATION
            intPlayerUps();

            // INT FRIENDLY GHOST
            intFriendlyGhost();

            collides();
            player.update();
            clear();
            repaint();
        }
    }

    ///////////////////////////////////////////////////////////
    /////////// MISCELLANOUS//////////////////////////////////
    /////////////////////////////////////////////////////////

    // INITIALIZE IMAGES FOR THE PANEL
    private void initImages() {
        BACKGROUND = new ImageIcon(this.getClass().getResource("resource/bg.jpg")).getImage();
        AIM = new ImageIcon(this.getClass().getResource("resource/aim.png")).getImage();
        GROUND = new ImageIcon(this.getClass().getResource("resource/ground.png")).getImage();
    }

    // GAME INITIALIZATION OR START
    private void gameStart() {
        game_loop.start();
    }

    // DRAWING METHOD
    private void display(Graphics2D g2) {
        //BACKGROUND CONFIGURATION
       g2.drawImage(BACKGROUND, getBgOffset(), 0, null);
        g2.drawImage(GROUND, 0, SCREEN_HEIGHT - 125, null); 
        // DRAW ENEMIES
        for (Enemy enemy : enemies) {
            enemy.draw(g2);
        }

        // DRAW FRIENDLY GHOST
        for (FriendGhost friend : friends) {
            friend.draw(g2);
        }

        // DRAW PLAYER
        player.draw(g2);

        // DRAW BULLETS
        for (Bullet bullet : bullets) {
            bullet.draw(g2);
        }

        // DRAW AIM CURSOR
        g2.drawImage(AIM, aimLocX - 32, aimLocY - 32, null);
        // DRAW ENEMY BULLETS
        for (EnemyBullet enBulls : enBullets) {
            enBulls.draw(g2);
        }

    }

    // COLLISON / DELETION FUNCTION
    private void collides() {
        // COLLISON FOR ENEMY AND BULLETS AND VICE VERSA
        for (Enemy enemy : enemies) {
            for (Bullet bullet : bullets) {
                if (bullet.intersects(enemy)) {
                    enemy.isAlive = false;
                    bullet.hit = true;
                }
            }
        }

        // COLLISION FOR ENEMY BULLET AND BULLET
        for (EnemyBullet enbullet : enBullets) {
            for (Bullet bullet : bullets) {
                if (bullet.intersects(enbullet)) {
                    bullet.hit = true;
                    enbullet.hit = true;
                }
            }
        }

        // COLLISION FOR ENEMY BULLETS AND PLAYER
        for (EnemyBullet enBullet : enBullets) {
            if (enBullet.intersects(player)) {
                enBullet.hit = true;
            }
        }

        // THIS WHERE WE REMOVE THE ITEM WHEN COLLISION
        bullets.removeIf(el -> el.hit == true);
        enBullets.removeIf(el -> el.hit == true);
        enemies.removeIf(el -> el.isAlive == false);
    }

    // MEMORY CLEANING
    private void clear() {
        final int MINIMUM = -100;

        bullets.removeIf(en -> en.y < MINIMUM || en.y > SCREEN_HEIGHT || en.x < MINIMUM || en.x > SCREEN_WIDTH);
        enemies.removeIf(en -> en.x < MINIMUM || en.x > SCREEN_WIDTH);
        enBullets.removeIf(en -> en.y < MINIMUM || en.y > SCREEN_HEIGHT || en.x < MINIMUM || en.x > SCREEN_WIDTH);
    }

    ////////////////////////////////////////////////////////////
    //////// PARALLAX EFFECT////////////////////////////////////
    //////////////////////////////////////////////////////////
    private int getBgOffset() {
        int BGWIDTH = 1645;
        double coords = player.getX() / (SCREEN_WIDTH - player.getWidth());
        return (int) -((BGWIDTH - SCREEN_WIDTH) * coords);
    }

    // ENEMY CONFIGURATION AND SPAWNING METHOD
    private void enemyInt() {
        // RATE OF ENEMY SPAWNS
        // MAGE TYPE
        if (FRAME % 90 == 0) {
            enemies.add(new EnemyMage());
        }
        // ASSASIN TYPE
        if (FRAME % 90 == 0) {
            enemies.add(new EnemyNinja());
        }

        // UPDATE ENEMY POSITION
        for (Enemy enemy : enemies) {
            enemy.update();
        }

        // SPAWN ENEMY BULLETS
        if (FRAME % 60 == 0) {
            for (Enemy enemy : enemies) {
                int x = (int) enemy.getX();
                int y = (int) enemy.getY();
                if (x > 150 && x < SCREEN_WIDTH - 100) {
                    EnemyBullet enBull = enemy.shoot(x, y);
                    enBullets.add(enBull);
                }
            }
        }

        for (EnemyBullet enBulls : enBullets) {
            enBulls.update();
        }
    }

    // METHOD FOR PLAYER POWER UPS PANEL CONFIGURATION
    private void intPlayerUps() {
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
    }

    // METHOD FOR FRIENDLY GHOST PANEL CONFIGURATION
    private void intFriendlyGhost() {
        if (FRAME % 900 == 0) {
            friends.add(new FriendGhost());
        }

        for (FriendGhost friend : friends) {
            friend.update();
        }
    }
}
