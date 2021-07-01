package action;

import player.Player;
import tiles.Meld;
import tiles.SequenceMeld;
import tiles.TripletMeld;
import tiles.KongMeld;
import tiles.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Action implements Comparable{
    protected ActionRank actionRank;
    protected Player player;
    protected List<Tile> tileHolder = new ArrayList<>();

    public abstract List<WinningTiles> execute(Tile tile);

    public Action(ActionRank actionRank, Player player, Tile... tileList) {
        this.actionRank = actionRank;
        this.player = player;
        this.tileHolder.addAll(Arrays.asList(tileList));
    }

    protected void getCardAndDrawMelds(Tile tile) {
        for (Tile t: tileHolder)
            player.getTiles().remove(t);
        List<Tile> tiles = new ArrayList<>();
        tiles.addAll(tileHolder);
        tiles.add(tile);
        Collections.sort(tiles);
        if (tiles.size() == 3) {
            if(tiles.get(0).compareTo(tiles.get(1)) == 0)
                player.addMeld(new TripletMeld(tiles.get(0), tiles.get(1), tiles.get(2)));
            else
                player.addMeld(new SequenceMeld(tiles.get(0), tiles.get(1), tiles.get(2)));
        }
        else if (tiles.size() == 4)
            player.addMeld(new KongMeld(tiles.get(0), tiles.get(1), tiles.get(2), tiles.get(3)));
        else
            assert false : "error in action";
    }

    public List<Tile> getTileHolder() {
        return tileHolder;
    }

    public ActionRank getActionRank() {
        return actionRank;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public int compareTo(Object o) {
        return actionRank.value() - ((Action) o).getActionRank().value();
    }
}
