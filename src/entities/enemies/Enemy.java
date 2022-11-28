package entities.enemies;

import java.awt.*;

public abstract class Enemy extends Rectangle {
    public boolean isAlive = true;
    int xSpeed = 4;
    int ySpeed = 4;

    public abstract void draw(Graphics2D g);

    // MOVEMENT OF GHOST
    public abstract void update();
}