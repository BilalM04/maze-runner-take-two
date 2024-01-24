package ca.mcmaster.se2aa4.mazerunner;

abstract class MazeAlgorithm {
    Explorer maze_explorer;

    abstract String getEastToWest(boolean factorized);

    abstract String getWestToEast(boolean factorized);
}
