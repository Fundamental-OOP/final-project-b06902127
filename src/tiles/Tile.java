package tiles;

import javax.swing.*;

public abstract class Tile implements Comparable{
    private Type type;

    public Tile(Type type) {
        this.type = type;
    }

    public Type getType(){
        return type;
    }

    public abstract ImageIcon getIcon();

    @Override
    public int compareTo(Object o) {
        return type.value() - ((Tile) o).getType().value();
    }
}