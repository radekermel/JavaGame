package com.tutorial.game.JavaGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private HUD hud;
    private final Random random = new Random();

    final Game game;

    public MouseInput(Handler handler, Game game, HUD hud) {
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
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler,100));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy, handler,2));
                game.difficulty = 0;
            }
            //HardButton
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler,100));
                handler.clearEnemies();
                handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.HardEnemy, handler,3));
                game.difficulty = 1;
            }
            //BackButton
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                Game.gameState = Game.STATE.Menu;
                return;
            }
        }

        if(Game.gameState == Game.STATE.Game){

            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getId() == ID.Player) {
                    handler.addObject(new PlayerBullet((int) (tempObject.getX()) + 3, (int) (tempObject.getY()) + 9, ID.PlayerBullet, handler, mx, my,2));
                }
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
}
