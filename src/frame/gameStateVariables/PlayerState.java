package frame.gameStateVariables;

import java.awt.Graphics2D;

import frame.GamePanel;

public class PlayerState extends StateVariable {
    int playerSpeed;
    int playerBullet;

    public PlayerState(int speed, int bullets) {
        this.playerBullet = bullets;
        this.playerSpeed = speed;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setFont(GamePanel.customSmallFont);
        g.drawString("speed:" + this.playerSpeed, 1100, this.positionY + 30);
        g.drawString("bullets:" + this.playerBullet, 1100, this.positionY + 60);
    }

}
