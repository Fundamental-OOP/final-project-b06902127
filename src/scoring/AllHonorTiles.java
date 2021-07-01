package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.Tile;
import tiles.SuitedTile;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class AllHonorTiles extends ScoringPattern {
	public AllHonorTiles() {
		super("AllHonorTiles", 16, Arrays.asList("MixedOneSuit", "AllInTriplets"));
	}

	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		List<Meld> melds = new ArrayList<Meld>();
		melds.addAll(winningTiles.getRevealedMelds());
		melds.addAll(winningTiles.getHiddenMelds());
		List<Tile> tiles = new ArrayList<Tile>();
		for (Meld meld: melds)
			tiles.addAll(meld.getTiles());
		tiles.addAll(winningTiles.getPair().getTiles());
		for (Tile tile: tiles) {
			if (tile instanceof SuitedTile) {
				return false;
			}
		}
		return true;
	}

}