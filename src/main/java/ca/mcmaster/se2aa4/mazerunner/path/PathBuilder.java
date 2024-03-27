package ca.mcmaster.se2aa4.mazerunner.path;

public class PathBuilder {

    /**
     * Parses a path string and converts it to a Path object.
     *
     * @param path The user-provided path string to be parsed and stored.
     * @return A Path object representing the path.
     * @throws IllegalArgumentException If an invalid character is encountered in the path string.
     */
    public Path buildPath(String strPath) {
        Path user_path = new Path();
        strPath = strPath.replace(" ", "");

        for (int i = 0; i < strPath.length(); i++) {
            int repeat = 1;
            int end = i;

            // extracts number portion from input sequence (if factorized)
            if (strPath.charAt(i) >= 48 && strPath.charAt(i) <= 57) {
                end++;
                while (strPath.charAt(end) >= 48 && strPath.charAt(end) <= 57) {
                    end++;
                }
                repeat = Integer.parseInt(strPath.substring(i, end));
                i = end;
            }

            // adds the intruction to the path object and repeats based on the number portion
            for (int r = 0; r < repeat; r++) {
                switch (strPath.charAt(end)) {
                    case 'F':
                        user_path.addInstruction(Instruction.F);
                        break;
                    case 'L':
                        user_path.addInstruction(Instruction.L);
                        break;
                    case 'R':
                        user_path.addInstruction(Instruction.R);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid character in path: " + strPath.charAt(end));
                }
            }
        }

        return user_path;
    }
}
