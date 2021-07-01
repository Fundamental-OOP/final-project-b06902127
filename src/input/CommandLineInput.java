package input;

import action.Action;
import tiles.Tile;

import java.util.List;
import static utils.Inputs.in;

public class CommandLineInput implements UserInputInterface {

    public int chooseActionToExecution(List<Action> actionList) {
        int ret = -1;
        do {
            System.out.println("Please choose an action to execute : ");
            for (int i = 0; i < actionList.size(); i++)
                System.out.println(String.format("[%d]%s ", i, actionList.get(i)));
            ret = Integer.parseInt(in.nextLine());
        } while (ret < 0 || ret >= actionList.size());
        return ret;
    }

    public int chooseCardToDiscard(List<Tile> tileList) {
        String tileListString = "";
        int ret = -1;
        for (int i = 0; i < tileList.size(); i++) {
            tileListString += String.format("[%d]%s ", i, tileList.get(i));
        }
        do {
            System.out.println("Please choose a tile to discard : ");
            System.out.println(tileListString);
            ret = Integer.parseInt(in.nextLine());
        } while (ret < 0 || ret >= tileList.size());
        return ret;
    }
}
