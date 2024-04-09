package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.path.Instruction;
import ca.mcmaster.se2aa4.mazerunner.path.Path;
import ca.mcmaster.se2aa4.mazerunner.path.PathBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PathBuilderTest {
    private PathBuilder builder = new PathBuilder();
    
    @Test
    public void buildTest1() {
        Path path = builder.buildPath("FLR");

        assertEquals(Instruction.F, path.getInstruction(0));
        assertEquals(Instruction.L, path.getInstruction(1));
        assertEquals(Instruction.R, path.getInstruction(2));
    }

    @Test
    public void buildTest2() {
        Path path = builder.buildPath("2F 2R 2L");

        assertEquals(Instruction.F, path.getInstruction(0));
        assertEquals(Instruction.F, path.getInstruction(1));
        assertEquals(Instruction.R, path.getInstruction(2));
        assertEquals(Instruction.R, path.getInstruction(3));
        assertEquals(Instruction.L, path.getInstruction(4));
        assertEquals(Instruction.L, path.getInstruction(5));
    }

    @Test
    public void buildTest3() {
        Path path = builder.buildPath("FF");

        assertEquals(Instruction.F, path.getInstruction(0));
        assertNotEquals(Instruction.R, path.getInstruction(1));
    }
}
