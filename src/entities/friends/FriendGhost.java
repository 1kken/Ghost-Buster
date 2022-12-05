package entities.friends;

import java.awt.*;
import javax.swing.ImageIcon;

import entities.friends.powerUps.PowerUp;
import frame.GamePanel;

public class FriendGhost extends Rectangle {
    Image image;
    public boolean dropped = false;
    int SPEED = 10;
    public FriendGhost(){
        this.x = GamePanel.SCREEN_WIDTH;
        this.y = (int) GamePanel.player.getY();
        this.width = 64;
        this.height = 64;
        image = new ImageIcon(this.getClass().getResource("resource/friend.gif")).getImage();
    }

    public void draw(Graphics2D g){
        g.drawImage(image, this.x, this.y, null); 
    }
    
    public void update(){
        this.x -= SPEED;
    }

    public PowerUp spawnPowUp(int x,int y){
        return new PowerUp(x,y);
    }
}
