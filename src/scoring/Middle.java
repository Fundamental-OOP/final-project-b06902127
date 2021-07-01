package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.Tile;
import tiles.SuitedTile;

import java.util.*;

public class Middle extends ScoringPattern {
	public Middle() {
		super("Middle", 1, new ArrayList<String>());
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		List<Meld> melds = new ArrayList<Meld>();
		melds.addAll(winningTiles.getRevealedMelds());
		melds.addAll(winningTiles.getHiddenMelds());
		for (Meld meld: melds) {
			List<Tile> tiles = meld.getTiles();
			List<SuitedTile> suitedTiles = new ArrayList<SuitedTile>();
			boolean suitedMeld = true;
			for (Tile tile: tiles) {
				if (tile instanceof SuitedTile)
					suitedTiles.add((SuitedTile)tile);
				else {
					suitedMeld = false;
					break;
				}
			}
			if (!suitedMeld)
				continue;

			Collections.sort(suitedTiles, new SuitedTileComparator());
			if (suitedTiles.get(1).compareTo(winningTiles.getLastTile()) == 0)
				return true;
			return false;
		}
		return false;
	}
}