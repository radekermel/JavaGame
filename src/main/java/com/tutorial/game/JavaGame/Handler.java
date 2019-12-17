package com.tutorial.game.JavaGame;

import java.awt.*;
import java.util.ArrayList;

public class Handler {

    ArrayList<GameObject> object = new ArrayList<>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    public void clearEnemies() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (Game.gameState != Game.STATE.Game) {
                object.clear();
            }

            if (tempObject.getId() != ID.Player) {
                removeObject(tempObject);
                i--;
            }
        }
    }
}
