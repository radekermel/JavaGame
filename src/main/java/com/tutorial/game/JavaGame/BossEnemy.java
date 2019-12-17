package com.tutorial.game.JavaGame;

import java.awt.*;
import java.util.Random;

public class BossEnemy extends GameObject {

    private Handler handler;
    private Random random = new Random();
    private int timer = 50;
    private int timer2 = 50;

    public BossEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 0;
        velY = 4;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (timer <= 0) velY = 0;
        else timer--;

        if (timer <= 0) timer2--;
        if (timer2 <= 0) {
            if (velX == 0) velX = 3;

            if (velX > 0) {
                velX += 0.005f;
            } else if (velX < 0) {
                velX -= 0.005f;
            }

            velX = Game.clamp(velX, -10, 10);

            int spawn = random.nextInt(10);
            if (spawn == 0) handler.addObject(new BossBullet((int) x, (int) y, ID.BossBullet, handler));
        }

        if (x <= 0 || x >= Game.WIDTH - 64) velX *= -1;
    }

    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int) x, (int) y, 64, 64);
    }
}
