package entities.enemies;

import java.awt.*;

import entities.enemies.enemyBullets.EnemyBullet;

public abstract class Enemy extends Rectangle {
    public boolean isAlive = true;
    public int xSpeed = 5;
    int ySpeed = 4;

    public abstract void draw(Graphics2D g);

    // MOVEMENT OF GHOST
    public abstract void update();

    // FIRE / SHOOT BULLETS
    public abstract EnemyBullet shoot(int x, int y);

    // GETTER METHOD FOR TYPE
    public abstract String getType();

    // GETTER METHOD FOR POINTS
    public abstract int getPoints();

    //GET HEALTH
    public abstract int intHit(int damage);
}