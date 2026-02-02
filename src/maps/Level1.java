package maps;


public class Level1 {

    public static Map createMap() {
        Map map = new Map(10, 20, 64);

        String[][] visualLayout = {
            {"gtod10", "d11", "gtod12", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "gtod10", "d11", "gtod12"},
            {"gtod10", "d11", "gtod12", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "gtod10", "d11", "gtod12"},
            {"gtod10", "d11", "gtod12", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "gtod10", "d11", "gtod12"},
            {"gtod10", "d11", "gtod12", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "gtod10", "d11", "gtod12"},
            {"gtod10", "d11", "gtod12", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "gtod10", "d11", "gtod12"},
            {"gtod10", "d11", "gtod12", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "gtod10", "d11", "gtod12"},
            {"gtod10", "d11", "gtod12", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "gtod10", "d11", "gtod12"},
            {"gtod10", "d11", "gtod10r", "gtod01", "gtod01", "gtod01", "gtod01", "gtod01", "gtod01", "gtod01", "gtod01", "gtod01", "gtod01", "gtod01", "gtod01", "gtod01", "gtod01", "gtod11r", "d11", "gtod12"},
            {"gtod10", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "d11", "gtod12"},
            {"gtod20", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod21", "gtod22"}
        };

        int[][] logicLayout = {
            {0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 20; col++) {
                map.setTile(row, col, visualLayout[row][col], logicLayout[row][col]);
            }
        }

        return map;
    }
}