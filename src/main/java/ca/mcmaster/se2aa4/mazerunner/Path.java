package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Path {
    ArrayList<String> canonical_path;
    ArrayList<String> factorized_path;

    public Path() {
        this.canonical_path = new ArrayList<>();
        this.factorized_path = new ArrayList<>();
    }

    public void addInstruction(char instruction) {
        /* IMPLEMENT LOGIC TO ADD INSTRUCTION */
    }

    public void printCanonicalPath() {
        /* IMPLEMENT LOGIC TO PRINT CANONICAL PATH */
    }

    public void printFactorizedPath() {
         /* IMPLEMENT LOGIC TO PRINT FACTORIZED PATH */
    }
}
