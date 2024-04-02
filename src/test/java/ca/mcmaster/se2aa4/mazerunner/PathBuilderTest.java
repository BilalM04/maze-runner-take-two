package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.path.Instruction;
import ca.mcmaster.se2aa4.mazerunner.path.Path;
import ca.mcmaster.se2aa4.mazerunner.path.PathBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathBuilderTest {
    private PathBuilder builder = new PathBuilder();
    
    @Test
    public void buildTest() {
        Path path = builder.buildPath("FLR");

        assertEquals(Instruction.F, path.getInstruction(0));
        assertEquals(Instruction.L, path.getInstruction(1));
        assertEquals(Instruction.R, path.getInstruction(2));
    }
}
