package entities.enemies;

import java.awt.*;

public abstract class Enemy extends Rectangle {
    int xSpeed = 5;
    int ySpeed = 5;
    int width = 32;
    int height = 32;

    public abstract void draw(Graphics2D g);

    // MOVEMENT OF GHOST
    public abstract void update();
}