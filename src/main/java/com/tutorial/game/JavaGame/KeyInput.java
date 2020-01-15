package com.tutorial.game.JavaGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class KeyInput extends KeyAdapter {

    private final Handler handler;
    private final boolean[] keyDown = new boolean[4];

    final Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        Arrays.fill(keyDown, false);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-handler.speed);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(handler.speed);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(handler.speed);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-handler.speed);
                    keyDown[3] = true;
                }
            }
        }
        if (key == KeyEvent.VK_P) {
            if (Game.gameState == Game.STATE.Game) {
                Game.paused = !Game.paused;
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
        if (key == KeyEvent.VK_SPACE) {
            if (Game.gameState == Game.STATE.Game) {
                Game.gameState = Game.STATE.Shop;
            } else if (Game.gameState == Game.STATE.Shop) {
                Game.gameState = Game.STATE.Game;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) keyDown[0] = false;
                if (key == KeyEvent.VK_S) keyDown[1] = false;
                if (key == KeyEvent.VK_D) keyDown[2] = false;
                if (key == KeyEvent.VK_A) keyDown[3] = false;

                if (!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);  //vertical value
                if (!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);  //horizontal value
            }
        }
    }
}
