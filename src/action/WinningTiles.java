package action;

import tiles.Meld;
import tiles.Tile;

import java.util.List;

public class WinningTiles {
	private List<Meld> revealedMelds;
	private List<Meld> hiddenMelds;
	private Pair pair;
	private List<Tile> revealedBonusTiles;
	private Tile lastTile;

	public WinningTiles(List<Meld> revealedMelds, List<Meld> hiddenMelds, Pair pair, List<Tile> revealedBonusTiles, Tile lastTile) {
		this.revealedMelds = revealedMelds;
		this.hiddenMelds = hiddenMelds;
		this.pair = pair;
		this.revealedBonusTiles = revealedBonusTiles;
		this.lastTile = lastTile;
	}

	public List<Meld> getRevealedMelds() {
		return revealedMelds;
	}

	public List<Meld> getHiddenMelds() {
		return hiddenMelds;
	}

	public Pair getPair() {
		return pair;
	}

	public List<Tile> getRevealedBonusTiles() {
		return revealedBonusTiles;
	}

	public Tile getLastTile() {
		return lastTile;
	}


}