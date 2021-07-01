package scoring;

import action.WinningTiles;
import tiles.WindRank;

import java.util.ArrayList;

public class AllFromSelf extends ScoringPattern {
	public AllFromSelf() {
		super("AllFromSelf", 1, new ArrayList<String>());
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		WinFromWall winFromWall = new WinFromWall();
		return isSelfPick && winFromWall.patternMatch(winningTiles, isSelfPick, prevailingWind, playerWind);
	}
}