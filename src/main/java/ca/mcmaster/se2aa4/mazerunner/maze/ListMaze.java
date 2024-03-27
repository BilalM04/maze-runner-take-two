package ca.mcmaster.se2aa4.mazerunner.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

public class ListMaze implements Maze {
    private final List<List<Tile>> grid;
    private final int width;
    private final int height;

    /**
     * Constructs an ArrayMaze object based on the provided configuration.
     * The constructor reads a maze file specified in the configuration,
     * processes it, and initializes the grid of tiles accordingly in a 2D array.
     *
     * @param config The configuration object containing maze-related settings.
     * @throws IOException If an I/O error occurs while reading the maze file.
     * @throws IllegalArgumentException If an invalid character is encountered in the maze file.
     */
    public ListMaze (String filePath) throws IOException {
        this.grid = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String mazeRow = bufferedReader.readLine();
        this.width = mazeRow.length();
        int gridRowIndex = 0;

        while (mazeRow != null) {
            char[] mazeRowArr = mazeRow.toCharArray();
            this.grid.add(new ArrayList<>());

            for (int i = 0; i < width; i++) {
                if (i >= mazeRowArr.length) {
                    grid.get(gridRowIndex).add(Tile.EMPTY);
                } else {
                    switch (mazeRowArr[i]) {
                        case '#':
                            grid.get(gridRowIndex).add(Tile.WALL);
                            break;
                        case ' ':
                            grid.get(gridRowIndex).add(Tile.EMPTY);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid character in maze file: " + mazeRowArr[i]);
                    }
                }
            }

            gridRowIndex++;
            mazeRow = bufferedReader.readLine();
        }

        bufferedReader.close();
        this.height = grid.size();
    }

    /**
     * Retrieves the tile at the specified location in the maze grid.
     *
     * @param loc The location for which to retrieve the tile.
     * @return The tile at the specified location.
     * @throws IllegalArgumentException If the location is outside the bounds of the maze grid.
     */
    public Tile getTile(Location loc) {
        if (loc.getRow() < 0 || loc.getRow() >= height || loc.getColumn() < 0 || loc.getColumn() >= width) {
            throw new IllegalArgumentException();
        }
        return grid.get(loc.getRow()).get(loc.getColumn());
    }

    /**
     * Retrieves the neighboring tiles of the specified location in the maze grid.
     * Neighbors are represented as a map of directions to tiles.
     * If a neighbor in a specific direction is outside the maze bounds, it is set to null.
     *
     * @param loc The location for which to retrieve neighbors.
     * @return A map of directions to tiles representing the neighboring tiles.
     * @throws IndexOutOfBoundsException If the specified location is outside the bounds of the maze grid.
     */
    public Map<Direction, Tile> getNeighbours(Location loc) {
        Map<Direction, Tile> neighbours = new HashMap<>();
        int row = loc.getRow();
        int col = loc.getColumn();

        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new IndexOutOfBoundsException();
        }
        
        if (row - 1 < 0) {
            neighbours.put(Direction.NORTH, null);
        } else {
            neighbours.put(Direction.NORTH, grid.get(row - 1).get(col));
        }

        if (row + 1 >= height) {
            neighbours.put(Direction.SOUTH, null);
        } else {
            neighbours.put(Direction.SOUTH, grid.get(row + 1).get(col));
        }

        if (col - 1 < 0) {
            neighbours.put(Direction.WEST, null);
        } else {
            neighbours.put(Direction.WEST, grid.get(row ).get(col - 1));
        }

        if (col + 1 >= width) {
            neighbours.put(Direction.EAST, null);
        } else {
            neighbours.put(Direction.EAST, grid.get(row).get(col + 1));
        }

        return neighbours;
    }

    /**
     * Finds the entry point on the west side of the maze.
     *
     * @return The location of the west entry point.
     * @throws IllegalStateException If no west entry point is found in the maze.
     */
    public Location findWestEntry() {
        for (int row = 0; row < height; row++) {
            if (grid.get(row).get(0) == Tile.EMPTY) {
                return new Location(row, 0, Direction.EAST);
            }
        }
        throw new IllegalStateException("No West entry point found in the maze");
    }

    /**
     * Finds the entry point on the east side of the maze.
     *
     * @return The location of the east entry point.
     * @throws IllegalStateException If no east entry point is found in the maze.
     */
    public Location findEastEntry() {
        for (int row = 0; row < height; row++) {
            if (grid.get(row).get(width - 1) == Tile.EMPTY) {
                return new Location(row, width - 1, Direction.EAST);
            }
        }
        throw new IllegalStateException("No East entry point found in the maze");
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }
}
