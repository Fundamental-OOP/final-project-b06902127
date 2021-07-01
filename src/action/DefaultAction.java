package action;

import player.Player;
import tiles.Tile;

import java.util.List;

public class DefaultAction extends Action {
    public DefaultAction(Player player) {
        super(ActionRank.DEFAULT, player);
    }

    public List<WinningTiles> execute(Tile tile) {
        return null;
    }

    @Override
    public String toString() {
        return "DefaultAction";
    }
}
