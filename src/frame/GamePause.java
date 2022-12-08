package frame;

import java.awt.*;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePause extends JPanel {
    public GamePause() {
        this.setPreferredSize(new Dimension(GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT));
    }

    public void draw(Graphics g) {
        g.drawString("PAUSED", 500, 350);
        g.drawString("space to continue...", 300, 430);
    }
}