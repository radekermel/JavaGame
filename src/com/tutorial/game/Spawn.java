package com.tutorial.game;

import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Game game;
    private int scoreKeep = 0;
    private int levelKeep = 0;
    private Random randomPosition = new Random();

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 100) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            levelKeep++;
            if (levelKeep >= 1) {
                if (game.difficulty == 0) {
                    if (levelKeep % 3 == 0) {
                        handler.addObject(new FastEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                    } else if (levelKeep % 5 == 0 && levelKeep % 2 == 0) {
                        handler.clearEnemies();
                        handler.addObject(new BossEnemy((Game.WIDTH / 2) - 48, -96, ID.BossEnemy, handler));
                    } else if (levelKeep % 2 == 0) {
                        handler.addObject(new BasicEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                    }
                }
            } else if (game.difficulty == 1) {
                if (levelKeep % 3 == 0) {
                    handler.addObject(new FastEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
                } else if (levelKeep % 5 == 0 && levelKeep % 2 == 0) {
                    handler.clearEnemies();
                    handler.addObject(new BossEnemy((Game.WIDTH / 2) - 48, -96, ID.BossEnemy, handler));
                } else if (levelKeep % 2 == 0) {
                    handler.addObject(new BasicEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                }
            }
        }
    }
}
    
