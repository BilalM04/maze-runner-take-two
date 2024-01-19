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

    public String getCanonicalPath() {
        /* IMPLEMENT LOGIC TO RETURN CANONICAL PATH */
        return "";
    }

    public String getFactorizedPath() {
         /* IMPLEMENT LOGIC TO RETURN FACTORIZED PATH */
         return "";
    }
}
