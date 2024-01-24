package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class RightHand extends MazeAlgorithm{
    Explorer maze_explorer;
    Maze maze;

    public RightHand(Configuration config) throws IOException {
        maze = new Maze(config);
        maze_explorer = new Explorer(maze);
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
        maze_explorer.setLocation(entry);

        while (!maze_explorer.isAt(exit)) {
            if (maze_explorer.goRight()) {
                path.addInstruction(Instruction.R);
                path.addInstruction(Instruction.F);
            } else if (maze_explorer.goForward()) {
                path.addInstruction(Instruction.F);
            } else if (maze_explorer.goLeft()) {
                path.addInstruction(Instruction.L);
                path.addInstruction(Instruction.F);
            } else if (maze_explorer.goBackward()) {
                path.addInstruction(Instruction.R);
                path.addInstruction(Instruction.R);
                path.addInstruction(Instruction.F);
            }
        }

        return path;
    }
}
