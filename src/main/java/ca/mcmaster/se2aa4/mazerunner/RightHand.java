package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class RightHand implements MazeAlgorithm{
    private Maze maze;

    public RightHand(Configuration config) throws IOException {
        this.maze = new ArrayMaze(config);
    }

    public String getPath(boolean factorized) {
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
        Explorer maze_explorer = new MazeExplorer(this.maze, entry);

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
                path.addInstruction(Instruction.L);
                path.addInstruction(Instruction.L);
                path.addInstruction(Instruction.F);
            }
        }

        return path;
    }
}
