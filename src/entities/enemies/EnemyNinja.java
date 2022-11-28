package entities.enemies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import frame.GamePanel;

public class EnemyNinja extends Enemy {
    public EnemyNinja(){
        this.x = xDirect();
        this.y = yLevel();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.blue);
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
        int level = 200;
        if(randInt() == 1){
           level = 150; 
        }
        return level;
    }
    
}
