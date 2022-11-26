package entities;

import java.awt.*;

import frame.GamePanel;

public class Bullet extends Rectangle {
    int xVelocity = 5;
    int yVelocity = 5;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.height = 10;
        this.width = 10;
        if (GamePanel.playerLocX <= this.x) {
            xVelocity *= -1;
        }
    }

    public void draw(Graphics2D g) {
        g.fillRect(x, y, width, height);
    }

    public void update() {
        if(xVelocity <= 0 || xVelocity >= 0){
            this.x += xVelocity;
        }
        this.y -= yVelocity;
    }
}
