package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.algorithm.MazeAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.algorithm.RightHand;
import ca.mcmaster.se2aa4.mazerunner.configuration.Configuration;
import ca.mcmaster.se2aa4.mazerunner.verification.PathVerifier;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Configuration config = Configuration.load(args);
        try {
            MazeAlgorithm right_hand_algo = new RightHand(config.MAZE_FILE());
            PathVerifier path_verifier = new PathVerifier(config.MAZE_FILE());
            if (config.PATH_SEQUENCE() != null) {
                boolean is_valid = path_verifier.verifyPath(config.PATH_SEQUENCE());
                if (is_valid) {
                    System.out.println("correct path");
                } else {
                    System.out.println("incorrect path");
                }
            } else {
                String maze_path = right_hand_algo.getPath(true);
                System.out.println(maze_path);
            }
        } catch(RuntimeException | IOException e) {
            System.out.println(e);
        }
    }
}