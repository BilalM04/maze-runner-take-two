package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class Explorer {
    Maze maze;
    Location loc;
    Path path;

    public Explorer(String file_path) throws IOException {
        this.maze = new Maze(file_path);
    }

    public String findPath() {
        return "";
    }

    public boolean verifyPath(String path){
        return false;
    }
}
