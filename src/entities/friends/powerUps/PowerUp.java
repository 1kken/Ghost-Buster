package entities.friends.powerUps;

import utils.PowerUps;
import java.util.Random;
import java.awt.*;
import javax.swing.ImageIcon;

import frame.GamePanel;

public class PowerUp extends Rectangle {
   public PowerUps powUp;
   Image image;

   public PowerUp(int x,int y) {
      powUp = whatPower();
      this.x = x;
      this.y = y + 10;
      this.width = 32;
      this.height =32;
   }

   public void draw(Graphics2D g) {
      imageUpdate();
      g.drawImage(image,x,y,null);
   }

   public void update() {
      if(y != GamePanel.player.getY()){
         y--;
      }
   }

   // MISCELLANOUS
   private PowerUps whatPower() {
      Random ran = new Random();
      PowerUps randomPowerUp = PowerUps.MOVEMENTSPEED;
      int randomSelector = ran.nextInt(2);
      if (randomSelector == 0) {
         randomPowerUp = PowerUps.MULTISHOT;
      }
      return randomPowerUp;
   }

   private void imageUpdate(){
      if(powUp == PowerUps.MOVEMENTSPEED){
         image = new ImageIcon(this.getClass().getResource("resource/movementspeed.png")).getImage();    
      }
      if(powUp == PowerUps.MULTISHOT){
         image = new ImageIcon(this.getClass().getResource("resource/multishot.png")).getImage();    
      }
   }
}
