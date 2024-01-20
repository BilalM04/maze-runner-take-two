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
        StringBuilder str = new StringBuilder();
        Location start = maze.findEntry();
        Location end = maze.findExit();
        int row = start.row();
        int col = start.column();
        int end_row = end.row();
        int end_col = end.column();
        str.append("R");
        col++;

        while (row != end_row || col != end_col) {
            if (maze.grid[row+1][col] == Tile.EMPTY) {
                str.append("D");
                row++;
            } else if (maze.grid[row][col+1] == Tile.EMPTY) {
                str.append("R");
                col++;
            } else if (maze.grid[row-1][col] == Tile.EMPTY) {
                str.append("U");
                row--;
            } else {
                str.append("L");
                col--;
            }
        }

        return str.toString();
    }

    public boolean verifyPath(){
        return false;
    }
}
