package entities.friends;

import java.awt.*;

public class FriendGhost extends Rectangle {
    int SPEED = 10;
    public FriendGhost(){
        this.x = 0;
        this.y = 150;
        this.width = 64;
        this.height = 64;
    }

    public void draw(Graphics2D g){
        g.fillOval(x, y, width, height);
    }
    
    public void update(){
        this.x += SPEED;
    }
}
