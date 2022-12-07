package frame.gameStateVariables;

import java.awt.*;

public abstract class StateVariable extends Rectangle {
    int positionY = 10;

    public abstract void draw(Graphics2D g);

}
