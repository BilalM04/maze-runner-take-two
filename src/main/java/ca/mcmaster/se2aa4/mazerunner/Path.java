package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    List<Instruction> canonical_path;
    List<String> factorized_path;

    public Path() {
        this.canonical_path = new ArrayList<>();
        this.factorized_path = new ArrayList<>();
    }

    public void addInstruction(Instruction instruction) {
        canonical_path.add(instruction);
    }

    public String getCanonicalPath() {
        StringBuilder canonical_path_str = new StringBuilder();
        for (Instruction inst : canonical_path) {
            canonical_path_str.append(inst.toString());
        }
        return canonical_path_str.toString();
    }

    public String getFactorizedPath() {
         /* IMPLEMENT LOGIC TO RETURN FACTORIZED PATH */
         return "";
    }
}
