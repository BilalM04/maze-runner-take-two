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
        Options options = new Options();
        options.addOption("i", "input", true, "maze input text file");
        options.addOption("p", "path", false, "maze input text file");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            String input_file = cmd.getOptionValue("i");
            logger.info("**** Reading the maze from file " + input_file);
            Maze maze = new Maze(input_file);
            if (cmd.hasOption("p")) {
                String input_path = cmd.getOptionValue("p");
                logger.info("**** Verifying path");
                maze.verifyPath(input_path);
                logger.info("PATH NOT VERIFIED");
            } else {
                logger.info("**** Computing path");
                maze.findPath();
                logger.info("PATH NOT COMPUTED");
            }
            logger.info("** End of MazeRunner");
            // BufferedReader reader = new BufferedReader(new FileReader(input_file));
            // String line;
            // while ((line = reader.readLine()) != null) {
            //     for (int idx = 0; idx < line.length(); idx++) {
            //         if (line.charAt(idx) == '#') {
            //             logger.info("WALL ");
            //         } else if (line.charAt(idx) == ' ') {
            //             logger.info("PASS ");
            //         }
            //     }
            //     logger.info(System.lineSeparator());
            // }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }
}
