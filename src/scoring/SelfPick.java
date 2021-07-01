package scoring;

import action.WinningTiles;
import tiles.WindRank;

import java.util.ArrayList;

public class SelfPick extends ScoringPattern {
	public SelfPick() {
		super("SelfPick", 1, new ArrayList<String>());
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		return isSelfPick;
	}
}