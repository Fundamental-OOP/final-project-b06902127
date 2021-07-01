package tiles;

public enum Type {
    Bonus(3), Wind(2), Dragon(1), Suited(0);

    private int value;
    private Type(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
