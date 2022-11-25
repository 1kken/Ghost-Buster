package frame;

import javax.swing.*;

import java.awt.*;

public class Control {
    public static void getMousePosition(GamePanel g) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, g);
        GamePanel.playerLocX = (int) p.getX();
        GamePanel.playerLocY = (int) p.getY();
    }
}
