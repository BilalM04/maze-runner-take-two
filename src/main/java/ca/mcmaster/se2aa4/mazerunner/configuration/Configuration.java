package ca.mcmaster.se2aa4.mazerunner.configuration;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import ca.mcmaster.se2aa4.mazerunner.path.Path;
import ca.mcmaster.se2aa4.mazerunner.path.PathBuilder;

public record Configuration(String MAZE_FILE, Path PATH_SEQUENCE, Method METHOD, Method BASELINE) {

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
        CommandLineParser parser = new DefaultParser();

        options.addOption("i", "input", true, "Maze text file");
        options.addOption("p", "path", true, "Input sequence");
        options.addOption("m", "method", true, "Maze algorithm");
        options.addOption("b", "baseline", true, "Benchmark baseline");
        
        try {
            CommandLine cmd = parser.parse(options, args);
            String file = cmd.getOptionValue("i");
            Path path = null;
            Method method = Method.RIGHTHAND;
            Method baseline = null;
            
            if (cmd.hasOption("p")) {
                String stringPath = cmd.getOptionValue("p");
                path = builder.buildPath(stringPath);
            }

            if (cmd.hasOption("b")) {
                String baselineInput = cmd.getOptionValue("b").toLowerCase();
                baseline = toMethod(baselineInput);
            }

            if (cmd.hasOption("m")) {
                String methodInput = cmd.getOptionValue("m").toLowerCase();
                method = toMethod(methodInput);
            }

            return new Configuration(file, path, method, baseline);
        } catch(ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static Method toMethod(String str) {
        if (str.equals("righthand")) {
            return Method.RIGHTHAND;
        } else if (str.equals("bfs")) {
            return Method.BFS;
        } else {
            throw new IllegalArgumentException("Illegal method: " + str); 
        }
    }
}
