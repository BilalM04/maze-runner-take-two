package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.util.Map;

public class Explorer {
    private Maze maze;
    private Location loc;

    public Explorer(Configuration config) throws IOException {
        this.maze = new Maze(config);
    }

    public String getEastToWest(boolean factorized) {
        Location start = maze.findEastEntry();
        start.setDirection(Direction.WEST);
        Location end = maze.findWestEntry();
        end.setDirection(Direction.WEST);
        Path path = findPath(start, end);

        if (factorized) {
            return path.getFactorizedPath();
        } else {
            return path.getCanonicalPath();
        }
    }

    public String getWestToEast(boolean factorized) {
        Location start = maze.findWestEntry();
        start.setDirection(Direction.EAST);
        Location end = maze.findEastEntry();
        end.setDirection(Direction.EAST);
        Path path = findPath(start, end);

        if (factorized) {
            return path.getFactorizedPath();
        } else {
            return path.getCanonicalPath();
        }
    }

    private Path findPath(Location entry, Location exit) {
        Path path = new Path();
        loc = entry;

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

    public boolean verifyPath(Path path){
        return checkWestToEast(path) || checkEastToWest(path);
    }

    private boolean checkWestToEast(Path path) {
        Location start = maze.findWestEntry();
        start.setDirection(Direction.EAST);
        Location end = maze.findEastEntry();
        end.setDirection(Direction.EAST);
        return traverseMaze(path, start, end);
    }

    private boolean checkEastToWest(Path path) {
        Location start = maze.findEastEntry();
        start.setDirection(Direction.WEST);
        Location end = maze.findWestEntry();
        end.setDirection(Direction.WEST);
        return traverseMaze(path, start, end);
    }

    private boolean traverseMaze(Path path, Location start, Location end) {
        loc = start;
        boolean is_valid_instruction;

        for (int i = 0; i < path.length(); i++) {
            is_valid_instruction = true;
            switch (path.getInstruction(i)) {
                case Instruction.F:
                    is_valid_instruction = goForward();
                    break;
                case Instruction.R:
                    loc.setDirection(loc.getDirection().getRightDirection());
                    break;
                case Instruction.L:
                    loc.setDirection(loc.getDirection().getLeftDirection());
                    break;
                default:
                    throw new IllegalStateException();
            }

            if (!is_valid_instruction) {
                return false;
            }
        }

        return loc.equals(end);
    }
}
