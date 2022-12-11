package entities.enemies;

import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.ImageIcon;

import java.awt.*;

import entities.enemies.enemyBullets.EnemyBullet;
import entities.enemies.enemyBullets.StraightBullet;
import frame.GamePanel;

public class EnemyNinja extends Enemy {
    Image image = new ImageIcon(this.getClass().getResource("resource/ghostNinja_right.gif")).getImage();
    int POINTS = 150;
    int HEALTH = 20;
    String TYPE = "NINJA";
    public EnemyNinja(int level) {
        this.xSpeed += 1 + level;
        this.x = xDirect();
        this.y = yLevel();
        this.width = 120;
        this.height = 120;
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
        int dir = randInt();
        if ( dir == 1) {
            origin = GamePanel.SCREEN_WIDTH;
            image = new ImageIcon(this.getClass().getResource("resource/ghostNinja_left.gif")).getImage();
            this.xSpeed *= -1;
        }
        return origin;
    }

    private int yLevel() {
        Random rand = new Random();
        int level = 0;
        while(level < 201){
            level = rand.nextInt(400);
        }
        return level;
    }

    @Override
    public EnemyBullet shoot(int x, int y) {
        return new StraightBullet(x, y);
    }

    @Override
    public String getType() {
        return this.TYPE;
    }

    @Override
    public int getPoints() {
        return this.POINTS;
    }

    @Override
    public int intHit(int damage) {
        this.HEALTH -= damage;
        return HEALTH; 
    }

}
