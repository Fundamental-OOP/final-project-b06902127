package tiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wall{
    List<Tile> tiles = new ArrayList<>();
    public int startPosition = 0;

    public Wall() {
        //Add Suit
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                tiles.add(new SuitedTile(SuitedType.Character, i));
                tiles.add(new SuitedTile(SuitedType.Bamboo, i));
                tiles.add(new SuitedTile(SuitedType.Dot, i));
            }
        }
        //Add Wind
        for (int i = 0; i < 4; i++) {
            tiles.add(new WindTile(WindRank.East));
            tiles.add(new WindTile(WindRank.South));
            tiles.add(new WindTile(WindRank.West));
            tiles.add(new WindTile(WindRank.North));
        }
        //Add Dragon
        for (int i = 0; i < 4; i++) {
            tiles.add(new DragonTile(DragonRank.Green));
            tiles.add(new DragonTile(DragonRank.Red));
            tiles.add(new DragonTile(DragonRank.White));
        }
        //Add Bonus
        tiles.add(new BonusTile(BonusRank.PlumBlossom));
        tiles.add(new BonusTile(BonusRank.Orchid));
        tiles.add(new BonusTile(BonusRank.Chrysanthemum));
        tiles.add(new BonusTile(BonusRank.Bamboo));
        tiles.add(new BonusTile(BonusRank.Spring));
        tiles.add(new BonusTile(BonusRank.Summer));
        tiles.add(new BonusTile(BonusRank.Autumn));
        tiles.add(new BonusTile(BonusRank.Winter));

        //shuffle
        shuffle();
    }

    public List<Tile> draw(int number) {
        List<Tile> ret = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Tile tile = tiles.get(0);
            tiles.remove(0);
            ret.add(tile);
        }
        return ret;
    }

    public void shuffle() {
        Collections.shuffle(tiles);
    }


    public int getLength() {
        return tiles.size();
    }
}