package ca.mcmaster.se2aa4.mazerunner.explorer;

import ca.mcmaster.se2aa4.mazerunner.position.Location;

public interface Explorer {

    public boolean goRight();

    public boolean goLeft();

    public boolean goForward();

    public boolean goBackward();

    public void turnLeft();

    public void turnRight();

    public boolean isAt(Location loc);
}
