package action;

import player.Player;
import tiles.Tile;

import java.util.List;

public class KongAction extends Action {
    public KongAction(Player player, Tile t1, Tile t2, Tile t3) {
        super(ActionRank.KONG, player, t1, t2, t3);
    }

    public List<WinningTiles> execute(Tile tile) {
        getCardAndDrawMelds(tile);
        return null;
    }

    @Override
    public String toString() {
        return "Perform KongAction With " + tileHolder;
    }
}
