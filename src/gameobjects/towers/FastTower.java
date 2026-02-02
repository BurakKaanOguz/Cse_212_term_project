package gameobjects.towers;


public class FastTower extends Tower {

    public FastTower(int x, int y) {
        super(x, y, 150);
        this.damage = 10;
        this.range = 120;
        this.fireRate = 30;
        loadImage("src/assets/tower_assets/tower21.png");
    }


    @Override
    protected void applyUpgrade() {
        this.damage = 18;
        this.range = 140;
        this.fireRate = 20;
        loadImage("src/assets/tower_assets/tower22.png");
    }
}