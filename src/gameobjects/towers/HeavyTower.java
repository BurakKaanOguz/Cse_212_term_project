package gameobjects.towers;


public class HeavyTower extends Tower {

    public HeavyTower(int x, int y) {
        super(x, y, 200);
        this.damage = 50;
        this.range = 200;
        this.fireRate = 90;
        loadImage("src/assets/tower_assets/tower31.png");
    }


    @Override
    protected void applyUpgrade() {
        this.damage = 80;
        this.range = 250;
        this.fireRate = 75;
        loadImage("src/assets/tower_assets/tower32.png");
    }
}