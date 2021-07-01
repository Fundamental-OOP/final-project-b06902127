package scoring;

import action.WinningTiles;
import tiles.WindRank;

import java.util.ArrayList;

public class AllFromOthers extends ScoringPattern {
	public AllFromOthers(boolean isSelfPick) {
		super("AllFromOthers", 2, new ArrayList<String>());
		if (isSelfPick)
			super.addRelatedScoringPattern("OneShot");
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		return winningTiles.getHiddenMelds().isEmpty(); 
	}
}