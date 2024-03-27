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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {
            return false;
        }

        Location loc = (Location) obj;
        
        if (this.row == loc.row && this.column == loc.column) {
            return true;
        }
        return false;
    }
}
