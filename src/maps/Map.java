package maps;

import java.awt.*;
import java.util.*;


public class Map {

    private String[][] visualLayer;
    private int[][] logicLayer;
    private int rows;
    private int cols;
    private int tileSize;
    private HashMap<String, Tile> tileCache;


    public Map(int rows, int cols, int tileSize) {
        this.rows = rows;
        this.cols = cols;
        this.tileSize = tileSize;
        this.visualLayer = new String[rows][cols];
        this.logicLayer = new int[rows][cols];
        this.tileCache = new HashMap<>();
    }


    public void setTile(int row, int col, String tileCode, int logicType) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            String tilePath = TileRegistry.getTilePath(tileCode);

            visualLayer[row][col] = tilePath;
            logicLayer[row][col] = logicType;

            if (!tileCache.containsKey(tilePath)) {
                tileCache.put(tilePath, new Tile(tilePath, tileSize));
            }
        }
    }


    public void draw(Graphics g) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String tilePath = visualLayer[row][col];
                if (tilePath != null && tileCache.containsKey(tilePath)) {
                    Tile tile = tileCache.get(tilePath);
                    if (tile.getImage() != null) {
                        int x = col * tileSize;
                        int y = row * tileSize;
                        g.drawImage(tile.getImage(), x, y, tileSize, tileSize, null);
                    }
                }
            }
        }
    }


    public boolean canPlaceTower(int x, int y) {
        int col = x / tileSize;
        int row = y / tileSize;

        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return logicLayer[row][col] == 0;
        }
        return false;
    }


    public boolean isPath(int x, int y) {
        int col = x / tileSize;
        int row = y / tileSize;

        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return logicLayer[row][col] == 1;
        }
        return false;
    }


    public ArrayList<int[]> getPathWaypoints() {
        ArrayList<int[]> waypoints = new ArrayList<>();

        int startRow = -1;
        int startCol = -1;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (logicLayer[row][col] == 3) {
                    startRow = row;
                    startCol = col;
                    break;
                }
            }
            if (startRow != -1) break;
        }

        if (startRow == -1) {
            return waypoints;
        }

        int centerOffset = 8;
        waypoints.add(new int[]{startCol * tileSize + centerOffset, startRow * tileSize + centerOffset});

        int currentRow = startRow;
        int currentCol = startCol;
        int lastDirection = -1;
        boolean reachedEnd = false;

        boolean[][] visited = new boolean[rows][cols];
        visited[currentRow][currentCol] = true;

        while (!reachedEnd) {
            int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            boolean foundNext = false;

            for (int dir = 0; dir < 4; dir++) {
                int nextRow = currentRow + directions[dir][0];
                int nextCol = currentCol + directions[dir][1];

                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                    int tileValue = logicLayer[nextRow][nextCol];

                    if (!visited[nextRow][nextCol] && (tileValue == 1 || tileValue == 3 || tileValue == 4)) {

                        if (lastDirection != -1 && lastDirection != dir) {
                            waypoints.add(new int[]{currentCol * tileSize + centerOffset, currentRow * tileSize + centerOffset});
                        }

                        currentRow = nextRow;
                        currentCol = nextCol;
                        visited[currentRow][currentCol] = true;
                        lastDirection = dir;
                        foundNext = true;

                        if (tileValue == 4) {
                            waypoints.add(new int[]{currentCol * tileSize + centerOffset, currentRow * tileSize + centerOffset});
                            reachedEnd = true;
                        }

                        break;
                    }
                }
            }

            if (!foundNext) {
                break;
            }
        }

        return waypoints;
    }


    public int getRows() { return rows; }

    public int getCols() { return cols; }

    public int getTileSize() { return tileSize; }

    public int[][] getLogicLayer() { return logicLayer; }
}