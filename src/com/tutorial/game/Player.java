package com.tutorial.game;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();

    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        if (id == ID.Player) g.setColor(Color.WHITE);
        else if (id == ID.Player2) g.setColor(Color.BLUE);
        g.fillRect(x, y, 32, 32);
    }
}
