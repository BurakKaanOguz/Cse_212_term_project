package engine;


public class WaveManager {

    private int currentWave;
    private int enemiesPerWave;
    private int enemiesSpawnedThisWave;
    private int framesSinceLastSpawn;
    private int spawnInterval;

    private static final int BASE_ENEMIES = 5;
    private static final int BASE_SPAWN_INTERVAL = 120;
    private static final int BASE_HEALTH = 100;
    private static final int BASE_SPEED = 2;
    private int difficulty;


    public WaveManager(int difficulty) {
        this.difficulty = difficulty;

        if (difficulty == 1) {
            this.currentWave = 1;
        } else if (difficulty == 2) {
            this.currentWave = 3;
        } else {
            this.currentWave = 5;
        }

        this.enemiesPerWave = BASE_ENEMIES + (currentWave - 1) * 3;
        this.enemiesSpawnedThisWave = 0;
        this.framesSinceLastSpawn = 0;
        this.spawnInterval = Math.max(30, BASE_SPAWN_INTERVAL - (currentWave - 1) * 10);
    }


    public void update() {
        framesSinceLastSpawn++;
    }


    public boolean shouldSpawnEnemy() {
        if (enemiesSpawnedThisWave >= enemiesPerWave) {
            return false;
        }

        if (framesSinceLastSpawn >= spawnInterval) {
            framesSinceLastSpawn = 0;
            enemiesSpawnedThisWave++;
            return true;
        }

        return false;
    }


    public boolean isWaveComplete() {
        return enemiesSpawnedThisWave >= enemiesPerWave;
    }


    public void startNextWave() {
        currentWave++;
        enemiesPerWave = BASE_ENEMIES + (currentWave - 1) * 3;
        spawnInterval = Math.max(30, BASE_SPAWN_INTERVAL - (currentWave - 1) * 10);
        enemiesSpawnedThisWave = 0;
        framesSinceLastSpawn = 0;
    }


    public int getEnemyHealth() {
        double baseHealth = BASE_HEALTH * (1 + (currentWave - 1) * 0.1);
        if (difficulty == 2) {
            return (int) (baseHealth * 1.2);
        }
        if (difficulty == 3) {
            return (int) (baseHealth * 1.4);
        }
        return (int) baseHealth;
    }


    public int getEnemySpeed() {
        int speed = BASE_SPEED + (currentWave - 1) / 3;
        if (difficulty == 3) {
            return (int) (speed * 1.2);
        }
        return speed;
    }


    public int getEnemyReward() {
        return 50 + (currentWave - 1) * 5;
    }


    public int getCurrentWave() {
        return currentWave;
    }


    public int getEnemiesSpawned() {
        return enemiesSpawnedThisWave;
    }


    public int getEnemiesPerWave() {
        return enemiesPerWave;
    }


    public int getEnemyType() {
        if (currentWave <= 2) {
            double random = Math.random();
            if (random < 0.7) {
                return 1;
            } else {
                return 2;
            }
        } else if (currentWave <= 5) {
            double rand = Math.random();
            if (rand < 0.3) {
                return 1;
            } else if (rand < 0.6) {
                return 2;
            } else if (rand < 0.8) {
                return 4;
            } else {
                return 3;
            }
        } else {
            double rand = Math.random();
            if (rand < 0.2) {
                return 1;
            } else if (rand < 0.5) {
                return 2;
            } else if (rand < 0.75) {
                return 4;
            } else {
                return 3;
            }
        }
    }
}
