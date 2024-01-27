package ca.mcmaster.se2aa4.mazerunner;

public interface Explorer {

    public boolean goRight();

    public boolean goLeft();

    public boolean goForward();

    public boolean goBackward();

    public void turnLeft();

    public void turnRight();

    public boolean isAt(Location loc);
}
