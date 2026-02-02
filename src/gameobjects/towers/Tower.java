package gameobjects.towers;

import gameobjects.*;
import gameobjects.enemies.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public abstract class Tower extends GameObject {

    protected int damage;
    protected int range;
    protected int fireRate;
    protected int cooldown;
    protected int cost;
    protected int level;
    protected double targetAngle;


    public Tower(int x, int y, int cost) {
        super(x, y, 64, 64);
        this.cost = cost;
        this.level = 1;
        this.cooldown = 0;
        this.targetAngle = 0;
    }


    public void update() {
        if (cooldown > 0) {
            cooldown--;
        }
    }


    public boolean canShoot() {
        return cooldown <= 0;
    }


    public void shoot(Enemy target) {
        if (canShoot()) {
            cooldown = fireRate;
        }
    }


    public Enemy findTarget(ArrayList<Enemy> enemies) {
        Enemy closest = null;
        double minDist = range;

        for (Enemy enemy : enemies) {
            double dist = distanceTo(enemy);
            if (dist <= range && dist < minDist) {
                minDist = dist;
                closest = enemy;
            }
        }

        if (closest != null) {
            int enemyCenterX = closest.getX() + closest.getWidth() / 2;
            int enemyCenterY = closest.getY() + closest.getHeight() / 2;
            int towerCenterX = x + width / 2;
            int towerCenterY = y + height / 2;
            targetAngle = Math.atan2(enemyCenterY - towerCenterY, enemyCenterX - towerCenterX);
        }

        return closest;
    }


    @Override
    public void draw(Graphics g) {
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform oldTransform = g2d.getTransform();

            int centerX = x + width / 2;
            int centerY = y + height / 2;

            g2d.rotate(targetAngle + Math.PI / 2, centerX, centerY);
            g2d.drawImage(image, x, y, width, height, null);
            g2d.setTransform(oldTransform);
        }
    }


    public void upgrade() {
        if (level < 2) {
            level = 2;
            applyUpgrade();
        }
    }


    protected abstract void applyUpgrade();


    public int getDamage() { return damage; }

    public int getRange() { return range; }

    public int getCost() { return cost; }

    public int getLevel() { return level; }

    public int getSellPrice() { return cost / 2; }

    public int getUpgradePrice() {
        if (level >= 2) {
            return -1;
        }
        return cost / 2;
    }
}