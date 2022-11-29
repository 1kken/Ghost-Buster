package entities.enemies.enemyBullets;

import java.awt.Graphics2D;

import java.awt.*;

import javax.swing.ImageIcon;

import frame.GamePanel;

public class HomingBullet extends EnemyBullet {
    Image bullet;
    public HomingBullet(int x, int y){
        this.height = 16;
        this.width = 16;
        this.x = x;
        this.y = y;
        // ARCTAN GETS THE ANGLE OF X AND Y
        angle = Math.atan2( GamePanel.player.getY()  - this.y, GamePanel.player.getX() - this.x);

        // MATCH THE X AND Y VELOCITY IN ORDER TO INTERSECT THE GIVEN POINT
        // SEPARATE THE X & Y VALUES
        this.xVelocity = (speed) * Math.cos(angle);
        this.yVelocity = (speed) * Math.sin(angle);

        bullet = new ImageIcon(this.getClass().getResource("resource/homingbullet.gif")).getImage();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(bullet, this.x, this.y, null);       
    }

    @Override
    public void update() {
       this.x += xVelocity;
       this.y += yVelocity; 
    }
    
}
