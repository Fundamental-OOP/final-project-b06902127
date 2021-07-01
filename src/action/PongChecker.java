package action;

import player.Player;
import tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class PongChecker extends ActionChecker{
    public PongChecker() {
        super(new WinChecker());
    }

    @Override
    public List<Action> check(Player player, Tile tile) {
        List<Tile> tiles = player.getTiles();
        int sameTileCounter = 0;
        for (Tile t: tiles) {
            if (t.compareTo((Object) tile) == 0)
                sameTileCounter += 1;
        }
        if (sameTileCounter >= 2) {
            List<Action> ret = new ArrayList<Action>();
            int lastIndexOfTile = -1;
            for (int i = 0; i < tiles.size(); i++)
                if (tiles.get(i).compareTo(tile) == 0)
                    lastIndexOfTile = i;
            ret.add(new PongAction(player, tiles.get(lastIndexOfTile), tiles.get(lastIndexOfTile-1)));
            ret.addAll(this.successor.check(player, tile));
            return ret;
        }
        else {
            return this.successor.check(player, tile);
        }
    }
}
