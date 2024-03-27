package ca.mcmaster.se2aa4.mazerunner.algorithm;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.path.Path;
import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

public class BFS implements MazeAlgorithm {
    
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

    private Path findPath(Maze maze, Location start, Location end) {
        Path path = new Path();
        Explorer explorer = new MazeExplorer(maze, start);


        return path;
    }
}
