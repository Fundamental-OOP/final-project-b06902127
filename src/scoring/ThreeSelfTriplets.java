package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.TripletMeld;
import tiles.KongMeld;

import java.util.ArrayList;

public class ThreeSelfTriplets extends ScoringPattern {
	public ThreeSelfTriplets() {
		super("ThreeSelfTriplets", 2, new ArrayList<String>());
	}

	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		int selfTripletCount = 0;
		for (Meld meld: winningTiles.getHiddenMelds()) {
			if (meld instanceof TripletMeld || meld instanceof KongMeld)
				selfTripletCount++;
		}
		return selfTripletCount == 3;
	}

}