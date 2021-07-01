package scoring;

import action.WinningTiles;
import tiles.WindRank;

import java.util.ArrayList;

public class WinFromWall extends ScoringPattern {
	public WinFromWall() {
		super("WinFromWall", 1, new ArrayList<String>());
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		return winningTiles.getRevealedMelds().isEmpty(); 
	}
}