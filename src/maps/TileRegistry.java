package maps;

import java.util.HashMap;


public class TileRegistry {

    private static HashMap<String, String> tileMap = new HashMap<>();

    static {
        tileMap.put("g11", "g/g11.png");
        tileMap.put("d11", "d/d11.png");
        tileMap.put("s11", "s/s11.png");
        tileMap.put("c11", "c/c11.png");

        tileMap.put("g", "g/g.png");
        tileMap.put("d", "d/d.png");
        tileMap.put("s", "s/s.png");
        tileMap.put("c", "c/c.png");

        tileMap.put("gtod00", "gtod/gtod00.png");
        tileMap.put("gtod01", "gtod/gtod01.png");
        tileMap.put("gtod02", "gtod/gtod02.png");
        tileMap.put("gtod10", "gtod/gtod10.png");
        tileMap.put("gtod11", "gtod/gtod11.png");
        tileMap.put("gtod12", "gtod/gtod12.png");
        tileMap.put("gtod20", "gtod/gtod20.png");
        tileMap.put("gtod21", "gtod/gtod21.png");
        tileMap.put("gtod22", "gtod/gtod22.png");

        tileMap.put("dtog00", "dtog/dtog00.png");
        tileMap.put("dtog01", "dtog/dtog01.png");
        tileMap.put("dtog02", "dtog/dtog02.png");
        tileMap.put("dtog10", "dtog/dtog10.png");
        tileMap.put("dtog11", "dtog/dtog11.png");
        tileMap.put("dtog12", "dtog/dtog12.png");
        tileMap.put("dtog20", "dtog/dtog20.png");
        tileMap.put("dtog21", "dtog/dtog21.png");
        tileMap.put("dtog22", "dtog/dtog22.png");

        tileMap.put("gtos00", "gtos/gtos00.png");
        tileMap.put("gtos01", "gtos/gtos01.png");
        tileMap.put("gtos02", "gtos/gtos02.png");
        tileMap.put("gtos10", "gtos/gtos10.png");
        tileMap.put("gtos11", "gtos/gtos11.png");
        tileMap.put("gtos12", "gtos/gtos12.png");
        tileMap.put("gtos20", "gtos/gtos20.png");
        tileMap.put("gtos21", "gtos/gtos21.png");
        tileMap.put("gtos22", "gtos/gtos22.png");

        tileMap.put("dtos00", "dtos/dtos00.png");
        tileMap.put("dtos01", "dtos/dtos01.png");
        tileMap.put("dtos02", "dtos/dtos02.png");
        tileMap.put("dtos10", "dtos/dtos10.png");
        tileMap.put("dtos11", "dtos/dtos11.png");
        tileMap.put("dtos12", "dtos/dtos12.png");
        tileMap.put("dtos20", "dtos/dtos20.png");
        tileMap.put("dtos21", "dtos/dtos21.png");
        tileMap.put("dtos22", "dtos/dtos22.png");

        tileMap.put("stod00", "stod/stod00.png");
        tileMap.put("stod01", "stod/stod01.png");
        tileMap.put("stod02", "stod/stod02.png");
        tileMap.put("stod10", "stod/stod10.png");
        tileMap.put("stod11", "stod/stod11.png");
        tileMap.put("stod12", "stod/stod12.png");
        tileMap.put("stod20", "stod/stod20.png");
        tileMap.put("stod21", "stod/stod21.png");
        tileMap.put("stod22", "stod/stod22.png");

        tileMap.put("gtoc00", "gtoc/gtoc00.png");
        tileMap.put("gtoc01", "gtoc/gtoc01.png");
        tileMap.put("gtoc02", "gtoc/gtoc02.png");
        tileMap.put("gtoc10", "gtoc/gtoc10.png");
        tileMap.put("gtoc11", "gtoc/gtoc11.png");
        tileMap.put("gtoc12", "gtoc/gtoc12.png");
        tileMap.put("gtoc20", "gtoc/gtoc20.png");
        tileMap.put("gtoc21", "gtoc/gtoc21.png");
        tileMap.put("gtoc22", "gtoc/gtoc22.png");

        tileMap.put("gtod00r", "gtod/gtod00r.png");
        tileMap.put("gtod01r", "gtod/gtod01r.png");
        tileMap.put("gtod10r", "gtod/gtod10r.png");
        tileMap.put("gtod11r", "gtod/gtod11r.png");

        tileMap.put("stod00r", "stod/stod00r.png");
        tileMap.put("stod01r", "stod/stod01r.png");
        tileMap.put("stod10r", "stod/stod10r.png");
        tileMap.put("stod11r", "stod/stod11r.png");

        tileMap.put("gtoc00r", "gtoc/gtoc00r.png");
        tileMap.put("gtoc01r", "gtoc/gtoc01r.png");
        tileMap.put("gtoc10r", "gtoc/gtoc10r.png");
        tileMap.put("gtoc11r", "gtoc/gtoc11r.png");
    }


    public static String getTilePath(String code) {
        String path = tileMap.get(code);
        if (path == null) {
            return "g/g.png";
        }
        return path;
    }


    public static void registerTile(String code, String path) {
        tileMap.put(code, path);
    }
}