package ca.mcmaster.se2aa4.mazerunner.path;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Instruction> canonical_path;

    public Path() {
        this.canonical_path = new ArrayList<>();
    }

    public void addInstruction(Instruction instruction) {
        canonical_path.add(instruction);
    }

    public Instruction getInstruction(int index) {
        if (index < 0 || index >= this.length()){
            throw new IndexOutOfBoundsException();
        }
        return canonical_path.get(index);
    }

    public int length() {
        return canonical_path.size();
    }

    /**
     * Retrieves the canonical path as a string representation.
     *
     * @return A string representation of the canonical path.
     */
    public String getCanonicalPath() {
        StringBuilder canonical_path_str = new StringBuilder();
        for (int i = 0; i < canonical_path.size(); i++) {
            if (i > 0 && canonical_path.get(i) != canonical_path.get(i-1)) {
                canonical_path_str.append(" ");
            }
            canonical_path_str.append(canonical_path.get(i).toString());
        }
        return canonical_path_str.toString();
    }

    /**
     * Retrieves the factorized path as a string representation.
     *
     * @return A string representation of the factorized path.
     */
    public String getFactorizedPath() {
         StringBuilder factorized_path_str = new StringBuilder();
         for (int i = 0; i < canonical_path.size(); i++) {
            Instruction current_instruction = canonical_path.get(i);
            int count = 0;
            // counts consequtive identical instructions
            while (i < canonical_path.size() && current_instruction.toString().equals(canonical_path.get(i).toString())) {
                i++;
                count++;
            }
            i--;
            // combines number + instruction into string
            if (count == 1) {
                factorized_path_str.append(current_instruction.toString() + " ");
            } else {
                factorized_path_str.append(count + "" + current_instruction.toString() + " ");
            }
         }
         return factorized_path_str.toString();
    }
}
