package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();

    public Maze () {
        logger.info("Maze Constructor");
    }

    public void setMaze (String maze_input_file) {
        logger.info("Setting Maze...");
    }

    public int[] findPath() {
        return null;
    }
}
