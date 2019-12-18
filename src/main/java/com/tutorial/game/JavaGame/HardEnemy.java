package com.tutorial.game.JavaGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class HardEnemy extends GameObject {

    private Handler handler;
    private Random random = new Random();

    private BufferedImage ENEMY_IMAGE;

    public HardEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 5;
        velY = 5;

        SpriteSheet ss = new SpriteSheet((Game.SPRITE_SHEET));
        ENEMY_IMAGE = ss.grabImage(1, 4, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 28, 18);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32) {
            if (velY < 0) velY = (random.nextInt(7) + 1);
            else velY = (random.nextInt(7) + 1) * -1;
        }

        if (x <= 0 || x >= Game.WIDTH - 16) {
            if (velX < 0)
                velX = (random.nextInt(7) + 1);
            else velX = (random.nextInt(7) + 1) * -1;
        }

        handler.addObject((new Trail((int) x, (int) y, ID.Trail, Color.YELLOW, 8, 8, 0.08f, handler)));
    }

    public void render(Graphics g) {
        g.drawImage(ENEMY_IMAGE, (int) x, (int) y, null);
    }
}
