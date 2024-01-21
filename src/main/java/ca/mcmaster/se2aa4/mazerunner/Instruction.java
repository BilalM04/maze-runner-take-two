package ca.mcmaster.se2aa4.mazerunner;

public enum Instruction {
    F('F'), L('L'), R('R');

    private final char symbol;

    Instruction(char symbol) { 
        this.symbol = symbol; 
    }

    public String toString() { 
        return "" + symbol; 
    }
}
