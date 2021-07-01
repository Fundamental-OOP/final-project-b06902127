package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.TripletMeld;
import tiles.KongMeld;

import java.util.ArrayList;
import java.util.Arrays;

public class FiveSelfTriplets extends ScoringPattern {
	public FiveSelfTriplets() {
		super("FiveSelfTriplets", 2, Arrays.asList("FourSelfTriplets", "ThreeSelfTriplets"));
	}

	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		int selfTripletCount = 0;
		for (Meld meld: winningTiles.getHiddenMelds()) {
			if (meld instanceof TripletMeld || meld instanceof KongMeld)
				selfTripletCount++;
		}
		return selfTripletCount == 5;
	}

}