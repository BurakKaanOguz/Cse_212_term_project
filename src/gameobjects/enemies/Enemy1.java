package gameobjects.enemies;

import java.util.*;


public class Enemy1 extends Enemy {

    public Enemy1(int x, int y, ArrayList<int[]> path) {
        super(x, y, path, 50, 4, 25);
        loadImage("src/assets/enemy_assets/enemy1.png");
    }
}
