package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Tile;
import tiles.BonusTile;
import tiles.BonusRank;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SeasonKong extends ScoringPattern {
	public SeasonKong() {
		super("SeasonKong", 2, Arrays.asList("Seasons"));
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		int seasonConut = 0;
		for (Tile tile: winningTiles.getRevealedBonusTiles()) {
			if (tile instanceof BonusTile) {
				BonusTile bonusTile = (BonusTile)tile;
				if (bonusTile.getBonusRank() == BonusRank.Spring || bonusTile.getBonusRank() == BonusRank.Summer ||
					bonusTile.getBonusRank() == BonusRank.Autumn || bonusTile.getBonusRank() == BonusRank.Winter)
					seasonConut++;
			}	
		}
		return seasonConut == 4;
	}
}