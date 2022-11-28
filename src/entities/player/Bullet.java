package entities.player;

import java.awt.*;

import frame.GamePanel;

public class Bullet extends Rectangle {
    public boolean hit = false;
    double speed = 10.0;
    double xVelocity;
    double yVelocity;
    double angle;

    public Bullet(int x, int y, int offset) {
        initBullets(x, y, offset);
    }

    public void draw(Graphics2D g) {
        g.fillRect(x, y, width, height);
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
        this.height = 15;
        this.width = 15;
        // ARCTAN GETS THE ANGLE OF X AND Y
        angle = Math.atan2(GamePanel.aimLocY - this.y, GamePanel.aimLocX - this.x);

        // MATCH THE X AND Y VELOCITY IN ORDER TO INTERSECT THE GIVEN POINT
        // SEPARATE THE X & Y VALUES
        xVelocity = (speed) * Math.cos(angle) + offset;
        yVelocity = (speed) * Math.sin(angle);
    }
}
