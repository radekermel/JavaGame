package com.tutorial.game.JavaGame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private final Random random;
    private final Handler handler;
    private final HUD hud;
    private final Spawn spawn;
    private final Menu menu;
    private final Shop shop;

    public int difficulty = 0;
    public static boolean paused = false;

    public enum STATE {
        Menu,
        Help,
        Game,
        Select,
        Shop,
        End
    }

    public static STATE gameState = STATE.Menu;

    public static BufferedImage SPRITE_SHEET;

    public Game() {
        handler = new Handler();
        hud = new HUD();
        shop = new Shop(hud, handler);
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        new Window(WIDTH, HEIGHT, "Game title", this);

        BufferedImageLoader loader = new BufferedImageLoader();
        SPRITE_SHEET = loader.loadImage("/chart.png");

        this.addMouseListener(menu);
        this.addMouseListener(shop);

        spawn = new Spawn(handler, hud, this);
        random = new Random();

        if (gameState == STATE.Game) {
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
        } else {
            for (int i = 0; i < 20; i++) {
                handler.addObject(new MenuParticle(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();
    }

    private void tick() {

        if (gameState == STATE.Game) {
            if (!paused) {
                hud.tick();
                spawn.tick();
                handler.tick();
                if (HUD.HEALTH <= 0) {
                    HUD.HEALTH = 100;
                    spawn.setLevelKeep(0);
                    spawn.setEnemyCount(0);
                    gameState = STATE.End;
                    handler.clearEnemies();
                    for (int i = 0; i < 20; i++) {
                        handler.addObject(new MenuParticle(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.MenuParticle, handler));
                    }
                }
            }
        } else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {
            menu.tick();
            handler.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (paused) {
            g.setColor(Color.WHITE);
            g.drawString("PAUSED", 200, 100);
        }

        if (gameState == STATE.Game) {
            hud.render(g);
            handler.render(g);
        } else if (gameState == STATE.Shop) {
            shop.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
            menu.render(g);
            handler.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    public static void main(String[] args) {
        new Game();
    }
}
