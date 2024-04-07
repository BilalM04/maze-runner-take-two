package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.ListMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.Tile;
import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;

public class ListMazeTest {
    Maze maze;

    @BeforeEach
    public void setUp() throws IOException {
        this.maze = new ListMaze("./examples/small.maz.txt");
    }

    @Test
    public void testGetTile() {
        assertEquals(Tile.EMPTY, maze.getTile(new Location(4, 1, Direction.NORTH)));
        assertEquals(Tile.WALL, maze.getTile(new Location(0, 0, Direction.NORTH)));
        assertEquals(Tile.EMPTY, maze.getTile(new Location(2, 7, Direction.NORTH)));
        assertEquals(Tile.WALL, maze.getTile(new Location(4, 7, Direction.NORTH)));
    }

    @Test
    public void testNeighbours() {
        Map<Direction, Tile> neighbours = maze.getNeighbours(new Location(5, 7, null));
        assertEquals(Tile.WALL, neighbours.get(Direction.NORTH));
        assertEquals(Tile.WALL, neighbours.get(Direction.EAST));
        assertEquals(Tile.WALL, neighbours.get(Direction.SOUTH));
        assertEquals(Tile.EMPTY, neighbours.get(Direction.WEST));
    }

    @Test
    public void testWestEntry() {
        assertEquals(new Location(8, 0, Direction.EAST), maze.findWestEntry());
    }

    @Test
    public void testEastEntry() {
        assertEquals(new Location(5, 10, Direction.EAST), maze.findEastEntry());
    }

    @Test
    public void testWidth() {
        assertEquals(11, maze.width());
    }

    @Test
    public void testHeight() {
        assertEquals(11, maze.height());
    }
    

}
