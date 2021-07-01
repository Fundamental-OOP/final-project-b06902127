package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.Tile;
import tiles.SuitedTile;
import tiles.SuitedType;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class AllOneSuit extends ScoringPattern {
	public AllOneSuit() {
		super("AllOneSuit", 8, Arrays.asList("MixedOneSuit"));
	}

	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		List<Meld> melds = new ArrayList<Meld>();
		melds.addAll(winningTiles.getRevealedMelds());
		melds.addAll(winningTiles.getHiddenMelds());
		SuitedType suitedTypePresent = null;
		List<Tile> tiles = new ArrayList<Tile>();
		for (Meld meld: melds)
			tiles.addAll(meld.getTiles());
		tiles.addAll(winningTiles.getPair().getTiles());
		for (Tile tile: tiles) {
			if (tile instanceof SuitedTile) {
				SuitedTile suitedTile = (SuitedTile)tile;
				SuitedType suitedType = suitedTile.getSuitedType();
				if (suitedTypePresent == null)
					suitedTypePresent = suitedType;
				if (suitedType != suitedTypePresent)
					return false;
			}
		}
		return true;
	}

}