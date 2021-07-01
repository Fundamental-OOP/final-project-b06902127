package input;

import tiles.Meld;
import tiles.SequenceMeld;
import tiles.Tile;
import tiles.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawTilePanel extends JDialog {

    public DrawTilePanel(Dialog owner, String title, boolean modal, List<Tile> tileList1,
                         List<Meld> meldList1, List<Meld> meldList2, List<Meld> meldList3,
                         List<Meld> meldList4, List<Boolean> selectedList) {
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
            int index = i;
            b1.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(e.getSource() == b1);
                    selectedList.remove(index);
                    selectedList.add(index, true);
                    dispose();
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });
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

    public static int chooseTileToDiscard(List<Tile> tileList1, List<Meld> meldList1, List<Meld> meldList2,
                                      List<Meld> meldList3, List<Meld> meldList4, int pid) {
        List<Boolean> selectedList1 = new ArrayList<>();
        for (int i = 0; i < tileList1.size(); i++)
            selectedList1.add(Boolean.FALSE);

        DrawTilePanel dialog = new DrawTilePanel(null, String.format("user %d", pid), true, tileList1, meldList1, meldList2, meldList3, meldList4, selectedList1);
        dialog.pack();
        dialog.setVisible(true);

        for (int i = 0; i < tileList1.size(); i++)
            if (selectedList1.get(i))
                return i;
        return 0;
    }

}
