package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Configuration config = Configuration.load(args);
        try {
            MazeAlgorithm right_hand_algo = new RightHand(config);
            Verifier path_verifier = new Verifier(config);
            if (config.PATH_SEQUENCE() != null) {
                boolean is_valid = path_verifier.verifyPath(config.PATH_SEQUENCE());
                if (is_valid) {
                    System.out.println("Correct.");
                } else {
                    System.out.println("Incorrect.");
                }
            } else {
                String maze_path = right_hand_algo.getPath(true);
                System.out.println(maze_path);
            }
        } catch(RuntimeException | IOException e) {
            logger.error(e);
        }
    }
}