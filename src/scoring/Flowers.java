package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Tile;
import tiles.BonusTile;

import java.util.List;
import java.util.ArrayList;

public class Flowers extends ScoringPattern {
	public Flowers() {
		super("Flowers", 1, new ArrayList<String>());
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		for (Tile tile: winningTiles.getRevealedBonusTiles()) {
			if (tile instanceof BonusTile) {
				BonusTile bonusTile = (BonusTile)tile;
				if (bonusTile.getBonusRank().value() == playerWind.value())
					return true;
			}
		}
		return false;
	}
}