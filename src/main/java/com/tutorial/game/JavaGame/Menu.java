package com.tutorial.game.JavaGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private final Game game;
    private final Handler handler;
    private final HUD hud;

    private final Random random = new Random();
    private final Font headerFont = new Font("arial", Font.BOLD, 50);
    private final Font menuFont = new Font("arial", Font.BOLD, 30);
    private final Font descriptionFont = new Font("arial", Font.BOLD, 20);


    public Menu(Game game, Handler handler, HUD hud) {
        this.handler = handler;
        this.game = game;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (Game.gameState == Game.STATE.Menu) {
            //PlayButton
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                Game.gameState = Game.STATE.Select;
                return;
            }
            //HelpButton
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                Game.gameState = Game.STATE.Help;
            }
            //QuitButton
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(1);
            }
        }

        if (Game.gameState == Game.STATE.Select) {
            //EasyButton
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
                game.difficulty = 0;
            }
            //HardButton
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
                game.difficulty = 1;
            }
            //BackButton
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                Game.gameState = Game.STATE.Menu;
                return;
            }
        }

        //BackButton
        if (Game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                Game.gameState = Game.STATE.Menu;
                return;
            }
        }

        if (Game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                Game.gameState = Game.STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            return my > y && my < y + height;
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
            g.drawString("Press 'P' for Pause.", 220, 230);

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
        } else if (Game.gameState == Game.STATE.Select) {
            g.setFont(headerFont);
            g.setColor(Color.CYAN);
            g.drawString("SELECT DIFFICULTY", 140, 50);

            g.setFont(menuFont);
            g.setColor(Color.WHITE);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 270, 190);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 270, 290);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }
    }
}
