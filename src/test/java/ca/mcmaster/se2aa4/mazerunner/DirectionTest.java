package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.position.Direction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {
    private Direction north = Direction.NORTH;
    private Direction east = Direction.EAST;
    private Direction south = Direction.SOUTH;
    private Direction west = Direction.WEST;

    @Test
    public void testLeft() {
        assertEquals(Direction.WEST, north.getLeftDirection());
        assertEquals(Direction.NORTH, east.getLeftDirection());
        assertEquals(Direction.EAST, south.getLeftDirection());
        assertEquals(Direction.SOUTH, west.getLeftDirection());
    }

    @Test
    public void testRight() {
        assertEquals(Direction.EAST, north.getRightDirection());
        assertEquals(Direction.SOUTH, east.getRightDirection());
        assertEquals(Direction.WEST, south.getRightDirection());
        assertEquals(Direction.NORTH, west.getRightDirection());
    }

    @Test
    public void testOpposite() {
        assertEquals(Direction.SOUTH, north.getOppositeDirection());
        assertEquals(Direction.WEST, east.getOppositeDirection());
        assertEquals(Direction.NORTH, south.getOppositeDirection());
        assertEquals(Direction.EAST, west.getOppositeDirection());
    }
}
