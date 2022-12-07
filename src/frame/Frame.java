package frame;

import javax.swing.JFrame;
public class Frame extends JFrame  {
    GamePanel gamePanel = new GamePanel();
    GameOver gameOver = new GameOver();
    public Frame(){
      gamePanel.setFocusable(true);
      this.add(gamePanel);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setTitle("GHOST BUSTER");
      this.setVisible(true);
      this.pack();
      this.setLocationRelativeTo(null);
    }
}
