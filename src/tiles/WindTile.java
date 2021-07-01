package tiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public class WindTile extends Tile implements Comparable{
    private final WindRank windRank;
    private ImageIcon icon;

    public WindTile(WindRank type) {
        super(Type.Wind);
        this.windRank = type;
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

    public WindRank getWindRank() {
        return windRank;
    }

    @Override
    public int compareTo(Object o) {
        if (super.compareTo(o) != 0)
            return super.compareTo(o);
        else
            return windRank.value() - ((WindTile) o).getWindRank().value();
    }

    @Override
    public String toString() {
        return this.windRank.toString();
    }
}
