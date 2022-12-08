package frame;

// JAVA UTILITIES IMPORT
import java.awt.event.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;
import java.awt.*;

// ENTITIES IMPORT
import entities.enemies.Enemy;
import entities.enemies.EnemyMage;
import entities.enemies.EnemyNinja;
import entities.enemies.enemyBullets.EnemyBullet;
import entities.friends.FriendGhost;
import entities.friends.powerUps.PowerUp;
import entities.player.Bullet;
import entities.player.Control;
import entities.player.Player;
import frame.gameStateVariables.LifeState;
import frame.gameStateVariables.PlayerState;
import frame.gameStateVariables.Score;
import utils.Audios;
import utils.CustomFont;
import utils.PowerUps;

public class GamePanel extends JPanel {
    // SCREEN SIZE & BACKGROUND & etc..
    JFrame ancestorFrame;
    public static Font customFont = CustomFont.load(60);
    public static Font customSmallFont = CustomFont.load(20);
    Image BACKGROUND;
    Image AIM;
    Image GROUND;
    public static final int SCREEN_WIDTH = 1366;
    public static final int SCREEN_HEIGHT = 768;
    // FOR SOUND EFFECTS
    Clip shootClip;
    Clip backgroundClip;
    Clip gameOverClip;
    LinkedList<Clip> ghostClips = new LinkedList<Clip>();

    // GAME STATE VARIABLES
    public static boolean gameOver = false;
    PlayerState playerState;
    boolean paused = false;
    // FRAMES ARE LIKE A TIMER BUT GLOBAL
    Timer game_loop;
    public int FRAME;

    // ENTITITES VARIABLE
    public static Player player = new Player();
    LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    LinkedList<Enemy> enemies = new LinkedList<Enemy>();
    LinkedList<EnemyBullet> enBullets = new LinkedList<EnemyBullet>();
    LinkedList<FriendGhost> friends = new LinkedList<FriendGhost>();
    LinkedList<PowerUp> powerUps = new LinkedList<PowerUp>();

    // GAME STATE VARIABLES
    Score score = new Score();
    LinkedList<LifeState> health = new LinkedList<LifeState>();

    // PLAYER MOUSE POSITION
    public static int aimLocX = 0;
    public static int aimLocY = 0;

    // ====GAME CONSTRUCTOR=====//
    public GamePanel() {
        // INTITIALIZE SOUNDS
        intSounds();

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

        // HEALTH DISPLAY
        heartsInt();

    }

    // PAINT METHOD FOR EVERY COMPONENTS
    public void paint(Graphics g) {
        // CAST GRAPHIC OBJECT TO GRAPHICS 2D
        Graphics2D g2 = (Graphics2D) g;

        // SET G@ CHARACTERISTICS
        g2.setFont(customFont);
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
                if (e.getKeyChar() == ' ') {
                    if (paused == false) {
                        paused = true;
                        game_loop.stop();
                    } else {
                        game_loop.start();
                        paused = false;
                    }
                }
                if (gameOver == true) {
                    if (e.getKeyChar() == 'y' || e.getKeyChar() == 'Y') {
                        gameOverClip.stop();
                        reset();
                    } else if (e.getKeyChar() == 'n' || e.getKeyChar() == 'N') {
                        ancestorFrame.dispose();
                    }
                }
            }
        });

        // THIS IS FOR THE MOUSE LISTENER
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SHOOT = 1;
                shootClip.loop(-1);
                shootClip.start();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                SHOOT = 0;
                shootClip.stop();
            }
        });
    }

    // INNER CLASS FOR THE TIMER
    public class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FRAME += 1;
            Control.getMousePosition(GamePanel.this);

            // ENEMY INTITAILZA/ITION AND CONFIGURATION
            enemyInt();

            // PLAYER POWER UPS CONFIGURATION
            intPlayerUps();

            // INT FRIENDLY GHOST
            intFriendlyGhost();

            // INT POWER UPS
            intPowerUps();

            // COLLISON DETECTION
            collides();

            // SCORE DEDUCTION METHOD
            penalty();

            // GAME OVER METHOD
            gameOver();

            // UPDATE PLAYER POSITION
            player.update();

            // CLEAR MEMORY(OBVIOUSLY FOR PERFORMANCE)
            clear();

            // REPAINT THE PANEL
            repaint();
            if (!gameOver == true) {
                backgroundClip.loop(-1);
                backgroundClip.start();
            } else {
                backgroundClip.stop();
                gameOverClip.loop(-1);
                gameOverClip.start();
            }
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
        // BACKGROUND CONFIGURATION
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
        // DRAW POWER UPS THAT HAVE BEEN DROPPED BY CHARLIE(THE FRIENDLY GHOST)
        for (PowerUp powerUp : powerUps) {
            powerUp.draw(g2);
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

        // DRAW GAME STATE VARIABLES
        for (LifeState heart : health) {
            heart.draw(g2);
        }
        // DRAW SCORE
        score.draw(g2);

        // DRAW GAME OVER
        if (gameOver == true) {
            ancestorFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            game_loop.stop();
            new GameOver().draw(g2);
        }

        // DRAW PLAYER STATUS
        playerState = new PlayerState(player.maxSpeed, player.NUMOFBULLETS);
        playerState.draw(g2);
    }

    // COLLISON / DELETION FUNCTION
    private void collides() {
        // COLLISON FOR ENEMY AND BULLETS AND VICE VERSA
        for (Enemy enemy : enemies) {
            for (Bullet bullet : bullets) {
                if (bullet.intersects(enemy)) {
                    updateScore(enemy.getType(), enemy.getPoints());
                    enemy.isAlive = false;
                    bullet.hit = true;
                    // THIS METHOD ADDS A SOUND OF DYING GHOST IN THE LinkedList
                    intDyingGhost();
                    for (Clip sound : ghostClips) {
                        sound.start();
                    }
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
                player.HEALTH -= 1;
                if (player.HEALTH > -1) {
                    health.removeLast();
                }
            }
        }

        // COLLISION FOR PLAYER AND POWERUPS
        for (PowerUp powerUp : powerUps) {
            if (powerUp.intersects(player)) {
                if (powerUp.powUp == PowerUps.MOVEMENTSPEED) {
                    powerUp.picked = true;
                    if (player.maxSpeed < 10) {
                        player.maxSpeed += 1;
                    }
                }
            }

            if (powerUp.intersects(player)) {
                if (powerUp.powUp == PowerUps.MULTISHOT) {
                    powerUp.picked = true;
                    if (player.NUMOFBULLETS < 3) {
                        player.NUMOFBULLETS += 1;
                    }
                }
            }
        }

        // THIS WHERE WE REMOVE THE ITEM WHEN COLLISION
        bullets.removeIf(el -> el.hit == true);
        enBullets.removeIf(el -> el.hit == true);
        enemies.removeIf(el -> el.isAlive == false);
        powerUps.removeIf(el -> el.picked == true);
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

    // HEALTH DISPLAY CONFIGURATION
    // for heart position
    int offset = 0;

    private void heartsInt() {
        for (int i = player.HEALTH; i >= 1; i--) {
            LifeState heart = new LifeState();
            heart.positionX += offset;
            health.add(heart);
            if (offset < 60) {
                offset += 50;
            }
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
        // 900
        if (FRAME % 600 == 0) {
            friends.add(new FriendGhost());
        }

        for (FriendGhost friend : friends) {
            friend.update();
        }

        for (FriendGhost friend : friends) {
            int x = (int) friend.getX();
            int y = (int) friend.getY();
            if (x > 0 && x < SCREEN_WIDTH - 100 && friend.dropped == false) {
                if (probabilty() <= 0.7 / 60) {
                    powerUps.add(friend.spawnPowUp(x, y));
                    friend.dropped = true;
                }
            }
        }
    }

    // METHOD FOR GAMEOVER CONFIGURATIOM
    private void gameOver() {
        if (player.HEALTH < 1 || player.SCORE < 0) {
            gameOver = true;
        }
    }

    // RESET WHEN PLAYER PRESS RETRY
    private void reset() {
        player.HEALTH = 3;
        player.NUMOFBULLETS = 1;
        player.SCORE = 0;
        player.maxSpeed = 4;
        clearScreen();
        heartsInt();
        System.out.println(player.HEALTH + "");
        gameOver = false;
        game_loop.restart();
    }

    // RESET SCREEN
    private void clearScreen() {
        offset = 0;
        enemies.clear();
        enBullets.clear();
        friends.clear();
        bullets.clear();
        powerUps.clear();
        health.clear();
    }

    // METHOD FOR POWER UPS CONFIGURATION
    private void intPowerUps() {
        for (PowerUp powerUp : powerUps) {
            powerUp.update();
        }
    }

    // METHOD FOR SCORING CONFIGURATION
    private void updateScore(String type, int points) {
        if (type.equals("MAGE")) {
            player.SCORE += points;
        }

        if (type.equals("NINJA")) {
            player.SCORE += points;
        }
    }

    // METHOD PROBABILITY DISTRIBUTION
    public static float probabilty() {
        Random ran = new Random();
        return ran.nextFloat();
    }

    // METHOD FOR DECREMENTING SCORE
    private void penalty() {
        final int MINIMUM = -100;
        for (Enemy enemy : enemies) {
            if (enemy.x < MINIMUM || enemy.x > SCREEN_WIDTH) {
                player.SCORE -= enemy.getPoints();
            }
        }
    }

    // METHOD FOR SOUND CONFIGURATION
    private void intSounds() {
        try {
            shootClip = Audios.getAudio(0);
            backgroundClip = Audios.getAudio(1);
            gameOverClip = Audios.getAudio(3);
        } catch (Exception e) {
            System.out.println(e + "");
        }
    }

    private void intDyingGhost() {
        try {
            ghostClips.add(Audios.getAudio(2));
        } catch (Exception e) {
            System.out.println(e + "");
        }
    }

    // MEMORY CLEANING
    private void clear() {
        final int MINIMUM = -100;
        bullets.removeIf(en -> en.y < MINIMUM || en.y > SCREEN_HEIGHT || en.x < MINIMUM || en.x > SCREEN_WIDTH);
        enemies.removeIf(en -> en.x < MINIMUM || en.x > SCREEN_WIDTH);
        enBullets.removeIf(en -> en.y < MINIMUM || en.y > SCREEN_HEIGHT || en.x < MINIMUM || en.x > SCREEN_WIDTH);
        ghostClips.removeIf(en -> en.isRunning() == false);
    }
}
