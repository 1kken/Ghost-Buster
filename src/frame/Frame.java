package frame;
import javax.swing.JFrame;
public class Frame extends JFrame  {
    public Frame(){
      add(new GamePanel());
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setTitle("GHOST BUSTER");
      setVisible(true);
      pack();
      this.setLocationRelativeTo(null);
    } 
}
