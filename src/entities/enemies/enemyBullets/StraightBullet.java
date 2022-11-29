package entities.enemies.enemyBullets;

import java.awt.Graphics2D;
import java.awt.*;

import javax.swing.ImageIcon;

public class StraightBullet extends EnemyBullet {
    Image bullet;
    public StraightBullet(int x, int y) {
        this.yVelocity = 20.0;
        this.height = 16;
        this.width = 16;
        this.x = x;
        this.y = y;

        //SET BULLET image
        bullet = new ImageIcon(this.getClass().getResource("resource/straightbullet.gif")).getImage();
    }

    @Override
    public void draw(Graphics2D g) {
         g.drawImage(bullet, this.x, this.y, null);    
    }

    @Override
    public void update() {
        y += speed;
    }
}
