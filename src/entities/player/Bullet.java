package entities.player;

import java.awt.*;

import javax.swing.ImageIcon;

import frame.GamePanel;

public class Bullet extends Rectangle {
    public boolean hit = false;
    double speed = 20.0;
    double xVelocity;
    double yVelocity;
    double angle;
    Image bullet;
    public Bullet(int x, int y, int offset) {
        initBullets(x, y, offset);
    }

    public void draw(Graphics2D g) {
        g.drawImage(bullet, this.x, this.y, null);
    }

    public void update() {
        this.x += xVelocity;
        this.y += yVelocity;
    }

    //////////////////////////////////////////
    ///// MISCELLANOUS////////////////////////
    ////////////////////////////////////////
    private void initBullets(int x, int y,int offset){
        this.x = x;
        this.y = y - 32;
        this.height = 16;
        this.width = 16;
        // ARCTAN GETS THE ANGLE OF X AND Y
        angle = Math.atan2(GamePanel.aimLocY - this.y, GamePanel.aimLocX - this.x);

        // MATCH THE X AND Y VELOCITY IN ORDER TO INTERSECT THE GIVEN POINT
        // SEPARATE THE X & Y VALUES
        xVelocity = (speed) * Math.cos(angle) + offset;
        yVelocity = (speed) * Math.sin(angle);

        //SET IMAGE
        bullet = new ImageIcon(this.getClass().getResource("resource/bullet.gif")).getImage();
    }
}
