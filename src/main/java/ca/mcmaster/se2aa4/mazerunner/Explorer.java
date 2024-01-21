package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.util.Map;

public class Explorer {
    private Maze maze;
    private Location loc;

    public Explorer(Configuration config) throws IOException {
        this.maze = new Maze(config);
        this.loc = maze.findEntry();
    }

    public String getPath() {
        Path path = findPath();
        return path.getFactorizedPath();
    }

    private Path findPath() {
        Path path = new Path();
        Location exit = maze.findExit();

        while (!this.loc.equals(exit)) {
            if (this.goRight()) {
                path.addInstruction(Instruction.R);
                path.addInstruction(Instruction.F);
            } else if (this.goForward()) {
                path.addInstruction(Instruction.F);
            } else if (this.goLeft()) {
                path.addInstruction(Instruction.L);
                path.addInstruction(Instruction.F);
            } else if (this.goBackward()) {
                path.addInstruction(Instruction.R);
                path.addInstruction(Instruction.R);
                path.addInstruction(Instruction.F);
            }
        }

        return path;
    }

    private boolean goRight() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction right = this.loc.getDirection().getRightDirection();

        if (neighbours.get(right) == Tile.EMPTY) {
            loc.setDirection(right);
            this.changePosition(right);
            return true;
        }

        return false;
    }

    private boolean goLeft() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction left = this.loc.getDirection().getLeftDirection();

        if (neighbours.get(left) == Tile.EMPTY) {
            loc.setDirection(left);
            this.changePosition(left);
            return true;
        }

        return false;
    }

    private boolean goForward() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction dir = loc.getDirection();

        if (neighbours.get(dir) == Tile.EMPTY) {
            loc.setDirection(dir);
            this.changePosition(dir);
            return true;
        }

        return false;
    }

    private boolean goBackward() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
        Direction back = this.loc.getDirection().getOppositeDirection();

        if (neighbours.get(back) == Tile.EMPTY) {
            loc.setDirection(back);
            this.changePosition(back);
            return true;
        }

        return false;
    }

    private void changePosition(Direction dir) {
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

    public boolean verifyPath(){
        return false;
    }
}
