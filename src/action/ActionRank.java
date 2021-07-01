package action;

public enum ActionRank {
	WIN(0), KONG(1), PONG(2), CHOW(3), DEFAULT(4);

    private int value;
    private ActionRank(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}

