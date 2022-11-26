package frame;

import javax.swing.*;

import java.awt.*;

public class Control {
    public static void getMousePosition(GamePanel g) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, g);
        GamePanel.aimLocX = (int) p.getX();
        GamePanel.aimLocY = (int) p.getY();
    }
}
