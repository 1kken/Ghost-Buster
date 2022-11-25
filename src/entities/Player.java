package entities;
import java.awt.*;
import frame.GamePanel;
public class Player extends Rectangle {
   int speed = 1;
   public Player(){
    this.x = 650 ;
    this.y = GamePanel.SCREEN_HEIGHT - 100;
    this.height = 64;
    this.width = 64;
   } 

   public void draw(Graphics2D g){
        g.fillRect(this.x, this.y, width, height);
   }

   public void update(int keyCode){
    System.out.println(keyCode+"");
        if(keyCode == 65){
            this.x -= speed;  
        }else if (keyCode == 68){
            this.x += speed;
        }
   }
}
