package com.tutorial.game.JavaGame;

import java.util.Random;

public class Spawn {
    private final Handler handler;
    private final HUD hud;
    private final Game game;
    private final Random randomPosition = new Random();
    private int scoreKeep = 0;
    private int levelKeep = 0;
    private int enemyCount = 0;

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public void setLevelKeep(int levelKeep) {
        this.levelKeep = levelKeep;
    }

    public int getLevelKeeP() {
        return levelKeep;
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 200) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            setLevelKeep(getLevelKeeP() + 1);

            if (getLevelKeeP() >= 1) {
                System.out.println(scoreKeep + " " + getLevelKeeP());
                String spawnedText = " enemy spawned: ";
                if (game.difficulty == 0) {
                    if (getLevelKeeP() % 3 != 0) {
                        handler.addObject(new FastEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler, 3));
                        setEnemyCount(getEnemyCount() + 1);
                        System.out.println(ID.FastEnemy + spawnedText + enemyCount);
                    } else if (getLevelKeeP() % 5 != 0) {
                        handler.addObject(new SmartEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler,5));
                        setEnemyCount(getEnemyCount() + 1);
                        System.out.println(ID.SmartEnemy + spawnedText + enemyCount);
                    } else if (getLevelKeeP() % 10 != 0) {
                        handler.clearEnemies();
                        handler.addObject(new BossEnemy((Game.WIDTH / 2) - 48, -96, ID.BossEnemy, handler,20));
                        setEnemyCount(getEnemyCount() + 1);
                        System.out.println(ID.BossEnemy + spawnedText + enemyCount);
                    } else {
                        handler.addObject(new BasicEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler, 2));
                        setEnemyCount(getEnemyCount() + 1);
                        System.out.println(ID.BasicEnemy + spawnedText + enemyCount);
                    }
                }
                if (game.difficulty == 1) {
                    if (getLevelKeeP() % 3 == 0) {
                        handler.addObject(new FastEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler,5));
                        setEnemyCount(getEnemyCount() + 1);
                        System.out.println(ID.FastEnemy + spawnedText + enemyCount);
                    } else if (getLevelKeeP() % 5 == 0) {
                        handler.addObject(new SmartEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler,7));
                        setEnemyCount(getEnemyCount() + 1);
                        System.out.println(ID.SmartEnemy + spawnedText + enemyCount);
                    } else if (getLevelKeeP() % 15 == 0) {
                        handler.clearEnemies();
                        handler.addObject(new BossEnemy((Game.WIDTH / 2) - 48, -96, ID.BossEnemy, handler,30));
                        setEnemyCount(getEnemyCount() + 1);
                        System.out.println(ID.BossEnemy + spawnedText + enemyCount);
                    } else {
                        handler.addObject(new HardEnemy(randomPosition.nextInt(Game.WIDTH - 50), randomPosition.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler,3));
                        setEnemyCount(getEnemyCount() + 1);
                        System.out.println(ID.HardEnemy + spawnedText + enemyCount);
                    }
                }
            }
        }
    }
}
