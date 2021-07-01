package tiles;

import java.util.ArrayList;
import java.util.List;

public abstract class Meld {
    List<Tile> tiles = new ArrayList<>();

    public Meld(Tile t1, Tile t2, Tile t3) {
        tiles.add(t1);
        tiles.add(t2);
        tiles.add(t3);
    }

    public Meld(Tile t1, Tile t2, Tile t3, Tile t4) {
        tiles.add(t1);
        tiles.add(t2);
        tiles.add(t3);
        tiles.add(t4);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public String toString() {
        String ret = "";
        for(int i = 0; i < tiles.size(); i++)
            ret += tiles.get(i).toString() + " ";
        return ret;
    }
}
