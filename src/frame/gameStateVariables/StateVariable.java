package frame.gameStateVariables;
import java.awt.*;
public abstract class StateVariable extends Rectangle {
   public abstract void draw(Graphics2D g);
   public abstract void update();
}
