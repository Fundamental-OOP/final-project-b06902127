package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.TripletMeld;

import java.util.List;
import java.util.ArrayList;

public class AllInTriplets extends ScoringPattern {
	public AllInTriplets() {
		super("AllInTriplets", 4, new ArrayList<String>());
	}

	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		List<Meld> melds = new ArrayList<Meld>();
		melds.addAll(winningTiles.getRevealedMelds());
		melds.addAll(winningTiles.getHiddenMelds());
		for (Meld meld: melds) {
			if (meld instanceof TripletMeld)
				continue;
			else
				return false;
		}
		return true;
	}

}