package com.tutorial.game.JavaGame;

import java.awt.*;

public class HUD {

    public float bounds = 0;
    public static float HEALTH = 100;
    private float colorTransitionValue = 255;
    private int score = 0;
    private int level = 1;

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds / 2));
        colorTransitionValue = HEALTH * 2;
        colorTransitionValue = Game.clamp(colorTransitionValue, 0, 255);
        score++;
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200 + (int) bounds, 32);
        g.setColor(new Color(75, (int) colorTransitionValue, 0));
        g.fillRect(15, 15, (int) HEALTH * 2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(15, 15, 200 + (int) bounds, 32);
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
        g.drawString("Space for Shop", 15, 94);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
