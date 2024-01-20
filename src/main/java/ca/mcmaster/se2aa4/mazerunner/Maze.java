package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();
    private static List<Tile[]> grid;

    public Maze (String maze_input_file) throws IOException {
        logger.info("Maze Constructor");
        Reader file_reader = new FileReader(maze_input_file);
        BufferedReader buffered_reader = new BufferedReader(file_reader);

        grid = new ArrayList<>(); // array list to store rows
        String maze_row = buffered_reader.readLine();
        int maze_width = maze_row.length();
        int grid_row_index = 0;

        while (maze_row != null) {
            char[] maze_row_arr = maze_row.toCharArray();
            grid.add(new Tile[maze_width]);

            for (int i = 0; i < maze_row_arr.length; i++) {
                switch (maze_row_arr[i]) {
                    case '#':
                        grid.get(grid_row_index)[i] = Tile.WALL;
                        break;
                    case ' ':
                        grid.get(grid_row_index)[i] = Tile.EMPTY;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid character in maze file: " + maze_row_arr[i]);
                }
            }

            grid_row_index++;
            maze_row = buffered_reader.readLine();
        }

        buffered_reader.close();
    }

    public Tile getTile(Location loc) {
        return null;
    }

    public HashMap<Direction, Tile> getNeighbours(Location loc) {
        return null;
    }

    public Location findEntry() {
        return null;
    }

    public Location findExit() {
        return null;
    }
}
