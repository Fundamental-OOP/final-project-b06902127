package tiles;

public enum BonusRank {
    PlumBlossom(0), Orchid(1), Chrysanthemum(2), Bamboo(3), Spring(4), Summer(5), Autumn(6), Winter(7);

    private int value;
    private BonusRank(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}