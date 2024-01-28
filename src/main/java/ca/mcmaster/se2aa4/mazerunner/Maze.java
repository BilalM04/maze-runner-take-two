package ca.mcmaster.se2aa4.mazerunner;

import java.util.Map;

public interface Maze {

    public Map<Direction, Tile> getNeighbours(Location loc);

    public Location findWestEntry();

    public Location findEastEntry();

    public Tile getTile(Location loc) throws IndexOutOfBoundsException;

    public int width();

    public int height();
}
