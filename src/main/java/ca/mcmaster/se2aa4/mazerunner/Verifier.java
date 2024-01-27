package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class Verifier {
    Maze maze;

    public Verifier(Configuration config) throws IOException {
        maze = new Maze(config);
    }

    public boolean verifyPath(Path path){
        return checkWestToEast(path) || checkEastToWest(path);
    }

    private boolean checkWestToEast(Path path) {
        Location start = maze.findWestEntry();
        start.setDirection(Direction.EAST);
        Location end = maze.findEastEntry();
        end.setDirection(Direction.EAST);
        return traverseMaze(path, start, end);
    }

    private boolean checkEastToWest(Path path) {
        Location start = maze.findEastEntry();
        start.setDirection(Direction.WEST);
        Location end = maze.findWestEntry();
        end.setDirection(Direction.WEST);
        return traverseMaze(path, start, end);
    }

    private boolean traverseMaze(Path path, Location start, Location end) {
        Explorer maze_explorer = new MazeExplorer(this.maze, start);
        boolean is_valid_instruction;

        for (int i = 0; i < path.length(); i++) {
            is_valid_instruction = true;
            switch (path.getInstruction(i)) {
                case Instruction.F:
                    is_valid_instruction = maze_explorer.goForward();
                    break;
                case Instruction.R:
                    maze_explorer.turnRight();
                    break;
                case Instruction.L:
                    maze_explorer.turnLeft();
                    break;
                default:
                    throw new IllegalStateException();
            }

            if (!is_valid_instruction) {
                return false;
            }
        }

        return maze_explorer.isAt(end);
    }
}
