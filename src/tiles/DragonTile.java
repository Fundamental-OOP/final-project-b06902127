package tiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public class DragonTile extends Tile implements Comparable{
    private final DragonRank dragonRank;
    private ImageIcon icon;

    public DragonTile(DragonRank type) {
        super(tiles.Type.Dragon);
        this.dragonRank = type;
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

    public DragonRank getDragonRank() {
        return dragonRank;
    }

    @Override
    public int compareTo(Object o) {
        if (super.compareTo(o) != 0)
            return super.compareTo(o);
        else
            return dragonRank.value() - ((DragonTile) o).getDragonRank().value();
    }

    @Override
    public String toString() {
        return this.dragonRank.toString();
    }

}
