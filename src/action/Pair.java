package action;

import tiles.Tile;

import java.util.List;
import java.util.ArrayList;

public class Pair {
	private List<Tile> tiles = new ArrayList<>();

	public Pair(Tile t1, Tile t2) {
		tiles.add(t1);
		tiles.add(t2);
	}

	public List<Tile> getTiles() {
		return tiles;
	}

}