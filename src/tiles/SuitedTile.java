package tiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public class SuitedTile extends Tile implements Comparable{
    private final SuitedType suitedType;
    private final int rank;
    private ImageIcon icon;

    public SuitedTile(SuitedType type, int rank) {
        super(Type.Suited);
        this.rank = rank;
        this.suitedType = type;
        try {
            if (rank >= 1 && rank <= 9)
                this.icon = new ImageIcon(ImageIO.read(new File("./images/" + this.toString() + ".png")));
            else
                this.icon = null;
        } catch (Exception e) {
            e.printStackTrace();
            this.icon = null;
        }
    }

    public int getRank() {
        return rank;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public SuitedType getSuitedType() {
        return suitedType;
    }

    @Override
    public int compareTo(Object o) {
        if (super.compareTo(o) != 0)
            return super.compareTo(o);
        else if (suitedType != ((SuitedTile) o).getSuitedType())
            return suitedType.value() - ((SuitedTile) o).getSuitedType().value();
        else
            return rank - ((SuitedTile) o).getRank();
    }

    @Override
    public String toString() {
        return this.suitedType + Integer.toString(rank);
    }

}
