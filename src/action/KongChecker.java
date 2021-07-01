package action;

import player.Player;
import tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class KongChecker extends ActionChecker{
    public KongChecker() {
        super(new PongChecker());
    }

    public List<Action> check(Player player, Tile tile) {
        List<Tile> tiles = player.getTiles();
        int sameTileCounter = 0;
        for (Tile t: tiles) {
            if (t.compareTo((Object) tile) == 0)
                sameTileCounter += 1;
        }
        if (sameTileCounter == 3) {
            List<Action> ret = new ArrayList<Action>();
            int lastIndexOfTile = -1;
            for (int i = 0; i < tiles.size(); i++)
                if (tiles.get(i).compareTo(tile) == 0)
                    lastIndexOfTile = i;
            ret.add(new KongAction(player, tiles.get(lastIndexOfTile), tiles.get(lastIndexOfTile-1), tiles.get(lastIndexOfTile-2)));
            ret.addAll(this.successor.check(player, tile));
            return ret;
        }
        else
            return this.successor.check(player, tile);
    }
}
