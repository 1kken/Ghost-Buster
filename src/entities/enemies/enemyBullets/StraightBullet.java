package entities.enemies.enemyBullets;

import java.awt.Graphics2D;

public class StraightBullet extends EnemyBullet {
    public StraightBullet(int x, int y) {
        this.yVelocity = 20.0;
        this.height = 16;
        this.width = 16;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect(x, y, width, height);
    }

    @Override
    public void update() {
        y += speed;
    }
}
