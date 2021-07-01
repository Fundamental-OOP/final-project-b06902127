package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.BonusTile;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SevenBonuses extends ScoringPattern {
	public SevenBonuses() {
		super("SevenBonuses", 8, Arrays.asList("Flowers", "Seasons", "FlowerKong", "SeasonKong"));
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		int bonusCount = winningTiles.getRevealedBonusTiles().size();
		boolean lastBonusTile = winningTiles.getLastTile() instanceof BonusTile;
		return bonusCount==7 && lastBonusTile;
	}
}