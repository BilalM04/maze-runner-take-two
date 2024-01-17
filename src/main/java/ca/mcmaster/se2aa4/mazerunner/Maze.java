package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();

    public Maze (String maze_input_file) {
        logger.info("Maze Constructor");
    }

    public Path findPath() {
        return null;
    }

    public boolean verifyPath(String path) {
        return false;
    }
}
