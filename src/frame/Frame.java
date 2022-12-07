package frame;
import java.awt.Dimension;

import javax.swing.JFrame;
public class Frame extends JFrame  {
    public Frame(){
      GamePanel gamePanel = new GamePanel();
      gamePanel.setFocusable(true);
      this.add(gamePanel);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setTitle("GHOST BUSTER");
      this.setVisible(true);
      this.pack();
      this.setLocationRelativeTo(null);
    } 
}
