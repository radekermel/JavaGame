package com.tutorial.game.JavaGame;

import java.awt.*;
import java.util.Random;

public class BossBullet extends GameObject {

    private Handler handler;
    private Random random = new Random();

    public BossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (random.nextInt(5 - -5) + -5);
        velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y >= Game.HEIGHT) {
            handler.removeObject(this);
        }

        handler.addObject((new Trail((int) x, (int) y, ID.Trail, Color.red, 16, 16, 0.05f, handler)));
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
