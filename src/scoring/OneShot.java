package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Tile;

import java.util.List;
import java.util.ArrayList;

public class OneShot extends ScoringPattern {
	public OneShot() {
		super("OneShot", 1, new ArrayList<String>());
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		for (Tile tile: winningTiles.getPair().getTiles()) {
			if (tile.compareTo(winningTiles.getLastTile()) == 0)
				return true;
		}
		return false;
	}
}