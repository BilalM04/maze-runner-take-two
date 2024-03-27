package ca.mcmaster.se2aa4.mazerunner.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;
import ca.mcmaster.se2aa4.mazerunner.path.Instruction;
import ca.mcmaster.se2aa4.mazerunner.path.Path;
import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

public class BFS implements MazeAlgorithm {

    private static final Logger logger = LogManager.getLogger();
    
    public String getPath(Maze maze, boolean factorized) {
        Location start = maze.findWestEntry();
        start.setDirection(Direction.EAST);
        Location end = maze.findEastEntry();
        end.setDirection(Direction.EAST); 
        Path path = findPath(maze, start, end);

        // Location loc = new Location(0, 0, Direction.NORTH);
        // Location loc2 = new Location(0, 0, Direction.NORTH);

        // Map<Location, Integer> map = new HashMap<>();

        // map.put(loc, 2);

        // System.out.println(map.containsKey(loc2));

        if (factorized) {
            return path.getFactorizedPath();
        } else {
            return path.getCanonicalPath();
        }

        //return "";
    }

    private Path findPath(Maze maze, Location start, Location end) {
        Path path = new Path();
        List<Map> result = getIndex(maze, start, end);
        Map<Location, Location> pathIndex = result.get(0);
        Map<Location, Path> costIndex = result.get(1);
        Stack<Location> stack = new Stack<>();

        if (!pathIndex.containsKey(end)) {
            return path;
        }

        Location current = end;

        while (!current.equals(start)) {
            stack.add(current);
            current = pathIndex.get(current);
        }

        stack.add(start);

        while (!stack.isEmpty()) {
            path.appendPath(costIndex.get(stack.pop()));
        }

        return path;
    }

    private List<Map> getIndex(Maze maze, Location start, Location end) {
        Map<Location, Location> pathIndex = new HashMap<>();
        Map<Location, Path> costIndex = new HashMap<>();
        pathIndex.put(start, start);
        costIndex.put(start, new Path());
        Queue<Location> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Location loc = queue.poll();
            // logger.info(loc.getRow());
            Map<Direction, Tile> neighbours = maze.getNeighbours(loc);
            for (Direction dir : neighbours.keySet()) {
                if (neighbours.get(dir) == Tile.EMPTY) {
                    Location m = getLocation(loc, dir);
                    if (!pathIndex.containsKey(m)) {
                        queue.add(m);
                        pathIndex.put(m, loc);
                        costIndex.put(m, generPath(loc, m));
                    }
                }
            }
        }
        List<Map> lst = new ArrayList<>();

        lst.add(pathIndex);
        lst.add(costIndex);

        return lst;
    }

    private Location getLocation(Location loc, Direction dir) {
        switch(dir) {
            case Direction.NORTH:
                return new Location(loc.getRow() - 1, loc.getColumn(), dir);
            case Direction.EAST:
                return new Location(loc.getRow(), loc.getColumn() + 1, dir);
            case Direction.SOUTH:
                return new Location(loc.getRow() + 1, loc.getColumn(), dir);
            case Direction.WEST:
                return new Location(loc.getRow(), loc.getColumn() - 1, dir);
            default:
                throw new IllegalStateException();
        }
    }

    private Path generPath(Location start, Location end) {
        Path path = new Path();

        if (start.getLeftDirection() == end.getDirection()) {
            path.addInstruction(Instruction.L);
        } else if (start.getRightDirection() == end.getDirection()) {
            path.addInstruction(Instruction.R);
        } 
        path.addInstruction(Instruction.F);

        return path;
    }
}
