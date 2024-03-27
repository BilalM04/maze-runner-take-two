package ca.mcmaster.se2aa4.mazerunner.algorithm;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;

public interface MazeAlgorithm {

    public String getPath(Maze maze, boolean factorized);
}
