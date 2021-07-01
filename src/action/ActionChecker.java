package action;

import player.Player;
import tiles.Tile;

import java.util.List;

public abstract class ActionChecker {
    protected ActionChecker successor;
    public ActionChecker(ActionChecker successor) {
        this.successor = successor;
    }

    public abstract List<Action> check(Player player, Tile tile);
}
