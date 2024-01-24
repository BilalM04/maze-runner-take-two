package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.util.Map;

public class Explorer {
    private Maze maze;
    private Location loc;

    public Explorer(Maze maze) throws IOException {
        this.maze = maze;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }

    public Location getLocation() {
        return this.loc;
    }

    public boolean goRight() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction right = this.loc.getDirection().getRightDirection();

        if (neighbours.get(right) == Tile.EMPTY) {
            loc.setDirection(right);
            this.changePosition(right);
            return true;
        }

        return false;
    }

    public boolean goLeft() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction left = this.loc.getDirection().getLeftDirection();

        if (neighbours.get(left) == Tile.EMPTY) {
            loc.setDirection(left);
            this.changePosition(left);
            return true;
        }

        return false;
    }

    public boolean goForward() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction dir = loc.getDirection();

        if (neighbours.get(dir) == Tile.EMPTY) {
            loc.setDirection(dir);
            this.changePosition(dir);
            return true;
        }

        return false;
    }

    public boolean goBackward() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction back = this.loc.getDirection().getOppositeDirection();

        if (neighbours.get(back) == Tile.EMPTY) {
            loc.setDirection(back);
            this.changePosition(back);
            return true;
        }

        return false;
    }

    public void turnLeft() {
        loc.setDirection(loc.getDirection().getLeftDirection());
    }

    public void turnRight() {
        loc.setDirection(loc.getDirection().getRightDirection());
    }

    public boolean isAt(Location loc) {
        return this.loc.equals(loc);
    }

    public void changePosition(Direction dir) {
        if (dir == Direction.NORTH) {
            loc.setRow(loc.getRow() - 1);
        } else if (dir == Direction.EAST) {
            loc.setColumn(loc.getColumn() + 1);
        } else if (dir == Direction.SOUTH) {
            loc.setRow(loc.getRow() + 1);
        } else if (dir == Direction.WEST) {
            loc.setColumn(loc.getColumn() - 1);
        }
    }
}
