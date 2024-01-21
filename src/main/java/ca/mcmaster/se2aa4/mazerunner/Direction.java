package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction getRightDirection() {
        int ordinal = (this.ordinal() + 1) % 4;
        return values()[ordinal];
    }

    public Direction getLeftDirection() {
        int ordinal = (this.ordinal() - 1 + 4) % 4;
        return values()[ordinal];
    }

    public Direction getOppositeDirection() {
        int ordinal = (this.ordinal() + 2) % 4;
        return values()[ordinal];
    }
}
