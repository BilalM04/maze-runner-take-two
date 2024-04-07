package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;

public class LocationTest {
    private Location loc;

    @BeforeEach
    public void setUp() {
        this.loc = new Location(6, 7, Direction.NORTH);
    }

    @Test
    public void rowTest() {
        loc.setRow(98);
        assertEquals(98, loc.getRow());
        loc.setRow(7);
        assertEquals(7, loc.getRow());
        loc.setRow(-45);
        assertNotEquals(-78, loc.getRow());
    }

    @Test
    public void columnTest() {
        loc.setColumn(14);
        assertEquals(14, loc.getColumn());
        loc.setColumn(190);
        assertEquals(190, loc.getColumn());
        loc.setColumn(-78);
        assertNotEquals(-4, loc.getColumn());
    }

    @Test
    public void directionTest() {
        loc.setDirection(Direction.SOUTH);
        assertEquals(Direction.SOUTH, loc.getDirection());
        loc.setDirection(Direction.EAST);
        assertEquals(Direction.EAST, loc.getDirection());
        loc.setDirection(Direction.WEST);
        assertEquals(Direction.WEST, loc.getDirection());
        loc.setDirection(Direction.NORTH);
        assertEquals(Direction.NORTH, loc.getDirection());
    }

    @Test
    public void adjacentTest() {
        assertEquals(new Location(5, 7, Direction.NORTH), loc.getAdjacentLocation(Direction.NORTH));
        assertEquals(new Location(6, 8, Direction.EAST), loc.getAdjacentLocation(Direction.EAST));
        assertEquals(new Location(7, 7, Direction.SOUTH), loc.getAdjacentLocation(Direction.SOUTH));
        assertEquals(new Location(6, 6, Direction.WEST), loc.getAdjacentLocation(Direction.WEST));
    }

    @Test
    public void equalsTest() {
        assertEquals(new Location(6, 7, Direction.NORTH), loc);
    }
    
}
