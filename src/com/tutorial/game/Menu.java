package com.tutorial.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Font fnt = new Font("arial", Font.BOLD, 50);
        Font fnt2 = new Font("arial", Font.BOLD, 30);

        g.setFont(fnt);
        g.setColor(Color.CYAN);
        g.drawString("Menu", 240, 50);

        g.setFont(fnt2);
        g.setColor(Color.WHITE);
        g.drawRect(210, 150, 200, 64);
        g.drawString("Play", 270, 190);
        g.drawRect(210, 250, 200, 64);
        g.drawString("Help", 270, 290);
        g.drawRect(210, 350, 200, 64);
        g.drawString("Quit", 270, 390);
    }


}
