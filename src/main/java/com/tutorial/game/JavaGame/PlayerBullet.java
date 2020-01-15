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
    private int bossKillCount = 0;

    public PlayerBullet(int x, int y, ID id, Handler handler, int mx, int my, int health) {
        super(x, y, id, health);
        this.handler = handler;

        velX = (mx - x) / 10;
        velY = (my - y) / 10;

        SpriteSheet ss = new SpriteSheet((Game.SPRITE_SHEET));
        BOSS_BULLET_IMAGE = ss.grabImage(2, 2, 32, 22);
    }

    public int getBossKillCount() {
        return bossKillCount;
    }

    public void setBossKillCount(int bossKillCount) {
        this.bossKillCount = bossKillCount;
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
                    System.out.println(tempObject.id + "health: " + tempObject.getHealth());
                    if (tempObject.getHealth() == 0) {
                        if (tempObject.getId() == ID.BossEnemy) {
                            setBossKillCount(getBossKillCount()+1);
                        }
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
