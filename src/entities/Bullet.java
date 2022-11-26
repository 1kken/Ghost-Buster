package entities;

import java.awt.*;

import frame.GamePanel;

public class Bullet extends Rectangle {
    double speed = 10.0;
    double xVelocity;
    double yVelocity;
    double angle;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.height = 10;
        this.width = 10;
        // calculate the distance between mouse and origin
        angle = Math.atan2(GamePanel.aimLocY - this.y, GamePanel.aimLocX - this.x);
        xVelocity = (speed) * Math.cos(angle);
        yVelocity = (speed) * Math.sin(angle);
    }

    public void draw(Graphics2D g) {
        g.fillRect(x, y, width, height);
    }

    public void update() {
        this.x += xVelocity;
        this.y += yVelocity;
    }
}
