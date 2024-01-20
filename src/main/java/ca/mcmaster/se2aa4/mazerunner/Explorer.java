package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class Explorer {
    Maze maze;
    Location loc;
    Path path;

    public Explorer(Configuration config) throws IOException {
        this.maze = new Maze(config);
    }

    public String findPath() {
        return "";
    }

    public boolean verifyPath(){
        return false;
    }
}
