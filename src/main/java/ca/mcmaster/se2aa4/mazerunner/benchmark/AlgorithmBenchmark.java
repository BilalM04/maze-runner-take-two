package ca.mcmaster.se2aa4.mazerunner.benchmark;

import java.io.IOException;

import ca.mcmaster.se2aa4.mazerunner.algorithm.BFS;
import ca.mcmaster.se2aa4.mazerunner.algorithm.MazeAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.algorithm.RightHand;
import ca.mcmaster.se2aa4.mazerunner.configuration.Method;
import ca.mcmaster.se2aa4.mazerunner.maze.ListMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;

public class AlgorithmBenchmark implements Benchmark {
    private Method method;
    private Method baseline;
    private String file;
    private Maze maze;

    private double loadTime;
    private double methodTime;
    private double baselineTime;
    private double speedup;

    private String methodPath;
    private String baselinePath;

    public AlgorithmBenchmark(Method method, Method baseline, String file) {
        this.method = method;
        this.baseline = baseline;
        this.file = file;
    }

    @Override
    public void runBenchmark(){
        try {
            measureLoadTime();
            measureMethodTime();
            measureBaselineTime();
            measureSpeedup();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public String generateReport() {
        StringBuilder builder = new StringBuilder();
        builder.append("Time spent loading the maze: ").append(String.format("%.2f", loadTime)).append(" ms\n");
        builder.append("Method Time: ").append(String.format("%.2f", methodTime)).append(" ms\n");
        builder.append("Baseline Time: ").append(String.format("%.2f", baselineTime)).append(" ms\n");
        builder.append("Speedup: ").append(String.format("%.2f", speedup));
        return builder.toString();
    }

    private void measureLoadTime() throws IOException{
        double start = System.nanoTime();
        this.maze = new ListMaze(file);
        double end = System.nanoTime();
        this.loadTime = (end - start) / 1.0E6;
    }

    private void measureMethodTime() {
        double start = System.nanoTime();
        this.methodPath = solveMaze(this.method);
        double end = System.nanoTime();
        this.methodTime = (end - start) / 1.0E6;
    }

    private void measureBaselineTime() {
        double start = System.nanoTime();
        this.baselinePath = solveMaze(this.baseline);
        double end = System.nanoTime();
        this.baselineTime = (end - start) / 1.0E6;
    }

    private void measureSpeedup() {
        this.speedup = (baselinePath.length() * 1.0 / methodPath.length());
    }

    private String solveMaze(Method method) {
        switch (method) {
            case Method.RIGHTHAND:
                MazeAlgorithm righthand = new RightHand();
                return righthand.getPath(maze, false).replace(" ", "");
            case Method.BFS:
                MazeAlgorithm bfs = new BFS();
                return bfs.getPath(maze, false).replace(" ", "");
            default:
                throw new IllegalArgumentException();
        }
    }
}
