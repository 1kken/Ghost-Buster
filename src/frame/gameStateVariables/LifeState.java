package frame.gameStateVariables;
import java.awt.*;
import javax.swing.ImageIcon;
public class LifeState extends StateVariable {
    public int positionX = 10;
    int positionY = 10;
    Image image = new ImageIcon(this.getClass().getResource("resource/heart.gif")).getImage();
    @Override
    public void draw(Graphics2D g) {
       g.drawImage(image,positionX,positionY,null); 
    }

    @Override
    public void update() {
       //no implimentation  
    }
    
}
