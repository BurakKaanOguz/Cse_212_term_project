package gameobjects.enemies;

import java.util.*;


public class Enemy4 extends Enemy {

    public Enemy4(int x, int y, ArrayList<int[]> path) {
        super(x, y, path, 75, 3, 40);
        loadImage("src/assets/enemy_assets/enemy4.png");
    }
}
