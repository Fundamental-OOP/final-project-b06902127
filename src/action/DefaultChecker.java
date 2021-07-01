package action;

import player.Player;
import tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class DefaultChecker extends ActionChecker {
    public DefaultChecker() {
        super(null);
    }

    @Override
    public List<Action> check(Player player, Tile tile) {
        ArrayList<Action> ret = new ArrayList<>();
        ret.add(new DefaultAction(player));
        return ret;
    }
}
