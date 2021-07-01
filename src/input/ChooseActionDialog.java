package input;

import action.Action;
import tiles.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChooseActionDialog extends JDialog {

    public ChooseActionDialog(Dialog owner, String title, boolean modal, List<Tile> tileList1,
                              List<Meld> meldList1, List<Meld> meldList2, List<Meld> meldList3,
                              List<Meld> meldList4, List<Action> actionList, List<Boolean> selectedList,
                              Tile tile) {
        super(owner, title, modal);
        setPreferredSize(new Dimension(1200, 650));
        setBackground(new Color(0, 255, 0));

        JPanel contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(1200, 600));
        contentPane.setBackground(new Color(14, 14, 95));
        contentPane.setLayout(new BorderLayout());

        // tile container for the current user
        JPanel currentPanel = new JPanel();
        currentPanel.setBackground(new Color(0, 44, 0));
        currentPanel.setPreferredSize(new Dimension(1200, 100));
        contentPane.add(currentPanel, BorderLayout.SOUTH);
        for (int i = 0; i < tileList1.size(); i++) {
            Tile t = tileList1.get(i);
            JLabel b1 = new JLabel(t.getIcon());
            b1.setPreferredSize(new Dimension(60, 100));
            currentPanel.add(b1);
        }
        addMeldTB(meldList1, currentPanel);

        // tile container for the previous user
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(111, 111, 0));
        leftPanel.setPreferredSize(new Dimension(200, 360));
        contentPane.add(leftPanel, BorderLayout.WEST);
        addMeldLR(meldList4, leftPanel);

        //tile container for the next user
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(111, 0, 111));
        rightPanel.setPreferredSize(new Dimension(200, 360));
        contentPane.add(rightPanel, BorderLayout.EAST);
        addMeldLR(meldList2, rightPanel);

        //tile container for the opposite user
        JPanel oppositePanel = new JPanel();
        oppositePanel.setBackground(new Color(0, 111, 111));
        oppositePanel.setPreferredSize(new Dimension(1200, 100));
        contentPane.add(oppositePanel, BorderLayout.NORTH);
        addMeldTB(meldList3, oppositePanel);

        // Add Action
        JPanel centralPanel = new JPanel();
        centralPanel.setBackground(new Color(22, 11, 66));
        contentPane.add(centralPanel, BorderLayout.CENTER);
        for (int i = 0; i < actionList.size(); i++) {
            Action action = actionList.get(i);
            JPanel actionPanel = new JPanel();
            actionPanel.setPreferredSize(new Dimension(700, 100));
            actionPanel.setBackground(new Color(44, 11, 11));
            centralPanel.add(actionPanel);
            try {
                JLabel actionIcon;
                if (action instanceof action.WinAction)
                    actionIcon = new JLabel(new ImageIcon(ImageIO.read(new File("./images/Hu.png"))));
                else if (action instanceof action.KongAction)
                    actionIcon = new JLabel(new ImageIcon(ImageIO.read(new File("./images/Kong.png"))));
                else if (action instanceof action.PongAction)
                    actionIcon = new JLabel(new ImageIcon(ImageIO.read(new File("./images/Pong.png"))));
                else if (action instanceof action.ChowAction)
                    actionIcon = new JLabel(new ImageIcon(ImageIO.read(new File("./images/Chow.png"))));
                else if (action instanceof action.DefaultAction)
                    actionIcon = new JLabel(new ImageIcon(ImageIO.read(new File("./images/Pass.png"))));
                else
                    throw new RuntimeException("Unknown Action");
                actionIcon.setPreferredSize(new Dimension(90, 100));
                actionPanel.add(actionIcon);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (Tile t: action.getTileHolder()) {
                JLabel icon = new JLabel(t.getIcon());
                icon.setPreferredSize(new Dimension(60, 100));
                actionPanel.add(icon);
            }
            JLabel icon = new JLabel(tile.getIcon());
            icon.setPreferredSize(new Dimension(60, 100));
            actionPanel.add(icon);
            int finalI = i;
            actionPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedList.remove(finalI);
                    selectedList.add(finalI, true);
                    dispose();
                }
            });
        }

        setContentPane(contentPane);
    }

    private void addMeldTB(List<Meld> meldList, JPanel panel) {
        for (int i = 0; i < meldList.size(); i++) {
            List<Tile> tileList = meldList.get(i).getTiles();
            for (Tile t: tileList) {
                JLabel b1 = new JLabel(t.getIcon());
                b1.setPreferredSize(new Dimension(60, 100));
                b1.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                panel.add(b1);
            }
        }
    }

    private void addMeldLR(List<Meld> meldList, JPanel panel) {
        //panel.setLayout(new GridLayout(5, 4));
        for (int i = 0; i < meldList.size(); i++) {
            List<Tile> tileList = meldList.get(i).getTiles();
            for (int j = 0; j < tileList.size(); j++) {
                Tile t = tileList.get(j);
                ImageIcon icon = t.getIcon();
                JLabel b1 = new JLabel(icon);
                b1.setPreferredSize(new Dimension(60, 100));
                panel.add(b1);//.setLocation(i, j);
            }
        }
    }

    public static int chooseActionToExecute(List<Tile> tileList1, List<Meld> meldList1, List<Meld> meldList2,
                                            List<Meld> meldList3, List<Meld> meldList4, List<Action> actionList,
                                            Tile tile, int pid) {
        List<Boolean> selectedList1 = new ArrayList<>();
        for (int i = 0; i < actionList.size(); i++)
            selectedList1.add(Boolean.FALSE);

        ChooseActionDialog dialog = new ChooseActionDialog(null, String.format("user %d", pid), true, tileList1,
                meldList1, meldList2, meldList3, meldList4, actionList, selectedList1, tile);
        dialog.pack();
        dialog.setVisible(true);

        for (int i = 0; i < actionList.size(); i++)
            if (selectedList1.get(i))
                return i;
        return 0;
    }

}
