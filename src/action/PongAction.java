package action;

import player.Player;
import tiles.Tile;

import java.util.List;

public class PongAction extends Action {
    public PongAction(Player player, Tile t1, Tile t2) {
        super(ActionRank.PONG, player, t1, t2);
    }

    public List<WinningTiles> execute(Tile tile) {
        getCardAndDrawMelds(tile);
        return null;
    }

    @Override
    public String toString() {
        return "Perform PongAction With " + tileHolder.toString();
    }
}
