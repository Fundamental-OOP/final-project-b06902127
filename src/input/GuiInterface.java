package input;

import action.Action;
import tiles.Meld;
import tiles.Tile;

import java.awt.*;
import java.util.List;

public class GuiInterface {
    public static int chooseActionToExecution(List<Tile> tileList1, List<Meld> meldList1,
                                              List<Meld> meldList2, List<Meld> meldList3,
                                              List<Meld> meldList4, List<Action> actionList,
                                              Tile tile, int pid) {
        return ChooseActionDialog.chooseActionToExecute(tileList1, meldList1,
                meldList2, meldList3, meldList4, actionList, tile, pid);
    }

    public static int chooseTileToDiscard(List<Tile> tileList1, List<Meld> meldList1, List<Meld> meldList2,
                                          List<Meld> meldList3, List<Meld> meldList4, int pid) {
        return DrawTilePanel.chooseTileToDiscard(tileList1, meldList1, meldList2, meldList3, meldList4, pid);
    }

    public static boolean askWhetherContinue(int pid1, int pid2, int pid3, int pid4,
                                             int mny1, int mny2, int mny3, int mny4) {
        return ContinueDialog.createAndShowUI(pid1, pid2, pid3, pid4, mny1, mny2, mny3, mny4);
    }
}
