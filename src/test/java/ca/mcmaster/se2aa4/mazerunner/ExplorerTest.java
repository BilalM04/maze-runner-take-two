package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.maze.ListMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;

public class ExplorerTest {
    private Maze maze;
    private Explorer explorer;

    @BeforeEach
    public void setUp() throws IOException {
        this.maze = new ListMaze("./examples/small.maz.txt");
        this.explorer = new MazeExplorer(maze, maze.findWestEntry());
    }

    @Test
    public void testSequence1() {
        assertFalse(explorer.goRight());
        assertFalse(explorer.goLeft());
        assertTrue(explorer.goForward());
        assertFalse(explorer.goForward());
        assertTrue(explorer.isAt(new Location(8, 1, Direction.EAST)));
    }

    @Test
    public void testSequence2() {
        assertTrue(explorer.goForward());
        explorer.turnLeft();
        assertTrue(explorer.goForward());
        explorer.turnRight();
        assertTrue(explorer.goForward());
        assertTrue(explorer.isAt(new Location(7, 2, Direction.EAST)));
    }

    @Test
    public void testSequence3() {
        assertTrue(explorer.goForward());
        assertTrue(explorer.goLeft());
        assertTrue(explorer.goRight());
        assertTrue(explorer.isAt(new Location(7, 2, Direction.EAST)));
    }
}
