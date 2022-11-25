package frame;
import javax.swing.JFrame;
public class Frame extends JFrame  {
    public Frame(){
      GamePanel gamePanel = new GamePanel();
      gamePanel.setFocusable(true);
      add(gamePanel);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setTitle("GHOST BUSTER");
      setVisible(true);
      pack();
      setLocationRelativeTo(null);
    } 
}
