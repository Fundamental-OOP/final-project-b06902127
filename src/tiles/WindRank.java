package tiles;

public enum WindRank {
    East(0), South(1), West(2), North(3);

    private int value;
    private WindRank(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
