package ca.mcmaster.se2aa4.mazerunner.verification;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.path.Path;

public interface Verifier {
    
    public boolean verifyPath(Maze maze, Path path);
}
