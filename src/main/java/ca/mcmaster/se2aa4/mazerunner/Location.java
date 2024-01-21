package ca.mcmaster.se2aa4.mazerunner;

public class Location {
    private int row;
    private int column;
    private Direction direction;

    public Location(int row, int column, Direction dir) {
        this.row = row;
        this.column = column;
        this.direction = dir;
    }

    public void setRow(int row) {
        if (row < 0) {
            throw new IllegalArgumentException();
        }
        this.row = row;
    }

    public void setColumn(int column) {
        if (column < 0) {
            throw new IllegalArgumentException();
        }
        this.column = column;
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean equals(Location loc) {
        if (this.row == loc.row && this.column == loc.column && this.direction == loc.direction) {
            return true;
        }
        return false;
    }
}
