package ca.mcmaster.se2aa4.mazerunner.configuration;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import ca.mcmaster.se2aa4.mazerunner.path.Path;
import ca.mcmaster.se2aa4.mazerunner.path.PathBuilder;

public record Configuration(String MAZE_FILE, Path PATH_SEQUENCE, Method METHOD) {

    /**
     * Loads and parses command-line arguments to create a Configuration object for maze configuration.
     * Parses input options, including the maze text file and optional maze path for verification.
     *
     * @param args Command-line arguments passed to the program.
     * @return A Configuration object representing the loaded maze configuration.
     * @throws RuntimeException If an error occurs during parsing or loading file.
     */
    public static Configuration load(String[] args) {
        PathBuilder builder = new PathBuilder();
        Options options = new Options();
        options.addOption("i", "input", true, "Maze text file");
        options.addOption("p", "path", true, "Input sequence");
        options.addOption("m", "method", true, "Maze algorithm");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            String file = cmd.getOptionValue("i");
            Path path = null;
            Method method = null;
            if (cmd.hasOption("p")) {
                String stringPath = cmd.getOptionValue("p");
                path = builder.buildPath(stringPath);
            }
            //if (cmd.hasOption("m")) {
                String input = cmd.getOptionValue("m");
                if (input.equals("righthand")) {
                    method = Method.RIGHTHAND;
                } else if (input.equals("bfs")) {
                    method = Method.BFS;
                } else {
                    throw new IllegalArgumentException("Illegal -method: " + input);
                }
            //}
            return new Configuration(file, path, method);
        } catch(ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
