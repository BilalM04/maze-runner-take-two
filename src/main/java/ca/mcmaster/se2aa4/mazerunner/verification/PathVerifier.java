package ca.mcmaster.se2aa4.mazerunner.verification;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.path.Instruction;
import ca.mcmaster.se2aa4.mazerunner.path.Path;
import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

public class PathVerifier implements Verifier {

    public boolean verifyPath(Maze maze, Path path){
        return checkWestToEast(maze, path) || checkEastToWest(maze, path);
    }

    private boolean checkWestToEast(Maze maze, Path path) {
        Location start = maze.findWestEntry();
        start.setDirection(Direction.EAST);
        Location end = maze.findEastEntry();
        end.setDirection(Direction.EAST);
        return traverseMaze(maze, path, start, end);
    }

    private boolean checkEastToWest(Maze maze, Path path) {
        Location start = maze.findEastEntry();
        start.setDirection(Direction.WEST);
        Location end = maze.findWestEntry();
        end.setDirection(Direction.WEST);
        return traverseMaze(maze, path, start, end);
    }

    /**
     * Traverses the maze according to the provided path, starting from a specified location and aiming to reach the end.
     * The method uses a MazeExplorer to follow the instructions in the path and checks if the explorer ends up at the expected location.
     *
     * @param path The path containing instructions for the explorer to follow.
     * @param start The starting location in the maze.
     * @param end The expected ending location in the maze.
     * @return True if the explorer successfully traverses the maze according to the path and reaches the end, false otherwise.
     * @throws IllegalStateException If an invalid instruction is encountered in the path.
     */
    private boolean traverseMaze(Maze maze, Path path, Location start, Location end) {
        Explorer mazeExplorer = new MazeExplorer(maze, start);
        boolean isValidInstruction;

        for (int i = 0; i < path.length(); i++) {
            isValidInstruction = true; // variable to keep track whether an instruction was succesful
            switch (path.getInstruction(i)) {
                case Instruction.F:
                    isValidInstruction = mazeExplorer.goForward();
                    break;
                case Instruction.R:
                    mazeExplorer.turnRight();
                    break;
                case Instruction.L:
                    mazeExplorer.turnLeft();
                    break;
                default:
                    throw new IllegalStateException();
            }

            if (!isValidInstruction) {
                return false;
            }
        }

        return mazeExplorer.isAt(end);
    }
}
