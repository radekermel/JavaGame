package com.tutorial.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;


    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            //PlayButton
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemies();
            }
            //HelpButton
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = Game.STATE.Help;
            }
            //QuitButton
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(1);
            }
        }
        //BackButton
        if (game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Menu;
                return;
            }
        }
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
        if (game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);

            g.setFont(fnt);
            g.setColor(Color.CYAN);
            g.drawString("Game!", 240, 50);

            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);
        } else if (game.gameState == Game.STATE.Help) {
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);
            Font fnt3 = new Font("arial", Font.BOLD, 20);

            g.setFont(fnt);
            g.setColor(Color.WHITE);

            g.setFont(fnt3);
            g.drawString("Use WSAD keys to move around and doge enemies!", 60, 200);

            g.setFont(fnt2);
            g.drawString("Help", 240, 50);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back ->", 270, 390);
        }
    }
}
