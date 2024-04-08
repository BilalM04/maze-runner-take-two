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

    /**
     * Retrieves the path from the west entry point to the east entry point in the maze 
     * using a BFS algorithm.
     *
     * @param factorized If true, the returned path is factorized.
     *                   If false, the returned path is canonical.
     * @param maze Maze object
     * @return A string representation of the found path.
     */
    public String getPath(Maze maze, boolean factorized) {
        Location start = maze.findWestEntry();
        start.setDirection(Direction.EAST);
        Location end = maze.findEastEntry();
        end.setDirection(Direction.EAST);

        breadthFirstSearch(maze, start);
        Path path = findPath(start, end);

        if (factorized) {
            return path.getFactorizedPath();
        } else {
            return path.getCanonicalPath();
        }
    }

    /**
     * Performs a BFS from the start location and populates the parent and path index.
     * Inspiration from 2C03 textbook: Algorithms 4th Edition by Robert Sedgewick, Kevin Wayne
     *
     * @param start Start location in the maze
     * @param maze Maze object
     */
    private void breadthFirstSearch(Maze maze, Location start) {
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

    /**
     * Constructs the shortest path from the parent and path index.
     * Inspiration from 2C03 textbook: Algorithms 4th Edition by Robert Sedgewick, Kevin Wayne
     *
     * @param start Start location in the maze
     * @param end End Location in the maze
     * @return A Path object representing the shortest path.
     */
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

    /**
     * Given two adjacent Location objects, finds the series of instructions
     * from the start to the end.
     *
     * @param start Start location in the maze
     * @param end End Location in the maze
     * @return A Path object representing the path from start to end.
     */
    private Path getInstructions(Location start, Location end) {
        Path path = new Path();

        if (start.getDirection().getLeftDirection() == end.getDirection()) {
            path.addInstruction(Instruction.L);
        } else if (start.getDirection().getRightDirection() == end.getDirection()) {
            path.addInstruction(Instruction.R);
        } 

        path.addInstruction(Instruction.F);

        return path;
    }
}
