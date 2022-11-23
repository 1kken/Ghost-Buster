package frame;
import javax.swing.*;

import java.awt.*;

public class Control {
    public static void getMousePosition(JPanel panel) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, panel);
        GamePanel.playerLocX = (int) p.getX();
        GamePanel.playerLocy = (int) p.getY();
    }
}
