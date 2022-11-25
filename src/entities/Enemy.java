package entities;

import java.util.Random;
import java.awt.*;

import javax.swing.ImageIcon;

import frame.GamePanel;

public class Enemy extends Rectangle {
    // GHOST DIRECTION
    int dir = 0;
    int xSpeed = 15;
    int ySpeed = 15;
    Image GHOST_LEFT;
    Image GHOST_RIGHT;

    public Enemy() {
        GHOST_LEFT = new ImageIcon(this.getClass().getResource("resource/ghost_left.png")).getImage();
        GHOST_RIGHT = new ImageIcon(this.getClass().getResource("resource/ghost_right.png")).getImage();
        this.y = 300;
        this.x = locRandX();
        dirInt();
        this.height = 70;
        this.width = 70;
    }

    public void draw(Graphics2D g) {
        if (xSpeed < 0 || dir > 7) {
            g.drawImage(GHOST_LEFT, this.x, this.y, null);
        } else {
            g.drawImage(GHOST_RIGHT, this.x, this.y, null);
        }
    }

    // MOVEMENT OF GHOST
    public void update() {
        if (this.x >= GamePanel.SCREEN_WIDTH - GHOST_RIGHT.getWidth(null) - 50 || this.x <= 0) {
            this.xSpeed = xSpeed * -1;
        }
        if (this.y >= GamePanel.SCREEN_HEIGHT - 300 || this.y <= 0) {
            this.ySpeed = ySpeed * -1;
        }
        this.x += xSpeed;
        this.y += ySpeed;
    }

    // GENERATE NUMBER BETWEEN 300...900 inclusive
    private int locRandX() {
        return (int) ((Math.random() * (GamePanel.SCREEN_WIDTH - GHOST_LEFT.getWidth(null)) - GHOST_LEFT.getWidth(null))) + GHOST_LEFT.getWidth(null);
    }

    // GENERATE A RANDOM NUMBER FOR DIRECTION SPECIALLY ON X AXIS
    private void dirInt() {
        int dir = new Random().nextInt(11);
        if (dir < 6) {
            this.xSpeed *= -1;
        }
    }

    // private int locRandY() {
    // return (int) (Math.random() * GamePanel.SCREEN_HEIGHT - 300) +
    // GHOST.getHeight(null);
    // }
}