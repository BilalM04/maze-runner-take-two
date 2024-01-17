package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public record Configuration(String FILE_PATH, String INPUT_PATH) {

    private static final Logger logger = LogManager.getLogger();

    /* POTENTIALLY IMPLEMENT EXCEPTION LOGIC */

    public static Configuration load(String[] args) {
        /* IMPLEMENT LOGIC TO PARSE COMMAND LINE AND STORE INPUTS */

        Options options = new Options();
        options.addOption("i", "input", true, "maze input text file");
        options.addOption("p", "path", false, "maze input text file");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            String INPUT_FILE = cmd.getOptionValue("i");
            String INPUT_PATH = null;
            logger.info("**** Reading the maze from file " + INPUT_FILE);
            if (cmd.hasOption("p")) {
                INPUT_PATH = cmd.getOptionValue("p");
            }
            return new Configuration(INPUT_FILE, INPUT_PATH);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
