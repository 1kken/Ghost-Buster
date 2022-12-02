package entities.friends.powerUps;

import java.awt.*;
public abstract class PowerUps extends Rectangle {
   public abstract void draw(Graphics2D g);
   
   public abstract void update();
}
