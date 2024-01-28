package ca.mcmaster.se2aa4.mazerunner;
import java.util.Map;

public class MazeExplorer implements Explorer {
    private Maze maze;
    private Location loc;

    public MazeExplorer(Maze maze, Location loc) {
        this.maze = maze;
        if (loc.getRow() < 0 || loc.getRow() >= maze.height() || loc.getColumn() < 0 || loc.getColumn() >= maze.width() || maze.getTile(loc) == Tile.WALL) {
            throw new IndexOutOfBoundsException();
        }
        this.loc = loc;
    }

    public boolean goRight() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction right = this.loc.getRightDirection();

        if (neighbours.get(right) == Tile.EMPTY) {
            this.loc.setDirection(right);
            this.move(right);
            return true;
        }

        return false;
    }

    public boolean goLeft() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction left = this.loc.getLeftDirection();

        if (neighbours.get(left) == Tile.EMPTY) {
            this.loc.setDirection(left);
            this.move(left);
            return true;
        }

        return false;
    }

    public boolean goForward() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction dir = loc.getDirection();

        if (neighbours.get(dir) == Tile.EMPTY) {
            this.loc.setDirection(dir);
            this.move(dir);
            return true;
        }

        return false;
    }

    public boolean goBackward() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction back = this.loc.getOppositeDirection();

        if (neighbours.get(back) == Tile.EMPTY) {
            this.loc.setDirection(back);
            this.move(back);
            return true;
        }

        return false;
    }

    public void turnLeft() {
        this.loc.setDirection(this.loc.getLeftDirection());
    }

    public void turnRight() {
        this.loc.setDirection(this.loc.getRightDirection());
    }

    public boolean isAt(Location loc) {
        return this.loc.equals(loc);
    }

    private void move(Direction dir) {
        if (dir == Direction.NORTH) {
            this.loc.setRow(loc.getRow() - 1);
        } else if (dir == Direction.EAST) {
            this.loc.setColumn(loc.getColumn() + 1);
        } else if (dir == Direction.SOUTH) {
            this.loc.setRow(loc.getRow() + 1);
        } else if (dir == Direction.WEST) {
            this.loc.setColumn(loc.getColumn() - 1);
        }
    }
}
