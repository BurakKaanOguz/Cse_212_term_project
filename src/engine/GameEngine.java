package engine;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import gameobjects.towers.*;
import gameobjects.enemies.*;
import gameobjects.*;
import maps.Map;


public class GameEngine {

    private Map map;
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private WaveManager waveManager;

    private int money;
    private int health;
    private int enemiesDefeated;
    private int moneySpent;
    private boolean paused;
    private boolean gameOver;

    private long gameStartTime;
    private int gameTimeSeconds;

    private boolean doomMode;
    private AudioManager audioManager;
    private Timer gameLoop;


    public GameEngine(Map map, int difficulty) {
        this.map = map;
        this.towers = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.waveManager = new WaveManager(difficulty);

        this.money = 500;
        this.health = 100;
        this.enemiesDefeated = 0;
        this.moneySpent = 0;
        this.paused = false;
        this.gameOver = false;

        this.gameStartTime = System.currentTimeMillis();
        this.gameTimeSeconds = 0;

        this.doomMode = false;
        this.audioManager = new AudioManager();

        startGameLoop();
    }


    private void startGameLoop() {
        gameLoop = new Timer(16, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!paused && !gameOver) {
                    update();
                }
            }
        });
        gameLoop.start();
    }


    private void update() {
        long currentTime = System.currentTimeMillis();
        gameTimeSeconds = (int)((currentTime - gameStartTime) / 1000);

        waveManager.update();

        if (waveManager.shouldSpawnEnemy()) {
            spawnEnemy();
        }

        if (waveManager.isWaveComplete() && enemies.isEmpty()) {
            money += 100;
            waveManager.startNextWave();
        }

        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy enemy = enemies.get(i);
            enemy.update();

            if (enemy.hasReachedEnd()) {
                enemies.remove(i);
                health -= 10;
                if (health <= 0) {
                    health = 0;
                    gameOver = true;
                }
            }

            if (enemy.isDead()) {
                enemies.remove(i);
                enemiesDefeated++;
                money += enemy.getReward();
                audioManager.playSound("src/assets/sound/hit.wav");
            }
        }

        for (Tower tower : towers) {
            tower.update();

            if (tower.canShoot()) {
                Enemy target = tower.findTarget(enemies);
                if (target != null) {
                    if (tower instanceof PlaneTower) {
                        PlaneTower planeTower = (PlaneTower) tower;
                        bullets.add(new Bullet(planeTower.getPlaneX() + 32, planeTower.getPlaneY() + 32, target, tower.getDamage()));
                    } else {
                        bullets.add(new Bullet(tower.getX() + 32, tower.getY() + 32, target, tower.getDamage()));
                    }
                    tower.shoot(target);
                }
            }
        }

        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.update();

            if (bullet.shouldRemove()) {
                bullets.remove(i);
            }
        }
    }


    private void spawnEnemy() {
        ArrayList<int[]> path = map.getPathWaypoints();

        if (path.isEmpty()) {
            path.add(new int[] { 0, 320 });
            path.add(new int[] { 1280, 320 });
        }

        int startX = path.get(0)[0];
        int startY = path.get(0)[1];

        int enemyType = waveManager.getEnemyType();

        Enemy newEnemy = null;
        switch (enemyType) {
            case 1:
                newEnemy = new gameobjects.enemies.Enemy1(startX, startY, path);
                break;
            case 2:
                newEnemy = new gameobjects.enemies.Enemy2(startX, startY, path);
                break;
            case 3:
                newEnemy = new gameobjects.enemies.Enemy3(startX, startY, path);
                break;
            case 4:
                newEnemy = new gameobjects.enemies.Enemy4(startX, startY, path);
                break;
            default:
                newEnemy = new gameobjects.enemies.Enemy2(startX, startY, path);
        }

        enemies.add(newEnemy);
    }


    public boolean placeTower(int type, int x, int y) {
        if (!canPlaceTowerAt(x, y)) {
            return false;
        }

        int cost = getTowerCost(type);
        if (money < cost) {
            return false;
        }

        Tower tower = createTower(type, x, y);
        if (tower != null) {
            towers.add(tower);
            money -= cost;
            moneySpent += cost;
            return true;
        }
        return false;
    }


    private Tower createTower(int type, int x, int y) {
        switch (type) {
            case 0:
                return new BasicTower(x, y);
            case 1:
                return new FastTower(x, y);
            case 2:
                return new HeavyTower(x, y);
            case 3:
                return new PlaneTower(x, y);
            default:
                return null;
        }
    }


    public boolean sellTowerAt(int x, int y) {
        Tower tower = getTowerAt(x, y);
        if (tower != null) {
            money += tower.getSellPrice();
            towers.remove(tower);
            return true;
        }
        return false;
    }


    public boolean upgradeTowerAt(int x, int y) {
        Tower tower = getTowerAt(x, y);
        if (tower == null) {
            return false;
        }
        if (tower.getLevel() >= 2) {
            return false;
        }

        int cost = tower.getUpgradePrice();
        if (money >= cost) {
            tower.upgrade();
            money -= cost;
            moneySpent += cost;
            return true;
        }
        return false;
    }


    public Tower getTowerAt(int x, int y) {
        for (Tower tower : towers) {
            if (x >= tower.getX() && x < tower.getX() + 64 &&
                    y >= tower.getY() && y < tower.getY() + 64) {
                return tower;
            }
        }
        return null;
    }


    public boolean canPlaceTowerAt(int x, int y) {
        return map.canPlaceTower(x, y) && getTowerAt(x, y) == null;
    }


    public void render(Graphics g) {
        map.draw(g);

        for (Tower tower : towers) {
            tower.draw(g);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(g);
            enemy.drawHealthBar(g);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }


    public void togglePause() {
        paused = !paused;
    }


    public void stop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
    }


    public int calculateFinalScore() {
        return (enemiesDefeated * 20) + health + moneySpent;
    }


    private int getTowerCost(int type) {
        switch (type) {
            case 0:
                return 100;
            case 1:
                return 150;
            case 2:
                return 200;
            case 3:
                return 300;
            default:
                return 100;
        }
    }


    public int getMoney() {
        return money;
    }


    public int getHealth() {
        return health;
    }


    public boolean isPaused() {
        return paused;
    }


    public boolean isGameOver() {
        return gameOver;
    }


    public int getCurrentWave() {
        return waveManager.getCurrentWave();
    }


    public int getEnemiesInWave() {
        return waveManager.getEnemiesPerWave();
    }


    public int getEnemiesSpawned() {
        return waveManager.getEnemiesSpawned();
    }


    public int getEnemiesAlive() {
        return enemies.size();
    }


    public int getEnemiesDefeated() {
        return enemiesDefeated;
    }


    public int getGameTimeSeconds() {
        return gameTimeSeconds;
    }


    public String getGameTimeFormatted() {
        int minutes = gameTimeSeconds / 60;
        int seconds = gameTimeSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }


    public int getCurrentScore() {
        return (enemiesDefeated * 20) + health + moneySpent;
    }


    public void activateDoomMode() {
        if (doomMode) return;

        doomMode = true;

        ArrayList<int[]> gamePath = map.getPathWaypoints();

        final int[] spawnCount = {0};
        Timer spawnTimer = new Timer(100, null);
        spawnTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (spawnCount[0] < 20) {
                    int startX = gamePath.get(0)[0];
                    int startY = gamePath.get(0)[1];

                    Enemy doomEnemy = new gameobjects.enemies.Enemy3(startX, startY, gamePath);
                    doomEnemy.setHealth(400);
                    doomEnemy.setSpeed(4);
                    doomEnemy.setReward(200);

                    enemies.add(doomEnemy);
                    spawnCount[0]++;
                } else {
                    spawnTimer.stop();
                }
            }
        });

        spawnTimer.start();
    }


    public boolean isDoomMode() {
        return doomMode;
    }
}