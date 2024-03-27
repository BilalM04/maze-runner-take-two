package ca.mcmaster.se2aa4.mazerunner.path;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Instruction> canonicalPath;

    public Path() {
        this.canonicalPath = new ArrayList<>();
    }

    public void addInstruction(Instruction instruction) {
        canonicalPath.add(instruction);
    }

    public void appendPath(Path other) {
        for (int i = 0; i < other.length(); i++) {
            canonicalPath.add(other.getInstruction(i));
        }
    }

    public Instruction getInstruction(int index) {
        if (index < 0 || index >= this.length()){
            throw new IndexOutOfBoundsException();
        }
        return canonicalPath.get(index);
    }

    public int length() {
        return canonicalPath.size();
    }

    /**
     * Retrieves the canonical path as a string representation.
     *
     * @return A string representation of the canonical path.
     */
    public String getCanonicalPath() {
        StringBuilder canonicalPathStr = new StringBuilder();
        for (int i = 0; i < canonicalPath.size(); i++) {
            if (i > 0 && canonicalPath.get(i) != canonicalPath.get(i-1)) {
                canonicalPathStr.append(" ");
            }
            canonicalPathStr.append(canonicalPath.get(i).toString());
        }
        return canonicalPathStr.toString();
    }

    /**
     * Retrieves the factorized path as a string representation.
     *
     * @return A string representation of the factorized path.
     */
    public String getFactorizedPath() {
         StringBuilder factorizedPathStr = new StringBuilder();
         for (int i = 0; i < canonicalPath.size(); i++) {
            Instruction currentInstruction = canonicalPath.get(i);
            int count = 0;
            // counts consequtive identical instructions
            while (i < canonicalPath.size() && currentInstruction.toString().equals(canonicalPath.get(i).toString())) {
                i++;
                count++;
            }
            i--;
            // combines number + instruction into string
            if (count == 1) {
                factorizedPathStr.append(currentInstruction.toString() + " ");
            } else {
                factorizedPathStr.append(count + "" + currentInstruction.toString() + " ");
            }
         }
         return factorizedPathStr.toString();
    }
}
