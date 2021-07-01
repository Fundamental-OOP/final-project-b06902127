package action;

import player.Player;
import tiles.Tile;
import tiles.SuitedTile;
import tiles.Meld;
import tiles.SequenceMeld;
import tiles.TripletMeld;
import tiles.Type;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class WinAction extends Action {
    List<WinningTiles> allKindOfWinningTiles = new ArrayList<>();

    public WinAction(Player player) {
        super(ActionRank.WIN, player);
    }

    @Override
    public List<WinningTiles> execute(Tile tile) {
        List<Tile> playerTiles = player.getTiles();
        playerTiles.add(tile);
        Collections.sort(playerTiles);

        List<Meld> revealedMelds = player.getRevealedMelds();
        List<Meld> hiddenMelds = null;
        Pair pair = null;
        List<Tile> revealedBonusTiles = player.getRevealedBonusTiles();

        for (int i = 0; i < playerTiles.size() - 1; i++) {
            Tile t1 = playerTiles.get(i), t2 = playerTiles.get(i+1);
            if (t1.compareTo(t2) == 0) {
                playerTiles.remove(t1);
                playerTiles.remove(t2);
                hiddenMelds = new ArrayList<>();
                pair = new Pair(t1, t2);
                checkMelds(playerTiles, hiddenMelds, revealedMelds, pair, revealedBonusTiles, tile); 
                playerTiles.add(i, t1);
                playerTiles.add(i+1, t2); 
            }
        }

        return allKindOfWinningTiles;        
    }

    private void checkMelds(List<Tile> playerTiles, List<Meld> hiddenMelds, List<Meld> revealedMelds,
                            Pair pair, List<Tile> revealedBonusTiles, Tile lastTile) {
        if (playerTiles.size() == 0) {
            allKindOfWinningTiles.add(new WinningTiles(revealedMelds, hiddenMelds, pair, revealedBonusTiles, lastTile));
            return;
        }
        else if (playerTiles.size() < 3)
            return;

        Tile firstTile = playerTiles.get(0);
        int sameTileCounter = 0;
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
            Meld hiddenMeld = new TripletMeld(t1, t2, t3);
            hiddenMelds.add(hiddenMeld);
            checkMelds(playerTiles, hiddenMelds, revealedMelds, pair, revealedBonusTiles, lastTile);
            playerTiles.add(0, t1);
            playerTiles.add(1, t2);
            playerTiles.add(2, t3);
            hiddenMelds.remove(hiddenMeld);
        }

        if (firstTile.getType() == Type.Suited) {
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
                Tile pushBackTile1 = null, pushBackTile2 = null;
                for(int i = 0; i < playerTiles.size(); i++) {
                    if(!isContainT1 && !isContainT2)
                        break;

                    if(isContainT1 && playerTiles.get(i).compareTo(t1) == 0) {
                        pushBackTile1 = playerTiles.remove(i);
                        isContainT1 = false;
                        i--;
                    }
                    
                    else if (isContainT2 && playerTiles.get(i).compareTo(t2) == 0) {
                        pushBackTile2 = playerTiles.remove(i);
                        isContainT2 = false;
                        i--;
                    }                    
                }

                playerTiles.remove(firstTile);
                Meld hiddenMeld = new SequenceMeld(firstTile, pushBackTile1, pushBackTile2);
                hiddenMelds.add(hiddenMeld);
                checkMelds(playerTiles, hiddenMelds, revealedMelds, pair, revealedBonusTiles, lastTile);
                playerTiles.add(firstTile);
                playerTiles.add(pushBackTile1);
                playerTiles.add(pushBackTile2);
                Collections.sort(playerTiles);
            }                
        }
        return;
    }

    @Override
    public String toString() {
        return "Perform WinAction";
    }
}
