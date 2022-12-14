package entities.player;

import java.awt.*;
import javax.swing.ImageIcon;
import frame.GamePanel;

public class Player extends Rectangle {
    //====PLAYER POWER UPS=======
    public int NUMOFBULLETS = 1;
    public int HEALTH = 3;
    public int SCORE = 0; 
    Image image = new ImageIcon(this.getClass().getResource("resource/player.gif")).getImage();
    int speed = 0;
    public int maxSpeed = 4;

    public Player() {
        this.x = 650;
        this.y = GamePanel.SCREEN_HEIGHT - 100;
        this.height = 64;
        this.width = 64;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image,this.x,this.y,null);
    }

    public void update() {
        if (GamePanel.aimLocX < this.getCenterX()) {
            speed -= 1;
            if (speed < -maxSpeed) {
                speed = -maxSpeed;
            }
        } else if(GamePanel.aimLocX > this.x){
            speed += 1;
            if (speed > maxSpeed) {
                speed = maxSpeed;
            }
        }else{
            speed = 0;
        }
        this.x += speed;
        if (this.x >= GamePanel.SCREEN_WIDTH - this.width) {
            speed = 0;
            this.x = GamePanel.SCREEN_WIDTH - this.width;
        }

        if (this.x <= 0) {
            speed = 0;
            this.x = 0;
        }
    }
}
