package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.Tile;
import tiles.WindTile;
import tiles.Type;

import java.util.List;
import java.util.ArrayList;

public class PrevailingWind extends ScoringPattern {
	public PrevailingWind() {
		super("PrevailingWind", 1, new ArrayList<String>());
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		List<Meld> melds = new ArrayList<Meld>();
		melds.addAll(winningTiles.getRevealedMelds());
		melds.addAll(winningTiles.getHiddenMelds());
		for (Meld meld: melds) {
			boolean prevailingWindMeld = true;
			for (Tile tile: meld.getTiles()) {
				if (tile instanceof WindTile) {
					WindTile windTile = (WindTile) tile;
					if (windTile.getWindRank() != prevailingWind) {
						prevailingWindMeld = false;
						break;
					}
				}
				else {
					prevailingWindMeld = false;
					break;
				}

			}
			if (prevailingWindMeld)
				return true;
		}
		return false;
	}
}