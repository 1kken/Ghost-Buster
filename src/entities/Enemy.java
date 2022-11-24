package entities;

import java.awt.*;

import javax.swing.ImageIcon;

import frame.GamePanel;

public class Enemy extends Rectangle {
    // GHOST DIRECTION
    int xSpeed = 10;
    int ySpeed = 10;
    Image GHOST;

    public Enemy() {
        GHOST = new ImageIcon(this.getClass().getResource("resource/ghost.png")).getImage();
        this.y = locRandY();
        this.x = locRandX();
        this.height = 50;
        this.width = 50;
    }

    public void draw(Graphics2D g) {
        g.drawImage(GHOST, this.x, this.y, null);
    }

    public void update() {
        if (this.x >= GamePanel.SCREEN_WIDTH - GHOST.getWidth(null) || this.x <= 0) {
            this.xSpeed = xSpeed * -1;
        }
        if (this.y >= GamePanel.SCREEN_HEIGHT - GHOST.getHeight(null) || this.y <= 0) {
            this.ySpeed = ySpeed * -1;
        }
        this.x += xSpeed;
        this.y += ySpeed;
    }

    private int locRandX() {
        return (int) (Math.random() * GamePanel.SCREEN_WIDTH) + GHOST.getWidth(null);
    }

    private int locRandY() {
        return (int) (Math.random() * GamePanel.SCREEN_HEIGHT) + GHOST.getHeight(null);
    }
}