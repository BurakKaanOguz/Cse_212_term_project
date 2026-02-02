package gameobjects.towers;

import gameobjects.enemies.Enemy;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;


public class PlaneTower extends Tower {

    private int planeX;
    private int planeY;
    private int targetX;
    private int targetY;
    private int planeSpeed;
    private Image planeImage;
    private double planeAngle;


    public PlaneTower(int x, int y) {
        super(x, y, 300);
        this.damage = 30;
        this.range = 999;
        this.fireRate = 90;

        this.planeX = x;
        this.planeY = y;
        this.planeSpeed = 3;
        this.planeAngle = 0;

        setRandomTarget();

        loadImage("src/assets/tower_assets/landingpad1.png");
        loadPlaneImage("src/assets/tower_assets/plane1.png");
    }


    private void loadPlaneImage(String path) {
        try {
            ImageIcon icon = new ImageIcon(path);
            planeImage = icon.getImage();
        } catch (Exception e) {
            System.out.println("no plane img");
        }
    }


    @Override
    public void update() {
        super.update();

        int dx = targetX - planeX;
        int dy = targetY - planeY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        planeAngle = Math.atan2(dy, dx);

        if (distance < planeSpeed * 2) {
            setRandomTarget();
            dx = targetX - planeX;
            dy = targetY - planeY;
            planeAngle = Math.atan2(dy, dx);
        } else {
            planeX += (int)((dx / distance) * planeSpeed);
            planeY += (int)((dy / distance) * planeSpeed);
        }
    }


    private void setRandomTarget() {
        int maxX = 1280 - width;
        int maxY = 640 - height;

        targetX = (int)(Math.random() * maxX);
        targetY = (int)(Math.random() * maxY);
    }


    @Override
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        }

        if (planeImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform oldTransform = g2d.getTransform();

            int planeCenterX = planeX + width / 2;
            int planeCenterY = planeY + height / 2;

            g2d.rotate(planeAngle, planeCenterX, planeCenterY);
            g2d.drawImage(planeImage, planeX, planeY, width, height, null);
            g2d.setTransform(oldTransform);
        }
    }


    @Override
    public Enemy findTarget(ArrayList<Enemy> enemies) {
        Enemy closest = null;
        double minDist = range;

        for (Enemy enemy : enemies) {
            int dx = enemy.getX() - planeX;
            int dy = enemy.getY() - planeY;
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist <= range && dist < minDist) {
                minDist = dist;
                closest = enemy;
            }
        }
        return closest;
    }


    public int getPlaneX() {
        return planeX;
    }


    public int getPlaneY() {
        return planeY;
    }


    @Override
    protected void applyUpgrade() {
        this.damage = 50;
        this.fireRate = 60;
        this.planeSpeed = 4;

        loadImage("src/assets/tower_assets/landingpad2.png");
        loadPlaneImage("src/assets/tower_assets/plane2.png");
    }
}

