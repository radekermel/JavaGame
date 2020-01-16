package com.tutorial.game.JavaGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class PlayerBullet extends GameObject {

    private static final Set<ID> ENEMIES = Collections.unmodifiableSet(EnumSet.of(ID.BasicEnemy, ID.BossEnemy, ID.SmartEnemy, ID.HardEnemy, ID.FastEnemy));
    private final Handler handler;
    private final BufferedImage BOSS_BULLET_IMAGE;

    public PlayerBullet(int x, int y, ID id, Handler handler, int mx, int my, int health) {
        super(x, y, id, health);
        this.handler = handler;


        float bulletVelocity = 10.0f;
        float angle = (float) Math.atan2(mx - x, my - y);
        velY = (float) ((bulletVelocity) * Math.cos(angle));
        velX = (float) ((bulletVelocity) * Math.sin(angle));

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

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (ENEMIES.contains(tempObject.getId())) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    tempObject.setHealth(tempObject.getHealth() - 1);
                    handler.removeObject(this);
                    if (tempObject.getHealth() == 0) {
                        handler.removeObject(tempObject);
                    }
                }
            }
        }

        handler.addObject((new Trail((int) x, (int) y, ID.Trail, Color.red, 8, 8, 0.02f, handler, 1)));
    }

    public void render(Graphics g) {
        g.drawImage(BOSS_BULLET_IMAGE, (int) x, (int) y, null);
    }
}
