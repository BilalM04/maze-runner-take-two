package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public record Configuration(String MAZE_FILE, Path INPUT_PATH, int MAZE_WIDTH, int MAZE_HEIGHT) {

    private static final Logger logger = LogManager.getLogger();

    /* POTENTIALLY IMPLEMENT EXCEPTION LOGIC */

    public static Configuration load(String[] args) {
        Options options = new Options();
        options.addOption("i", "input", true, "Maze text file");
        options.addOption("p", "path", true, "Maze path for verification");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            String MAZE_FILE = cmd.getOptionValue("i");
            Path INPUT_PATH = null;
            logger.info("**** Reading the maze from file " + MAZE_FILE);
            if (cmd.hasOption("p")) {
                String string_path = cmd.getOptionValue("p");
                INPUT_PATH = storePath(string_path);
            }
            int MAZE_HEIGHT = mazeHeight(MAZE_FILE);
            int MAZE_WIDTH = mazeWidth(MAZE_FILE);
            return new Configuration(MAZE_FILE, INPUT_PATH, MAZE_WIDTH, MAZE_HEIGHT);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static int mazeWidth(String file_path) throws IOException {
        Reader file_reader = new FileReader(file_path);
        BufferedReader buffered_reader = new BufferedReader(file_reader);
        String maze_row = buffered_reader.readLine();
        buffered_reader.close();
        return maze_row.length();
    }

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

    private static Path storePath(String path) {
        Path user_path = new Path();

        for (int i = 0; i < path.length(); i++) {
            int repeat = 1;
            int end = i;

            if (path.charAt(i) >= 48 && path.charAt(i) <= 57) {
                end++;

                while (path.charAt(end) >= 48 && path.charAt(end) <= 57) {
                    end++;
                }
                
                repeat = Integer.parseInt(path.substring(i, end));
                i = end;
            }

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
                    case ' ':
                        continue;
                    default:
                        throw new IllegalArgumentException("Invalid character in path: " + path.charAt(end));
                }
            }
        }

        return user_path;
    }
}
