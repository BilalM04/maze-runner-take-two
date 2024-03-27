package ca.mcmaster.se2aa4.mazerunner.algorithm;

import java.util.List;
import java.util.Map;

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
        List<Object> result = findPath(maze, start, end);
        Map<Location, Location> pathIndex = (Map) result.get(0);
        Map<Location, Path> costIndex = (Map) result.get(1);

        // if (factorized) {
        //     return path.getFactorizedPath();
        // } else {
        //     return path.getCanonicalPath();
        // }

        return "";
    }

    private List<Object> findPath(Maze maze, Location start, Location end) {
        Path path = new Path();
        Explorer explorer = new MazeExplorer(maze, start);




        return null;
    }
}
