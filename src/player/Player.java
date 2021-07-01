package player;

import action.Action;
import input.CommandLineInput;
import input.GuiInterface;
import input.UserInputInterface;
import tiles.Meld;
import tiles.Tile;
import tiles.Type;
import tiles.WindRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private int id;
    private List<Tile> tiles;
    private WindRank wind;
    private List<Meld> revealedMelds;
    private List<Tile> revealedBonusTiles;
    private int money;

    public Player() {
        this.tiles = new ArrayList<>();
        this.revealedMelds = new ArrayList<Meld>();
        this.revealedBonusTiles = new ArrayList<Tile>();
        this.money = 0;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void sortTiles() {
        Collections.sort(tiles);
    }

    public void clearAllTiles() {
        this.tiles = new ArrayList<>();
        this.revealedMelds = new ArrayList<Meld>();
        this.revealedBonusTiles = new ArrayList<Tile>();        
    }

    public void draw(Tile tile) {
        tiles.add(tile);
        Collections.sort(tiles);
    }

    public Tile discard() {
        //TODO
        UserInputInterface ui = new CommandLineInput();
        int index = ui.chooseCardToDiscard(tiles);
        return tiles.remove(index);
    }

    public Tile discard(List<Meld> meldList2, List<Meld> meldList3, List<Meld> meldList4) {
        int index = GuiInterface.chooseTileToDiscard(tiles, revealedMelds, meldList2, meldList3, meldList4, id);
        return tiles.remove(index);
    }

    public Action chooseAction(List<Action> actions) {
        //TODO
        UserInputInterface ui = new CommandLineInput();
        int index = ui.chooseActionToExecution(actions);
        return actions.get(index);
    }

    public Action chooseAction(List<Action> actions, List<Meld> meldList2, List<Meld> meldList3, List<Meld> meldList4, Tile tile) {
        int index = GuiInterface.chooseActionToExecution(tiles, revealedMelds, meldList2, meldList3, meldList4, actions, tile, id);
        return actions.get(index);
    }

    public int revealBonusTiles() {
        ArrayList<Integer> bonusIndexList = new ArrayList<>();
        for (int i = 0; i < tiles.size(); i++)
            if (tiles.get(i).getType() == Type.Bonus)
                bonusIndexList.add(0, i);
        for (int i: bonusIndexList) {
            Tile t = tiles.remove(i);
            revealedBonusTiles.add(t);
        }
        Collections.sort(revealedBonusTiles);
        Collections.sort(tiles);
        return bonusIndexList.size();
    }

    public void addMeld(Meld meld) {
        this.revealedMelds.add(meld);
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void minusMoney(int money) {
        this.money -= money;
    }

    public int getMoney() {
        return this.money;
    }

    public List<Tile> getTiles() {
        return this.tiles;
    }

    public void setWind(WindRank wind) {
        this.wind = wind;
    }

    public WindRank getWind() {
        return wind;
    }

    public List<Meld> getRevealedMelds() {
        return revealedMelds;
    }

    public List<Tile> getRevealedBonusTiles() {
        return revealedBonusTiles;
    }
}
