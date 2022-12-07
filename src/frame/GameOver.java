package frame;

import java.awt.*;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GameOver extends JPanel {
    public GameOver() {
        this.setPreferredSize(new Dimension(GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT));
    }

    public void draw(Graphics g) {
        g.drawString("GAME OVER!", 500, 350);
    }
}
