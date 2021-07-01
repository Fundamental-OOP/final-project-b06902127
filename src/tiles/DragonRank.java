package tiles;

public enum DragonRank {
    Green(0), Red(1), White(2);

    private int value;
    private DragonRank(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
