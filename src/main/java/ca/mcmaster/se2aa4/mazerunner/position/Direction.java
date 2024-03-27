package ca.mcmaster.se2aa4.mazerunner.position;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction getRightDirection() {
        return values()[(this.ordinal() + 1) % 4];
    }

    public Direction getLeftDirection() {
        return values()[(this.ordinal() - 1 + 4) % 4];
    }

    public Direction getOppositeDirection() {
        return values()[(this.ordinal() + 2) % 4];
    }
}
