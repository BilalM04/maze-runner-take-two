package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        Configuration config = Configuration.load(args);
        try {
            Explorer maze_explorer = new Explorer(config);
            if (config.INPUT_PATH() != null) {
                logger.info("**** Verifying path");
                //boolean isValid = maze_explorer.verifyPath();
                System.out.println(config.INPUT_PATH().getCanonicalPath());
                logger.info("PATH NOT VERIFIED");
            } else {
                logger.info("**** Computing path");
                String factorized_path = maze_explorer.getWestToEast(true);
                String path = maze_explorer.getEastToWest(true);
                logger.info(factorized_path);
                logger.info(path);
            }
            logger.info("** End of MazeRunner");
        } catch(Exception e) {
            logger.error(e);
        }
    }
}
