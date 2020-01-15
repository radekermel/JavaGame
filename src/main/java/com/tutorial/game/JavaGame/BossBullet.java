package com.tutorial.game.JavaGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BossBullet extends GameObject {

    private final Handler handler;
    private final BufferedImage BOSS_BULLET_IMAGE;

    public BossBullet(int x, int y, ID id, Handler handler, int health) {
        super(x, y, id, health);
        this.handler = handler;
        Random random = new Random();
        velX = (random.nextInt(5 - -5) + -5);
        velY = 5;
        SpriteSheet ss = new SpriteSheet((Game.SPRITE_SHEET));
        BOSS_BULLET_IMAGE = ss.grabImage(2, 2, 32, 22);
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

        handler.addObject((new Trail((int) x, (int) y, ID.Trail, Color.red, 8, 8, 0.08f, handler,1)));
    }

    public void render(Graphics g) {
        g.drawImage(BOSS_BULLET_IMAGE, (int) x, (int) y, null);
    }
}
