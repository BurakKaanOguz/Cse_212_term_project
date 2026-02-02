package gameobjects;

import gameobjects.enemies.*;
import java.awt.*;


public class Bullet extends GameObject {

    private Enemy target;
    private int damage;
    private int speed;
    private boolean hit;


    public Bullet(int x, int y, Enemy target, int damage) {
        super(x, y, 8, 8);
        this.target = target;
        this.damage = damage;
        this.speed = 10;
        this.hit = false;
    }


    public void update() {
        if (hit || target == null || target.isDead()) {
            hit = true;
            return;
        }

        int targetX = target.getX() + target.getWidth() / 2;
        int targetY = target.getY() + target.getHeight() / 2;

        int dx = targetX - x;
        int dy = targetY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < 15) {
            target.takeDamage(damage);
            hit = true;
            return;
        }

        if (distance > 0) {
            x += (int)((dx / distance) * speed);
            y += (int)((dy / distance) * speed);
        }
    }


    @Override
    public void draw(Graphics g) {
        if (!hit) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, width, height);
        }
    }


    public boolean shouldRemove() {
        return hit;
    }


    public int getDamage() {
        return damage;
    }
}