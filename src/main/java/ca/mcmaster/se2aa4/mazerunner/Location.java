package ca.mcmaster.se2aa4.mazerunner;

public class Location {
    private int row;
    private int column;
    private Direction direction;
    private Configuration config;

    public Location(int row, int column, Direction dir, Configuration config) {
        this.row = row;
        this.column = column;
        this.direction = dir;
        this.config = config;
    }

    public void setRow(int row) {
        if (row < 0 || row >= config.MAZE_HEIGHT()) {
            throw new IllegalArgumentException("Row out of bounds of maze: " + row);
        }
        this.row = row;
    }

    public void setColumn(int column) {
        if (column < 0 || column >= config.MAZE_WIDTH()) {
            throw new IllegalArgumentException("Column out of bounds of maze: " + column);
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
