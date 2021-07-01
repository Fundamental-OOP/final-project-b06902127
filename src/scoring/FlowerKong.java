package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Tile;
import tiles.BonusTile;
import tiles.BonusRank;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class FlowerKong extends ScoringPattern {
	public FlowerKong() {
		super("FlowerKong", 2, Arrays.asList("Flowers"));
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		int flowerConut = 0;
		for (Tile tile: winningTiles.getRevealedBonusTiles()) {
			if (tile instanceof BonusTile) {
				BonusTile bonusTile = (BonusTile)tile;
				if (bonusTile.getBonusRank() == BonusRank.PlumBlossom || bonusTile.getBonusRank() == BonusRank.Orchid ||
					bonusTile.getBonusRank() == BonusRank.Chrysanthemum || bonusTile.getBonusRank() == BonusRank.Bamboo)
					flowerConut++;
			}	
		}
		return flowerConut == 4;
	}
}