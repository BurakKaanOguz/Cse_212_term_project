package maps;

public class Level3 {

	public static Map createMap() {
		Map map = new Map(10, 20, 64);

		String[][] visualLayout = {
				{ "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc02" },
				{ "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "gtoc12" },
				{ "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc01r", "c11", "gtoc12" },
				{ "gtoc00", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc11r", "c11", "gtoc12" },
				{ "gtoc10", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "gtoc12" },
				{ "gtoc10", "c11", "gtoc00r", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc22" },
				{ "gtoc10", "c11", "gtoc10r", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01", "gtoc01" },
				{ "gtoc10", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11", "c11" },
				{ "gtoc20", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21", "gtoc21" },
				{ "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g", "g" }
			};

		int[][] logicLayout = {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		};

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 20; col++) {
				map.setTile(row, col, visualLayout[row][col], logicLayout[row][col]);
			}
		}

		return map;
	}
}
