package entities.enemies;

import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.ImageIcon;

import java.awt.*;

import entities.enemies.enemyBullets.EnemyBullet;
import entities.enemies.enemyBullets.StraightBullet;
import frame.GamePanel;

public class EnemyNinja extends Enemy {
    Image image = new ImageIcon(this.getClass().getResource("resource/ghostAssasin.gif")).getImage();
    public EnemyNinja() {
        this.xSpeed = 5;
        this.x = xDirect();
        this.y = yLevel();
        this.width = 32;
        this.height = 32;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, null, null);        
    }

    @Override
    public void update() {
        x += this.xSpeed;
    }

    // MISC FOR THIS THING ONLY
    private int randInt() {
        Random rand = new Random();
        return rand.nextInt(2);
    }

    private int xDirect() {
        int origin = 0;
        if (randInt() == 1) {
            origin = GamePanel.SCREEN_WIDTH;
            this.xSpeed *= -1;
        }
        return origin;
    }

    private int yLevel() {
        int level = 200;
        if (randInt() == 1) {
            level = 150;
        }
        return level;
    }

    @Override
    public EnemyBullet shoot(int x, int y) {
        return new StraightBullet(x, y);
    }

}
