package entities;

import java.awt.*;

import frame.GamePanel;

public class Enemy extends Rectangle {
    public Enemy(){
        this.y = locRand('y');
        this.x = locRand('x');
        this.height = 50;
        this.width = 50; 
    }

    public void draw(Graphics2D g){
        g.fillOval(this.x, this.y, this.width, this.height);
    }

    public void update(){
        this.y = locRand('y');
        this.x = locRand('x');
    }

    private int locRand(char axis) {
        int dir = 0;
        switch (axis) {
            case 'x':
                dir = (int)(Math.random()*(GamePanel.SCREEN_WIDTH-0-+1)+0); 
            case 'y':
                dir = (int)(Math.random()*(GamePanel.SCREEN_HEIGHT-0-+1)+0);  
        }
        return dir;
    }
}
