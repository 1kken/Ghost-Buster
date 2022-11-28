package entities.enemies;

import java.awt.Graphics2D;
import java.util.Random;


import frame.GamePanel;

public class EnemyMage extends Enemy {
    public EnemyMage(){
        this.x = xDirect();
        this.y = yLevel();
        this.width = 32;
        this.height = 32;
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect(x, y, width, height);
    }

    @Override
    public void update() {
        x += this.xSpeed; 
    }

    //MISC FOR THIS THING ONLY
    private int randInt(){
        Random rand = new Random();
        return rand.nextInt(2);
    } 
    
    private int xDirect(){
        int origin = 0;
        if(randInt() == 1){
           origin = GamePanel.SCREEN_WIDTH; 
           this.xSpeed *= -1;
        }
        return origin;
    }

    private int yLevel(){
        int level = 0;
        if(randInt() == 1){
           level = 100; 
        }
        return level;
    }
}
