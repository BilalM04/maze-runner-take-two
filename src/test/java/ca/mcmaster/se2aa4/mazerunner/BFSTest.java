package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.algorithm.BFS;
import ca.mcmaster.se2aa4.mazerunner.algorithm.MazeAlgorithm;
import ca.mcmaster.se2aa4.mazerunner.maze.ListMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class BFSTest {
    private MazeAlgorithm bfs = new BFS();

    @Test
    public void testSmall() throws IOException{
        assertEquals("F L F R 2F L 6F R 4F R 2F L 2F R 2F L F ", result("./examples/small.maz.txt"));
    }

    @Test
    public void testStraight() throws IOException{
        assertEquals("4F ", result("./examples/straight.maz.txt"));
    }

    @Test
    public void testTiny() throws IOException{
        assertNotEquals("FFFF LL RR FF", result("./examples/tiny.maz.txt"));
    }

    private String result(String file) throws IOException {
        Maze maze = new ListMaze(file);
        return bfs.getPath(maze, true);
    }
}