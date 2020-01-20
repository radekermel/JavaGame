package com.tutorial.game.JavaGame;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends GameObject {
    final Handler handler;

    private final BufferedImage player_image;

    public Player(int x, int y, ID id, Handler handler, int health) {
        super(x, y, id, health);
        this.handler = handler;
        SpriteSheet ss = new SpriteSheet(Game.SPRITE_SHEET);
        player_image = ss.grabImage(1, 1, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp((int) x, 0, Game.WIDTH - 37);
        y = Game.clamp((int) y, 0, Game.HEIGHT - 60);

        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy
                    || tempObject.getId() == ID.FastEnemy
                    || tempObject.getId() == ID.SmartEnemy
                    || tempObject.getId() == ID.BossEnemy
                    || tempObject.getId() == ID.BossBullet
                    || tempObject.getId() == ID.HardEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(player_image, (int) x, (int) y, null);
    }
}
