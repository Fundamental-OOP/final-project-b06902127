package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.BonusTile;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class EightBonuses extends ScoringPattern {
	public EightBonuses() {
		super("EightBonuses", 8, Arrays.asList("Flowers", "Seasons", "FlowerKong", "SeasonKong"));
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		int bonusCount = winningTiles.getRevealedBonusTiles().size();
		return bonusCount==8;
	}
}