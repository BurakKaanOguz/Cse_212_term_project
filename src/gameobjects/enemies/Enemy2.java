package gameobjects.enemies;

import java.util.*;


public class Enemy2 extends Enemy {

    public Enemy2(int x, int y, ArrayList<int[]> path) {
        super(x, y, path, 100, 2, 50);
        loadImage("src/assets/enemy_assets/enemy2.png");
    }
}
