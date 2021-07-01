package tiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public class BonusTile extends Tile implements Comparable{
    private final BonusRank bonusRank;
    private ImageIcon icon;

    public BonusTile(BonusRank type) {
        super(Type.Bonus);
        this.bonusRank = type;
        try {
            this.icon = new ImageIcon(ImageIO.read(new File("./images/" + this.toString() + ".png")));
        } catch (Exception e) {
            e.printStackTrace();
            this.icon = null;
        }
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    public BonusRank getBonusRank() {
        return bonusRank;
    }

    @Override
    public int compareTo(Object o) {
        if (super.compareTo(o) != 0)
            return super.compareTo(o);
        else
            return bonusRank.value() - ((BonusTile) o).getBonusRank().value();
    }

    @Override
    public String toString() {
        return this.bonusRank.toString();
    }
}
