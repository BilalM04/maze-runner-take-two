package ca.mcmaster.se2aa4.mazerunner.algorithm;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.path.Instruction;
import ca.mcmaster.se2aa4.mazerunner.path.Path;
import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

public class RightHand implements MazeAlgorithm {

    /**
     * Retrieves the path from the west entry point to the east entry point in the maze 
     * using the righthand method.
     *
     * @param factorized If true, the returned path is factorized.
     *                   If false, the returned path is canonical.
     * @param maze Maze Object
     * @return A string representation of the found path.
     */
    @Override
    public String getPath(Maze maze, boolean factorized) {
        Location start = maze.findWestEntry();
        start.setDirection(Direction.EAST);
        Location end = maze.findEastEntry();
        end.setDirection(Direction.EAST);
        Path path = findPath(maze, start, end);

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
    private Path findPath(Maze maze, Location entry, Location exit) {
        Path path = new Path();
        Explorer mazeExplorer = new MazeExplorer(maze, entry);

        while (!mazeExplorer.isAt(exit)) {
            if (mazeExplorer.goRight()) {
                path.addInstruction(Instruction.R);
                path.addInstruction(Instruction.F);
            } else if (mazeExplorer.goForward()) {
                path.addInstruction(Instruction.F);
            } else if (mazeExplorer.goLeft()) {
                path.addInstruction(Instruction.L);
                path.addInstruction(Instruction.F);
            } else if (mazeExplorer.goBackward()) {
                path.addInstruction(Instruction.L);
                path.addInstruction(Instruction.L);
                path.addInstruction(Instruction.F);
            }
        }

        return path;
    }
}
