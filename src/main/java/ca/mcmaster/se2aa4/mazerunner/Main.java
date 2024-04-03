package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import ca.mcmaster.se2aa4.mazerunner.algorithm.BFS;
import ca.mcmaster.se2aa4.mazerunner.algorithm.MazeAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.algorithm.RightHand;
import ca.mcmaster.se2aa4.mazerunner.benchmark.AlgorithmBenchmark;
import ca.mcmaster.se2aa4.mazerunner.benchmark.Benchmark;
import ca.mcmaster.se2aa4.mazerunner.configuration.Configuration;
import ca.mcmaster.se2aa4.mazerunner.configuration.Method;
import ca.mcmaster.se2aa4.mazerunner.maze.ListMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.verification.PathVerifier;
import ca.mcmaster.se2aa4.mazerunner.verification.Verifier;

public class Main {

    public static void main(String[] args) {
        Configuration config = Configuration.load(args);
        try {
            if (config.BASELINE() != null) {
                Benchmark benchmark = new AlgorithmBenchmark(config.METHOD(), config.BASELINE(), config.MAZE_FILE());
                benchmark.runBenchmark();
                System.out.println(benchmark.generateReport());
            } else {
                Maze maze = new ListMaze(config.MAZE_FILE());
                if (config.PATH_SEQUENCE() != null) {
                    Verifier pathVerifier = new PathVerifier(maze, config.PATH_SEQUENCE());
                    if (pathVerifier.verify()) {
                        System.out.println("correct path");
                    } else {
                        System.out.println("incorrect path");
                    }
                } else {
                    System.out.println(solveMaze(config.METHOD(), maze));
                }
            }
        } catch(RuntimeException | IOException e) {
            System.err.println(e);
        }
    }

    private static String solveMaze(Method method, Maze maze) {
        switch (method) {
            case Method.RIGHTHAND:
                MazeAlgorithm righthand = new RightHand();
                return righthand.getPath(maze, true);
            case Method.BFS:
                MazeAlgorithm bfs = new BFS();
                return bfs.getPath(maze, true);
            default:
                throw new IllegalArgumentException();
        }
    }
}