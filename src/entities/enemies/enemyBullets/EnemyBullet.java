package entities.enemies.enemyBullets;


import java.awt.*;

public abstract class EnemyBullet extends Rectangle {

    // hit detection
    public boolean hit = false;
    // Speed and direction
    double speed = 15.0;
    double xVelocity;
    double yVelocity;
    double angle;
    // position
    int x = (int) this.getX();
    int y = (int) this.getY();

    public abstract void draw(Graphics2D g);

    public abstract void update();

}
