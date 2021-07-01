package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.Tile;
import tiles.WindTile;
import tiles.Type;
import action.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SmallWinds extends ScoringPattern {
	public SmallWinds() {
		super("SmallWinds", 8, Arrays.asList("PrevailingWind", "SeatWind"));
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		List<Meld> melds = new ArrayList<Meld>();
		melds.addAll(winningTiles.getRevealedMelds());
		melds.addAll(winningTiles.getHiddenMelds());
		int windMeldCount = 0;
		for (Meld meld: melds) {
			boolean isWindMeld = true;
			for (Tile tile: meld.getTiles()) {
				if (tile instanceof WindTile)
					continue;
				else
					isWindMeld = false;

			}
			if (isWindMeld)
				windMeldCount++;
		}

		boolean isWindPair = true;
		for (Tile tile: winningTiles.getPair().getTiles()) {
			if (tile instanceof WindTile)
				continue;
			else
				isWindPair = false;
		}
		return windMeldCount==3 && isWindPair;
	}
}