package scoring;

import tiles.SuitedTile;

import java.util.*;


public class SuitedTileComparator implements Comparator<SuitedTile> {
    @Override
    public int compare(SuitedTile a, SuitedTile b) {
        return a.compareTo(b);
    }
}