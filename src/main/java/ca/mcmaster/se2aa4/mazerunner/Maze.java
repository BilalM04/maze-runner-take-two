package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();
    Tile[][] grid;

    public Maze (String maze_input_file) {
        logger.info("Maze Constructor");
    }

    public Tile getTile(Location loc) {
        return null;
    }

    public HashMap<String, Tile> getNeighbours(Location loc){
        return null;
    }

    public Location findEntry(){
        return null;
    }

    public Location findExit() {
        return null;
    }
}
