package ca.mcmaster.se2aa4.mazerunner.maze;

import java.util.Map;

import ca.mcmaster.se2aa4.mazerunner.position.Direction;
import ca.mcmaster.se2aa4.mazerunner.position.Location;

public interface Maze {

    public Map<Direction, Tile> getNeighbours(Location loc);

    public Location findWestEntry();

    public Location findEastEntry();

    public Tile getTile(Location loc) throws IndexOutOfBoundsException;

    public int width();

    public int height();
}
