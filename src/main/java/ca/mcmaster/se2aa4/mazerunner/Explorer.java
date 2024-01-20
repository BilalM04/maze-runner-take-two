package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class Explorer {
    Maze maze;
    Location loc;
    Path path;

    public Explorer(Configuration config) throws IOException {
        this.maze = new Maze(config);
        this.loc = maze.findEntry();
        this.path = new Path();
    }

    public String getPath() {
        path.addInstruction(Instruction.F);
        path.addInstruction(Instruction.R);
        return path.getCanonicalPath();
    }

    public Path findPath() {
        // StringBuilder str = new StringBuilder();
        // Location start = maze.findEntry();
        // Location end = maze.findExit();
        // int row = start.row();
        // int col = start.column();
        // int end_row = end.row();
        // int end_col = end.column();
        // str.append("R");
        // col++;

        // while (row != end_row || col != end_col) {
        //     if (maze.grid[row+1][col] == Tile.EMPTY) {
        //         str.append("D");
        //         row++;
        //     } else if (maze.grid[row][col+1] == Tile.EMPTY) {
        //         str.append("R");
        //         col++;
        //     } else if (maze.grid[row-1][col] == Tile.EMPTY) {
        //         str.append("U");
        //         row--;
        //     } else {
        //         str.append("L");
        //         col--;
        //     }
        // }

        // return str.toString();
        return null;
    }

    private boolean goRight() {
        return false;
    }

    private boolean goLeft() {
        return false;
    }

    private boolean goForward() {
        return false;
    }

    private boolean goBackward() {
        return false;
    }

    public boolean verifyPath(){
        return false;
    }
}
