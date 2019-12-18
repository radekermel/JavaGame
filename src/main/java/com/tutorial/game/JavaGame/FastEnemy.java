package com.tutorial.game.JavaGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FastEnemy extends GameObject {

    private Handler handler;
    private BufferedImage FAST_ENEMY_IMAGE;

    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 2;
        velY = 9;

        SpriteSheet ss = new SpriteSheet((Game.SPRITE_SHEET));
        FAST_ENEMY_IMAGE = ss.grabImage(1, 3, 28, 18);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject((new Trail((int) x, (int) y, ID.Trail, Color.CYAN, 8, 8, 0.08f, handler)));
    }

    public void render(Graphics g) {
        g.drawImage(FAST_ENEMY_IMAGE, (int) x, (int) y, null);
    }
}
