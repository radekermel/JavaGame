package com.tutorial.game.JavaGame;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sprite;

    public SpriteSheet(BufferedImage ss) {
        this.sprite = ss;
    }

    public BufferedImage grabImage(int column, int row, int width, int height) {
        return sprite.getSubimage((row * 32) - 32, (column * 32) - 32, width, height);
    }
}
