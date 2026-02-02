package gameobjects.enemies;

import gameobjects.*;
import java.awt.*;
import java.util.*;


public class Enemy extends GameObject {

    private int health;
    private int maxHealth;
    private int speed;
    private int reward;
    private ArrayList<int[]> path;
    private int currentWaypoint;
    private boolean reachedEnd;


    public Enemy(int x, int y, ArrayList<int[]> path) {
        super(x, y, 48, 48);
        this.path = path;
        this.currentWaypoint = 0;
        this.reachedEnd = false;

        this.maxHealth = 100;
        this.health = maxHealth;
        this.speed = 2;
        this.reward = 50;
    }


    public Enemy(int x, int y, ArrayList<int[]> path, int health, int speed, int reward) {
        super(x, y, 48, 48);
        this.path = path;
        this.currentWaypoint = 0;
        this.reachedEnd = false;

        this.maxHealth = health;
        this.health = maxHealth;
        this.speed = speed;
        this.reward = reward;
    }


    public void update() {
        if (reachedEnd || currentWaypoint >= path.size()) {
            reachedEnd = true;
            return;
        }

        int[] target = path.get(currentWaypoint);
        int targetX = target[0];
        int targetY = target[1];

        int dx = targetX - getX();
        int dy = targetY - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < speed) {
            setX(targetX);
            setY(targetY);
            currentWaypoint++;
            if (currentWaypoint >= path.size()) {
                reachedEnd = true;
            }
        } else {
            setX(getX() + (int)((dx / distance) * speed));
            setY(getY() + (int)((dy / distance) * speed));
        }
    }


    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }


    public boolean isDead() {
        return health <= 0;
    }


    public boolean hasReachedEnd() {
        return reachedEnd;
    }


    public int getHealth() { return health; }

    public int getMaxHealth() { return maxHealth; }

    public int getReward() { return reward; }


    public void drawHealthBar(Graphics g) {
        int barWidth = 30;
        int barHeight = 4;
        int barX = getX() + (getWidth() - barWidth) / 2;
        int barY = getY() - 8;

        g.setColor(Color.RED);
        g.fillRect(barX, barY, barWidth, barHeight);

        int healthWidth = (int)((double)health / maxHealth * barWidth);
        g.setColor(Color.GREEN);
        g.fillRect(barX, barY, healthWidth, barHeight);

        g.setColor(Color.BLACK);
        g.drawRect(barX, barY, barWidth, barHeight);
    }


    public void setHealth(int health) {
        this.health = health;
        this.maxHealth = health;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void setReward(int reward) {
        this.reward = reward;
    }
}