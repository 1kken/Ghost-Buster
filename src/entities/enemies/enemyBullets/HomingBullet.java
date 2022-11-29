package entities.enemies.enemyBullets;

import java.awt.Graphics2D;

import frame.GamePanel;

public class HomingBullet extends EnemyBullet {
    public HomingBullet(int x, int y){
        this.height = 16;
        this.width = 16;
        this.x = x;
        this.y = y;
        // ARCTAN GETS THE ANGLE OF X AND Y
        angle = Math.atan2( GamePanel.player.getY()  - this.y, GamePanel.player.getX() - this.x);

        // MATCH THE X AND Y VELOCITY IN ORDER TO INTERSECT THE GIVEN POINT
        // SEPARATE THE X & Y VALUES
        this.xVelocity = (speed) * Math.cos(angle);
        this.yVelocity = (speed) * Math.sin(angle);
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect(x, y, width, height);        
    }

    @Override
    public void update() {
       this.x += xVelocity;
       this.y += yVelocity; 
    }
    
}
