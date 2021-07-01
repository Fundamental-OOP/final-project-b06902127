package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.Tile;
import tiles.WindTile;
import tiles.Type;

import java.util.List;
import java.util.ArrayList;

public class SeatWind extends ScoringPattern {
	public SeatWind() {
		super("SeatWind", 1, new ArrayList<String>());
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		List<Meld> melds = new ArrayList<Meld>();
		melds.addAll(winningTiles.getRevealedMelds());
		melds.addAll(winningTiles.getHiddenMelds());
		for (Meld meld: melds) {
			boolean seatWindMeld = true;
			for (Tile tile: meld.getTiles()) {
				if (tile instanceof WindTile) {
					WindTile windTile = (WindTile) tile;
					if (windTile.getWindRank() != playerWind) {
						seatWindMeld = false;
						break;
					}
				}
				else {
					seatWindMeld = false;
					break;
				}

			}
			if (seatWindMeld)
				return true;
		}
		return false;
	}
}