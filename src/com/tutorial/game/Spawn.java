package com.tutorial.game;

import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    private Random randomPosition = new Random();

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 300) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() == 1) {
                handler.addObject(new BasicEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            } else if (hud.getLevel() == 2) {
                handler.addObject(new BasicEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            } else if (hud.getLevel() == 3) {
                handler.addObject(new FastEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
            } else if (hud.getLevel() == 5) {
                handler.addObject(new SmartEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
            } else if (hud.getLevel() == 6) {
                handler.addObject(new FastEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
            }else if (hud.getLevel() == 7) {
                handler.clearEnemies();
                handler.addObject(new BossEnemy((Game.WIDTH / 2) - 48, -96, ID.BossEnemy, handler));;
            }
        }
    }
}
