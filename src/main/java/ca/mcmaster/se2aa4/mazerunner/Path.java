package ca.mcmaster.se2aa4.mazerunner;

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
        return canonical_path.get(index);
    }

    public int length() {
        return canonical_path.size();
    }

    public String getCanonicalPath() {
        StringBuilder canonical_path_str = new StringBuilder();
        for (Instruction inst : canonical_path) {
            canonical_path_str.append(inst.toString());
        }
        return canonical_path_str.toString();
    }

    public String getFactorizedPath() {
         StringBuilder factorized_path_str = new StringBuilder();
         for (int i = 0; i < canonical_path.size(); i++) {
            Instruction current_instruction = canonical_path.get(i);
            int count = 0;
            while (i < canonical_path.size() && current_instruction.toString().equals(canonical_path.get(i).toString())) {
                i++;
                count++;
            }
            i--;
            if (count == 1) {
                factorized_path_str.append(current_instruction.toString() + " ");
            } else {
                factorized_path_str.append(count + "" + current_instruction.toString() + " ");
            }
         }
         return factorized_path_str.toString();
    }
}
