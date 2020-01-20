package com.tutorial.game.JavaGame;

import java.awt.*;

import static com.tutorial.game.JavaGame.PlayerBullet.DAMAGE;

public class PowerUp extends GameObject {

    private final Handler handler;

    public PowerUp(int x, int y, ID id, Handler handler, int health) {
        super(x, y, id, health);
        this.handler = handler;
        velX = 0;
        velY = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 28, 18);
    }

    public void tick() {
        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    DAMAGE++;
                    System.out.println("Damage: " + DAMAGE);
                    handler.removeObject(this);
                }
            }
        }
    }

    public void render(Graphics g) {
        g.drawRect((int) x, (int) y, 28, 18);
    }
}
