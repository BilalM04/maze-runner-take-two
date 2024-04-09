package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.path.Instruction;
import ca.mcmaster.se2aa4.mazerunner.path.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class PathTest {
    private Path path;
    
    @BeforeEach
    public void setUp() {
        this.path = new Path();
        path.addInstruction(Instruction.F);
        path.addInstruction(Instruction.F);
        path.addInstruction(Instruction.R);
        path.addInstruction(Instruction.L);
        path.addInstruction(Instruction.L);
        path.addInstruction(Instruction.F);
    }

    @Test
    public void canonicalTest() {
        assertEquals("FF R LL F", path.getCanonicalPath());
    }

    @Test
    public void factorizedTest() {
        assertEquals("2F R 2L F ", path.getFactorizedPath());
    }

    @Test
    public void getInstructionTest(){
        assertEquals(Instruction.F, path.getInstruction(0));
        assertEquals(Instruction.F, path.getInstruction(1));
        assertEquals(Instruction.R, path.getInstruction(2));
        assertEquals(Instruction.L, path.getInstruction(3));
        assertEquals(Instruction.L, path.getInstruction(4));
        assertEquals(Instruction.F, path.getInstruction(5));
    }

    @Test
    public void appendTest(){
        Path newPath = new Path();
        newPath.addInstruction(Instruction.F);
        newPath.addInstruction(Instruction.L);
        path.appendPath(newPath);

        assertEquals("2F R 2L 2F L ", path.getFactorizedPath());
        assertEquals("FF R LL FF L", path.getCanonicalPath());
    }
}
