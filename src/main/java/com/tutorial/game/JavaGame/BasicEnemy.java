package com.tutorial.game.JavaGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {

    private final Handler handler;
    private final BufferedImage BASIC_ENEMY_IMAGE;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 5;
        velY = 5;

        SpriteSheet ss = new SpriteSheet((Game.SPRITE_SHEET));
        BASIC_ENEMY_IMAGE = ss.grabImage(1, 2, 32, 22);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 28, 18);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject((new Trail((int) x, (int) y, ID.Trail, Color.red, 8, 8, 0.08f, handler)));
    }

    public void render(Graphics g) {
        g.drawImage(BASIC_ENEMY_IMAGE, (int) x, (int) y, null);
    }
}
