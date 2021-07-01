package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.Tile;
import tiles.Type;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SmallDragons extends ScoringPattern {
	public SmallDragons() {
		super("SmallDragons", 4, Arrays.asList("Dragons"));
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		List<Meld> melds = new ArrayList<Meld>();
		melds.addAll(winningTiles.getRevealedMelds());
		melds.addAll(winningTiles.getHiddenMelds());
		int dragonMeldCount = 0;
		for (Meld meld: melds) {
			boolean dragonMeld = true;
			for (Tile tile: meld.getTiles()) {
				if (tile.getType() != Type.Dragon) {
					dragonMeld = false;
					break;
				}
			}
			if (dragonMeld)
				dragonMeldCount++;
		}

		boolean dragonPair = true;
		for (Tile tile: winningTiles.getPair().getTiles()) {
			if (tile.getType() != Type.Dragon)
				dragonPair = false;
		}

		return dragonMeldCount==2 && dragonPair;
	}
}