package tiles;

public enum SuitedType {
    Character(0), Bamboo(1), Dot(2);

    private int value;
    private SuitedType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
