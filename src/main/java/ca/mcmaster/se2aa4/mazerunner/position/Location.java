package ca.mcmaster.se2aa4.mazerunner.position;

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
        this.row = row;
    }

    public void setColumn(int column) {
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

    public Direction getRightDirection() {
        return this.direction.getRightDirection(); // calls method from enum to retreive the relative right direction
    }

    public Direction getLeftDirection() {
        return this.direction.getLeftDirection(); // calls method from enum to retreive the relative left direction
    }

    public Direction getOppositeDirection() {
        return this.direction.getOppositeDirection(); // calls method from enum to retreive the relative opposite direction
    }

    public Location getAdjacentLocation(Direction dir) {
        switch(dir) {
            case Direction.NORTH:
                return new Location(row - 1, column, dir);
            case Direction.EAST:
                return new Location(row, column + 1, dir);
            case Direction.SOUTH:
                return new Location(row + 1, column, dir);
            case Direction.WEST:
                return new Location(row, column - 1, dir);
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + column;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        if (row != other.row)
            return false;
        if (column != other.column)
            return false;
        return true;
    }
}
