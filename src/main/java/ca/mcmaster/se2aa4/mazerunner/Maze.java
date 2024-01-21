package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();
    public Tile[][] grid;
    private Configuration config;

    public Maze (Configuration config) throws IOException {
        logger.info("Maze Constructor");
        this.config = config;
        Reader file_reader = new FileReader(config.MAZE_FILE());
        BufferedReader buffered_reader = new BufferedReader(file_reader);

        grid = new Tile[config.MAZE_HEIGHT()][config.MAZE_WIDTH()];
        String maze_row = buffered_reader.readLine();
        int grid_row_index = 0;

        while (maze_row != null) {
            char[] maze_row_arr = maze_row.toCharArray();

            for (int i = 0; i < config.MAZE_WIDTH(); i++) {
                if (i >= maze_row_arr.length) {
                    grid[grid_row_index][i] = Tile.EMPTY;
                } else {
                    switch (maze_row_arr[i]) {
                        case '#':
                            grid[grid_row_index][i] = Tile.WALL;
                            break;
                        case ' ':
                            grid[grid_row_index][i]  = Tile.EMPTY;
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid character in maze file: " + maze_row_arr[i]);
                    }
                }
            }

            grid_row_index++;
            maze_row = buffered_reader.readLine();
        }

        buffered_reader.close();
    }

    public Tile getTile(Location loc) {
        return grid[loc.getRow()][loc.getColumn()];
    }

    public Map<Direction, Tile> getNeighbours(Location loc) {
        Map<Direction, Tile> neighbours = new HashMap<>();
        int row = loc.getRow();
        int col = loc.getColumn();
        
        if (row - 1 < 0) {
            neighbours.put(Direction.NORTH, null);
        } else {
            neighbours.put(Direction.NORTH, grid[row-1][col]);
        }

        if (row + 1 >= config.MAZE_HEIGHT()) {
            neighbours.put(Direction.SOUTH, null);
        } else {
            neighbours.put(Direction.SOUTH, grid[row+1][col]);
        }

        if (col - 1 < 0) {
            neighbours.put(Direction.WEST, null);
        } else {
            neighbours.put(Direction.WEST, grid[row][col-1]);
        }

        if (col + 1 >= config.MAZE_WIDTH()) {
            neighbours.put(Direction.EAST, null);
        } else {
            neighbours.put(Direction.EAST, grid[row][col+1]);
        }

        return neighbours;
    }

    public Location findEntry() {
        for (int row = 0; row < config.MAZE_HEIGHT(); row++) {
            if (grid[row][0] == Tile.EMPTY) {
                return new Location(row, 0, Direction.EAST, config);
            }
        }
        throw new IllegalStateException("No entry point found in the maze");
    }

    public Location findExit() {
        for (int row = 0; row < config.MAZE_HEIGHT(); row++) {
            if (grid[row][config.MAZE_WIDTH()-1] == Tile.EMPTY) {
                return new Location(row, config.MAZE_WIDTH()-1, Direction.EAST, config);
            }
        }
        throw new IllegalStateException("No exit point found in the maze");
    }
}
