package gameobjects.enemies;

import java.util.*;


public class Enemy3 extends Enemy {

    public Enemy3(int x, int y, ArrayList<int[]> path) {
        super(x, y, path, 200, 1, 100);
        loadImage("src/assets/enemy_assets/enemy3.png");
    }
}
