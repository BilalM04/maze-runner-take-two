package ca.mcmaster.se2aa4.mazerunner.algorithm;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.Map.Entry;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;
import ca.mcmaster.se2aa4.mazerunner.path.Instruction;
import ca.mcmaster.se2aa4.mazerunner.path.Path;
import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

public class BFS implements MazeAlgorithm {

    private Map<Location, Location> parentIndex;
    private Map<Location, Path> pathIndex;
    private Maze maze;

    public BFS(Maze maze) {
        this.maze = maze;
    }
    
    public String getPath(Maze temp, boolean factorized) {
        Location start = maze.findWestEntry();
        start.setDirection(Direction.EAST);
        Location end = maze.findEastEntry();
        end.setDirection(Direction.EAST);

        breadthFirstSearch(start);
        Path path = findPath(start, end);

        if (factorized) {
            return path.getFactorizedPath();
        } else {
            return path.getCanonicalPath();
        }
    }

    private void breadthFirstSearch(Location start) {
        parentIndex = new HashMap<>();
        pathIndex = new HashMap<>();

        parentIndex.put(start, start);
        pathIndex.put(start, new Path());
        Queue<Location> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Location current = queue.poll();
            Map<Direction, Tile> neighbours = maze.getNeighbours(current);

            for (Entry<Direction, Tile> entry : neighbours.entrySet()) {
                if (entry.getValue() == Tile.EMPTY) {
                    Location neighbour = current.getAdjacentLocation(entry.getKey());
                    if (!parentIndex.containsKey(neighbour)) {
                        queue.add(neighbour);
                        parentIndex.put(neighbour, current);
                        pathIndex.put(neighbour, getInstructions(current, neighbour));
                    }
                }
            }
        }
    }

    private Path findPath(Location start, Location end) {
        Path path = new Path();
        Stack<Location> stack = new Stack<>();

        if (!pathIndex.containsKey(end)) {
            return path;
        }

        Location current = end;
        while (!current.equals(start)) {
            stack.add(current);
            current = parentIndex.get(current);
        }

        stack.add(start);
        while (!stack.isEmpty()) {
            path.appendPath(pathIndex.get(stack.pop()));
        }

        return path;
    }

    private Path getInstructions(Location start, Location end) {
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
