package gameobjects.towers;


public class BasicTower extends Tower {

    public BasicTower(int x, int y) {
        super(x, y, 100);
        this.damage = 20;
        this.range = 150;
        this.fireRate = 60;
        loadImage("src/assets/tower_assets/tower11.png");
    }


    @Override
    protected void applyUpgrade() {
        this.damage = 35;
        this.range = 180;
        this.fireRate = 45;
        loadImage("src/assets/tower_assets/tower12.png");
    }
}