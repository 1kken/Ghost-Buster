package entities.enemies;

import java.awt.*;

public abstract class Enemy extends Rectangle {
    int xSpeed = 3;
    int ySpeed = 3;

    public abstract void draw(Graphics2D g);

    // MOVEMENT OF GHOST
    public abstract void update();
}