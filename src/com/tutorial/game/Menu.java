package com.tutorial.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;

    private Font headerFont = new Font("arial", 1, 50);
    private Font menuFont = new Font("arial", 1, 30);
    private Font descriptionFont = new Font("arial", 1, 20);


    public Menu(Game game, Handler handler, HUD hud) {
        this.handler = handler;
        this.game = game;
        this.hud = hud;
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

        if (game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.BasicEnemy, handler));
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
        if (Game.gameState == Game.STATE.Menu) {
            g.setFont(headerFont);
            g.setColor(Color.CYAN);
            g.drawString("Game!", 240, 50);

            g.setFont(menuFont);
            g.setColor(Color.WHITE);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);
        } else if (Game.gameState == Game.STATE.Help) {
            g.setFont(headerFont);
            g.setColor(Color.WHITE);

            g.setFont(descriptionFont);
            g.drawString("Use WSAD keys to move around and doge enemies!", 60, 200);

            g.setFont(menuFont);
            g.drawString("Help", 240, 50);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back ->", 270, 390);
        } else if (Game.gameState == Game.STATE.End) {
            g.setFont(headerFont);
            g.setColor(Color.WHITE);
            g.drawString("Game Over", 180, 50);

            g.setFont(descriptionFont);
            g.drawString("YOU DIED: " + hud.getScore(), 175, 200);

            g.setFont(menuFont);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 245, 390);
        }
    }
}
