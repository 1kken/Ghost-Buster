package entities;

import java.awt.*;
import frame.GamePanel;

public class Player extends Rectangle {
    int speed = 0;
    int maxSpeed = 5;

    public Player() {
        this.x = 650;
        this.y = GamePanel.SCREEN_HEIGHT - 100;
        this.height = 64;
        this.width = 64;
    }

    public void draw(Graphics2D g) {
        g.fillRect(this.x, this.y, width, height);
    }

    public void update() {
        if (GamePanel.aimLocX < 650) {
            speed -= 1;
            if (speed < -maxSpeed) {
                speed = -maxSpeed;
            }
        } else {
            speed += 1;
            if (speed > maxSpeed) {
                speed = maxSpeed;
            }
        }
        this.x += speed;
        if (this.x >= GamePanel.SCREEN_WIDTH - this.width) {
            speed = 0;
            this.x = GamePanel.SCREEN_WIDTH - this.width;
        }

        if (this.x <= 0) {
            speed = 0;
            this.x = 0;
        }
    }
}
