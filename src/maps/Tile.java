package maps;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;


public class Tile {

    private BufferedImage image;
    private String tilePath;
    private int tileSize;


    public Tile(String tilePath, int tileSize) {
        this.tilePath = tilePath;
        this.tileSize = tileSize;
        loadImage();
    }


    private void loadImage() {
        try {
            String fullPath = "src/assets/tile_assets/" + tilePath;
            File imageFile = new File(fullPath);
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("no tile " + tilePath);
        }
    }


    public BufferedImage getImage() {
        return image;
    }


    public int getTileSize() {
        return tileSize;
    }
}