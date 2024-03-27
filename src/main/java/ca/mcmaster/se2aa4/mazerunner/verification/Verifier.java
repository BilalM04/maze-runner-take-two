package ca.mcmaster.se2aa4.mazerunner.verification;

import java.io.IOException;

import ca.mcmaster.se2aa4.mazerunner.configuration.Configuration;
import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.maze.ArrayMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.path.Instruction;
import ca.mcmaster.se2aa4.mazerunner.path.Path;
import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

public class Verifier {
    private Maze maze;

    public Verifier(Configuration config) throws IOException {
        maze = new ArrayMaze(config);
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
    private boolean traverseMaze(Path path, Location start, Location end) {
        Explorer maze_explorer = new MazeExplorer(this.maze, start);
        boolean is_valid_instruction;

        for (int i = 0; i < path.length(); i++) {
            is_valid_instruction = true; // variable to keep track whether an instruction was succesful
            switch (path.getInstruction(i)) {
                case Instruction.F:
                    is_valid_instruction = maze_explorer.goForward();
                    break;
                case Instruction.R:
                    maze_explorer.turnRight();
                    break;
                case Instruction.L:
                    maze_explorer.turnLeft();
                    break;
                default:
                    throw new IllegalStateException();
            }

            if (!is_valid_instruction) {
                return false;
            }
        }

        return maze_explorer.isAt(end);
    }
}
