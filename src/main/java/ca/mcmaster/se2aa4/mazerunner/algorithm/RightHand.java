package ca.mcmaster.se2aa4.mazerunner.algorithm;

import java.io.IOException;

import ca.mcmaster.se2aa4.mazerunner.configuration.Configuration;
import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.maze.ListMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.path.Instruction;
import ca.mcmaster.se2aa4.mazerunner.path.Path;
import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

public class RightHand implements MazeAlgorithm{
    private Maze maze;

    public RightHand(Configuration config) throws IOException {
        this.maze = new ListMaze(config);
    }

    /**
     * Retrieves the path from the west entry point to the east entry point in the maze.
     *
     * @param factorized If true, the returned path is factorized.
     *                   If false, the returned path is canonical.
     * @return A string representation of the found path.
     */
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

    /**
     * Finds a path from the entry point to the exit point in the maze using the right hand rule.
     * Algorithm: Go right. If you can't go right, go forward. If you can't go forward, go left.
     *            If you can't go left go backwards.
     *
     * @param entry The starting location in the maze.
     * @param exit The target location to reach in the maze.
     * @return A Path object representing the found path from the entry to the exit.
     */
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
