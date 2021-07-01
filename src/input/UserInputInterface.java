package input;

import action.Action;
import tiles.Tile;

import java.util.List;

public interface UserInputInterface {
    public int chooseCardToDiscard(List<Tile> tileList);
    public int chooseActionToExecution(List<Action> actionList);
}
