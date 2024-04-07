package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.ListMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.path.PathBuilder;
import ca.mcmaster.se2aa4.mazerunner.verification.PathVerifier;
import ca.mcmaster.se2aa4.mazerunner.verification.Verifier;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class VerifierTest {
    private PathBuilder builder = new PathBuilder();

    @Test
    public void testSmall() throws IOException{
        assertTrue(result("./examples/small.maz.txt", "F L F R 2F L 6F R 4F R 2F L 2F R 2F L F"));
    }

    @Test
    public void testDirect() throws IOException{
        assertTrue(result("./examples/direct.maz.txt", "F R 2F L 3F R F L F R F L 2F"));
    }

    @Test
    public void testStraight() throws IOException{
        assertTrue(result("./examples/straight.maz.txt", "FFFF"));
    }

    @Test
    public void testTiny() throws IOException{
        assertFalse(result("./examples/tiny.maz.txt", "FFFF LL RR FF"));
    }

    private boolean result(String file, String path) throws IOException {
        Maze maze = new ListMaze(file);
        Verifier verifier = new PathVerifier(maze, builder.buildPath(path));
        return verifier.verify();
    }
}
