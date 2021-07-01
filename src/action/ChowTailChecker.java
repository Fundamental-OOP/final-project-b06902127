package action;

import player.Player;
import tiles.SuitedTile;
import tiles.Tile;
import tiles.Type;

import java.util.ArrayList;
import java.util.List;


public class ChowTailChecker extends ActionChecker {
    public ChowTailChecker() {
        super(new KongChecker());
    }

    @Override
    public List<Action> check(Player player, Tile tile) {
        if (tile.getType() != Type.Suited)
            return this.successor.check(player, tile);

        List<Tile> tiles = player.getTiles();
        SuitedTile suitedTile = (SuitedTile)tile;

        Tile t11 = new SuitedTile(suitedTile.getSuitedType(), suitedTile.getRank()-2);
        Tile t12 = new SuitedTile(suitedTile.getSuitedType(), suitedTile.getRank()-1);


        if (checkContainsSuited(t11, t12, tiles)) {
            List<Action> ret = new ArrayList<>();
            int indexT11 = -1, indexT12 = -1;
            for (int i = 0; i < tiles.size(); i++) {
                if (t11.compareTo(tiles.get(i)) == 0)
                    indexT11 = i;
                else if (t12.compareTo(tiles.get(i)) == 0)
                    indexT12 = i;
            }
            ret.add(new ChowAction(player, tiles.get(indexT11), tiles.get(indexT12)));
            ret.addAll(this.successor.check(player, tile));
            return ret;
        }
        else
            return this.successor.check(player, tile);
    }

    private boolean checkContainsSuited(Tile t11, Tile t12, List<Tile> tiles) {
        boolean isContainT11 = false;
        boolean isContainT12 = false;
        for (Tile t: tiles) {
            if (t.compareTo(t11) == 0)
                isContainT11 = true;
            if (t.compareTo(t12) == 0)
                isContainT12 = true;
        }
        return isContainT11 && isContainT12;
    }
}
