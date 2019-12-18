package com.tutorial.game.JavaGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BossBullet extends GameObject {

    private Handler handler;
    private Random random = new Random();
    private BufferedImage ENEMY_IMAGE;

    public BossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (random.nextInt(5 - -5) + -5);
        velY = 5;
        SpriteSheet ss = new SpriteSheet((Game.SPRITE_SHEET));
        ENEMY_IMAGE = ss.grabImage(2, 2, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 28, 18);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y >= Game.HEIGHT) {
            handler.removeObject(this);
        }

        handler.addObject((new Trail((int) x, (int) y, ID.Trail, Color.red, 8, 8, 0.08f, handler)));
    }

    public void render(Graphics g) {
        g.drawImage(ENEMY_IMAGE, (int) x, (int) y, null);
    }
}
