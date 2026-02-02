package gameobjects;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;


public abstract class GameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected BufferedImage image;


    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    protected void loadImage(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println("no img");
        }
    }


    public abstract void update();


    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        }
    }


    public int getX() { return x; }

    public int getY() { return y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }


    public boolean collidesWith(GameObject other) {
        return x < other.x + other.width &&
               x + width > other.x &&
               y < other.y + other.height &&
               y + height > other.y;
    }


    public double distanceTo(GameObject other) {
        int dx = (x + width/2) - (other.x + other.width/2);
        int dy = (y + height/2) - (other.y + other.height/2);
        return Math.sqrt(dx * dx + dy * dy);
    }
}