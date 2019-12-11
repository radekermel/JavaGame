package com.tutorial.game;

import java.awt.*;

public class Player extends GameObject {

    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        if (id == ID.Player) g.setColor(Color.WHITE);
        g.fillRect(x, y, 32, 32);
    }
}
