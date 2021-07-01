package scoring;

import action.WinningTiles;
import tiles.WindRank;
import tiles.Meld;
import tiles.Tile;
import tiles.SuitedTile;

import java.util.*;

public class OneSide extends ScoringPattern {
	public OneSide() {
		super("OneSide", 1, new ArrayList<String>());
	}
	
	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		if (winningTiles.getLastTile() instanceof SuitedTile) {
			SuitedTile lastTile = (SuitedTile) winningTiles.getLastTile();
			if (lastTile.getRank() != 3 && lastTile.getRank() != 7)
				return false;
			List<Meld> melds = new ArrayList<Meld>();
			melds.addAll(winningTiles.getRevealedMelds());
			melds.addAll(winningTiles.getHiddenMelds());
			for (Meld meld: melds) {
				List<Tile> tiles = meld.getTiles();
				List<SuitedTile> suitedTiles = new ArrayList<SuitedTile>();
				boolean containLastTile = false;
				for (Tile tile: tiles) {
					if (tile instanceof SuitedTile)
						suitedTiles.add((SuitedTile)tile);
					else
						break;
					if (tile.compareTo(winningTiles.getLastTile()) == 0)
						containLastTile = true;
				}
				if (!containLastTile)
					continue;

				Collections.sort(suitedTiles, new SuitedTileComparator());
				if (suitedTiles.get(0).getRank() == 1 && suitedTiles.get(1).getRank() == 2 && suitedTiles.get(2).getRank() == 3 || 
					suitedTiles.get(0).getRank() == 7 && suitedTiles.get(1).getRank() == 8 && suitedTiles.get(2).getRank() == 9 )
					return true;
				return false;
			}
		}
		
		return false;
	}
}