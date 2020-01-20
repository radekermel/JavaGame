package com.tutorial.game.JavaGame;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class Menu extends MouseAdapter {


    private final HUD hud;
    private final Font headerFont = new Font("arial", Font.BOLD, 50);
    private final Font menuFont = new Font("arial", Font.BOLD, 30);
    private final Font descriptionFont = new Font("arial", Font.BOLD, 20);


    public Menu(HUD hud) {
        this.hud = hud;
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
            g.setColor(Color.WHITE);
            g.drawString("SELECT DIFFICULTY", 70, 50);

            g.setFont(menuFont);
            g.setColor(Color.WHITE);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 250, 190);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 250, 290);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 250, 390);
        }
    }
}
