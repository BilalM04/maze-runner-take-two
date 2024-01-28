package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public record Configuration(String MAZE_FILE, Path PATH_SEQUENCE, int MAZE_WIDTH, int MAZE_HEIGHT) {

    /**
     * Loads and parses command-line arguments to create a Configuration object for maze configuration.
     * Parses input options, including the maze text file and optional maze path for verification.
     *
     * @param args Command-line arguments passed to the program.
     * @return A Configuration object representing the loaded maze configuration.
     * @throws RuntimeException If an error occurs during parsing or loading file.
     */
    public static Configuration load(String[] args) {
        Options options = new Options();
        options.addOption("i", "input", true, "Maze text file");
        options.addOption("p", "path", true, "Input sequence");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            String MAZE_FILE = cmd.getOptionValue("i");
            Path PATH_SEQUENCE = null;
            if (cmd.hasOption("p")) {
                String string_path = cmd.getOptionValue("p");
                PATH_SEQUENCE = storePath(string_path);
            }
            int MAZE_HEIGHT = mazeHeight(MAZE_FILE);
            int MAZE_WIDTH = mazeWidth(MAZE_FILE);
            return new Configuration(MAZE_FILE, PATH_SEQUENCE, MAZE_WIDTH, MAZE_HEIGHT);
        } catch(ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Determines the width of the maze by reading the first line from the specified file.
     *
     * @param file_path The path to the file containing the maze.
     * @return The width of the maze, which is the length of the first line in the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private static int mazeWidth(String file_path) throws IOException {
        Reader file_reader = new FileReader(file_path);
        BufferedReader buffered_reader = new BufferedReader(file_reader);
        String maze_row = buffered_reader.readLine();
        buffered_reader.close();
        return maze_row.length();
    }

    /**
     * Determines the height of the maze by counting the number of lines in the specified file.
     *
     * @param file_path The path to the file containing the maze configuration.
     * @return The height of the maze, which is the number of lines in the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private static int mazeHeight(String file_path) throws IOException {
        Reader file_reader = new FileReader(file_path);
        BufferedReader buffered_reader = new BufferedReader(file_reader);
        String maze_row = buffered_reader.readLine();
        int count = 0;

        while (maze_row != null) {
            count++;
            maze_row = buffered_reader.readLine();
        }

        buffered_reader.close();
        return count;
    }

    /**
     * Parses and stores a user-provided path string into a Path object.
     *
     * @param path The user-provided path string to be parsed and stored.
     * @return A Path object representing the path.
     * @throws IllegalArgumentException If an invalid character is encountered in the path string.
     */
    private static Path storePath(String path) {
        Path user_path = new Path();
        path = path.replace(" ", "");

        for (int i = 0; i < path.length(); i++) {
            int repeat = 1;
            int end = i;

            // extracts number portion from input sequence (if factorized)
            if (path.charAt(i) >= 48 && path.charAt(i) <= 57) {
                end++;
                while (path.charAt(end) >= 48 && path.charAt(end) <= 57) {
                    end++;
                }
                repeat = Integer.parseInt(path.substring(i, end));
                i = end;
            }

            // adds the intruction to the path object and repeats based on the number portion
            for (int r = 0; r < repeat; r++) {
                switch (path.charAt(end)) {
                    case 'F':
                        user_path.addInstruction(Instruction.F);
                        break;
                    case 'L':
                        user_path.addInstruction(Instruction.L);
                        break;
                    case 'R':
                        user_path.addInstruction(Instruction.R);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid character in path: " + path.charAt(end));
                }
            }
        }

        return user_path;
    }
}
