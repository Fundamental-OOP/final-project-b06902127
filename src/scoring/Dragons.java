package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.Tile;
import tiles.Type;

import java.util.List;
import java.util.ArrayList;

public class Dragons extends ScoringPattern {
	public Dragons() {
		super("Dragons", 1, new ArrayList<String>());
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		List<Meld> melds = new ArrayList<Meld>();
		melds.addAll(winningTiles.getRevealedMelds());
		melds.addAll(winningTiles.getHiddenMelds());
		for (Meld meld: melds) {
			boolean dragonMeld = true;
			for (Tile tile: meld.getTiles()) {
				if (tile.getType() != Type.Dragon) {
					dragonMeld = false;
					break;
				}
			}
			if (dragonMeld)
				return true;
		}
		return false;
	}
}