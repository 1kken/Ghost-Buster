package frame.gameStateVariables;

import java.awt.Graphics2D;

import frame.GamePanel;

public class Score extends StateVariable {

    @Override
    public void draw(Graphics2D g) {
        g.drawString(GamePanel.player.SCORE + "", 600, this.positionY + 60);
    }

}
