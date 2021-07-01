package input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ContinueDialog  {
    public static boolean createAndShowUI(int pid1, int pid2, int pid3, int pid4,
                                          int mny1, int mny2, int mny3, int mny4) {

        JPanel panel = new JPanel( new GridLayout(5, 2) );

        JLabel labelId0 = new JLabel("player id");
        JLabel labelMy0 = new JLabel("money");
        panel.add(labelId0).setLocation(0, 0);
        panel.add(labelMy0).setLocation(0, 1);

        JLabel labelId1 = new JLabel(String.valueOf(pid1));
        JLabel labelMy1 = new JLabel(String.valueOf(mny1));
        panel.add(labelId1).setLocation(1, 0);
        panel.add(labelMy1).setLocation(1, 1);

        JLabel labelId2 = new JLabel(String.valueOf(pid2));
        JLabel labelMy2 = new JLabel(String.valueOf(mny2));
        panel.add(labelId2).setLocation(2, 0);
        panel.add(labelMy2).setLocation(2, 1);

        JLabel labelId3 = new JLabel(String.valueOf(pid3));
        JLabel labelMy3 = new JLabel(String.valueOf(mny3));
        panel.add(labelId3).setLocation(3, 0);
        panel.add(labelMy3).setLocation(3, 1);

        JLabel labelId4 = new JLabel(String.valueOf(pid4));
        JLabel labelMy4 = new JLabel(String.valueOf(mny4));
        panel.add(labelId4).setLocation(4, 0);
        panel.add(labelMy4).setLocation(4, 1);


        int result = JOptionPane.showConfirmDialog(
                null, // use your JFrame here
                panel,
                "ContinueDialog",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if(result == JOptionPane.YES_OPTION)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        createAndShowUI(0, 1, 2, 3, 0, 0, 20, 10);
    }
}