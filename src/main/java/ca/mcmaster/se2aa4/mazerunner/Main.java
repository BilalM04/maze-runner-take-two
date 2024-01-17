package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        Configuration config = Configuration.load(args);
        try {
            Maze maze = new Maze(config.FILE_PATH());
            if (config.INPUT_PATH() != null) {
                logger.info("**** Verifying path");
                boolean valid = maze.verifyPath(config.INPUT_PATH());
                logger.info("PATH NOT VERIFIED");
            } else {
                logger.info("**** Computing path");
                Path factorized_path = maze.findPath();
                logger.info("PATH NOT COMPUTED");
            }
            logger.info("** End of MazeRunner");
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }
}
