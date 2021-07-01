package action;

import player.Player;
import tiles.SuitedTile;
import tiles.Tile;
import tiles.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinChecker extends ActionChecker {
    public WinChecker() {
        super(new DefaultChecker());
    }

    @Override
    public List<Action> check(Player player, Tile tile) {
        List<Tile> playerTiles = player.getTiles();
        boolean isWin = false;
        playerTiles.add(tile);
        Collections.sort(playerTiles);

        for (int i = 0; i < playerTiles.size() - 1; i++) {
            Tile t1 = playerTiles.get(i), t2 = playerTiles.get(i+1);
            if (t1.compareTo(t2) == 0) {
                playerTiles.remove(t1);
                playerTiles.remove(t2);
                if (checkMelds(playerTiles)) {
                    isWin = true;
                }
                playerTiles.add(i, t1);
                playerTiles.add(i+1, t2);
            }

        }

        playerTiles.remove(tile);
        if (isWin) {
            List<Action> ret = new ArrayList<>();
            ret.add(new WinAction(player));
            ret.addAll(this.successor.check(player, tile));
            return ret;
        }
        else
            return this.successor.check(player, tile);
    }

    private boolean checkMelds(List<Tile> playerTiles) {
        if (playerTiles.size() == 0)
            return true;
        else if (playerTiles.size() < 3)
            return false;

        Tile firstTile = playerTiles.get(0);
        int sameTileCounter = 0;
        boolean isWin = false;
        for (Tile t: playerTiles)
            if (t.compareTo(firstTile) == 0)
                sameTileCounter += 1;
        if (sameTileCounter >= 3) {
            Tile t1 = playerTiles.get(0);
            Tile t2 = playerTiles.get(1);
            Tile t3 = playerTiles.get(2);
            playerTiles.remove(t1);
            playerTiles.remove(t2);
            playerTiles.remove(t3);
            if (checkMelds(playerTiles))
                isWin = true;
            playerTiles.add(0, t1);
            playerTiles.add(1, t2);
            playerTiles.add(2, t3);
        }

        if (firstTile.getType() == Type.Suited && !isWin) {
            SuitedTile suitedTile = (SuitedTile)firstTile;
            Tile t1 = new SuitedTile(suitedTile.getSuitedType(), suitedTile.getRank()+1);
            Tile t2 = new SuitedTile(suitedTile.getSuitedType(), suitedTile.getRank()+2);
            boolean isContainT1 = false, isContainT2 = false;
            for (Tile t: playerTiles) {
                if (t.compareTo(t1) == 0)
                    isContainT1 = true;
                if (t.compareTo(t2) == 0)
                    isContainT2 = true;
            }
            if (isContainT1 && isContainT2) {
                int i = 0;
                Tile pushBackTile0 = playerTiles.remove(0), pushBackTile1 = null, pushBackTile2 = null;
                while (i < playerTiles.size()) {
                    if (isContainT1 && playerTiles.get(i).compareTo(t1) == 0) {
                        pushBackTile1 = playerTiles.remove(i);
                        isContainT1 = false;
                    }
                    else if (isContainT2 && playerTiles.get(i).compareTo(t2) == 0) {
                        pushBackTile2 = playerTiles.remove(i);
                        isContainT2 = false;
                    }
                    else {
                        i += 1;
                    }
                }
                if (checkMelds(playerTiles))
                    isWin = true;
                playerTiles.add(pushBackTile0);
                playerTiles.add(pushBackTile1);
                playerTiles.add(pushBackTile2);
                Collections.sort(playerTiles);
            }
        }

        return isWin;
    }
}
