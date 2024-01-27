package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        Configuration config = Configuration.load(args);
        try {
            MazeAlgorithm right_hand_algo = new RightHand(config);
            Verifier path_verifier = new Verifier(config);
            if (config.PATH_SEQUENCE() != null) {
                logger.info("**** Verifying path");
                System.out.println(config.PATH_SEQUENCE().getCanonicalPath());
                boolean isValid = path_verifier.verifyPath(config.PATH_SEQUENCE());
                System.out.println(isValid);
                logger.info("PATH NOT VERIFIED");
            } else {
                logger.info("**** Computing path");
                String west_to_east = right_hand_algo.getPath(true);
                System.out.println(west_to_east);
            }
            logger.info("** End of MazeRunner");
        } catch(Exception e) {
            logger.error(e);
        }
    }
}