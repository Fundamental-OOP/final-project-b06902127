package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.SequenceMeld;

import java.util.List;
import java.util.ArrayList;

public class CommonHand extends ScoringPattern {
	public CommonHand() {
		super("CommanHand", 2, new ArrayList<String>());
	}

	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		OneShot oneShot = new OneShot();
		OneSide oneSide = new OneSide();
		Middle middle = new Middle();
		if (isSelfPick ||
			oneShot.patternMatch(winningTiles, isSelfPick, prevailingWind, playerWind) ||
			oneSide.patternMatch(winningTiles, isSelfPick, prevailingWind, playerWind) ||
			middle.patternMatch(winningTiles, isSelfPick, prevailingWind, playerWind))
			return false;

		List<Meld> melds = new ArrayList<Meld>();
		melds.addAll(winningTiles.getRevealedMelds());
		melds.addAll(winningTiles.getHiddenMelds());
		for (Meld meld: melds) {
			if (meld instanceof SequenceMeld)
				continue;
			else
				return false;
		}
		return true;
	}

}