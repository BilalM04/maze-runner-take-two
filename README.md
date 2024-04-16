# Maze Runner, Take Two

  * **Student**: [Mohammad Bilal](bilalm14@mcmaster.ca)
  * **Program**: B. Eng. In Software Engineering
  * **Course code**: SFWRENG 2AA4
  * **Course Title**: Software Design I - Introduction to Software Development 
  * Term: *Level II - Winter 2024*

## Business Logic Specification

This program explores a maze, finding a path from an entry point to an exit one.

- The maze is stored in a text file, with `#` representing walls and `␣` (_empty space_) representing passages.
- You’ll find examples of such mazes in the [`examples`](./examples) directory. 
    - You can also use the [Maze Generator](https://github.com/ace-lectures/maze-gen) to generate others.
- The Maze is surrounded by walls on its four borders, except for its entry/exit points.
    - Entry and exit points are always located on the East and West border.
    - The maze is not directed. As such, exit and entry can be interchanged.
- At the beginning of the exploration, we're located on the entry tile, facing the opposite side (e.g., if entering by the eastern entry, you're facing West).
- The program generates a sequence of instructions to reach the opposite exit (i.e., a "path"):
    - `F` means 'move forward' according to your current direction
    - `R` means 'turn right' (does not move, just change direction), and `L` means ‘turn left’. 
- A canonical path contains only `F`, `R` and `L` symbols
- A factorized path squashes together similar instructions (i.e., `FFF` = `3F`, `LL` = `2L`).
- Spaces are ignored in the instruction sequence (only for readability: `FFLFF` = `FF L FF`)
- The program takes as input a maze and prints the path on the standard output.
    - There are two maze algorithms available: BFS and Right Hand Rule
- The program can take a path as input and verify if it's a legit one.
- The program can benchmark and compare the efficiency of two algorithms.

## How to run this software?

To build the program, simply package it with Maven:

```
bilal@mohd % mvn -q clean package 
```

#### Command line arguments

- `-i MAZE_FILE`: specifies the filename to be used;
- `-p PATH_SEQUENCE`: activates the path verification mode to validate that PATH_SEQUENCE is correct for the maze
- `-m {righthand, bfs}`: specifies which path computation method to use. (default is right hand)
- `-b {righthand, bfs}`: activates the benchmark mode and specifies which path computation method to use as the baseline.

#### Examples

When no logs are activated, the programs only print the computed path on the standard output.

```
bilal@mohd % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -m righthand
4F
bilal@mohd %
```

If a given path is correct, the program prints the message `correct path` on the standard output.

```
bilal@mohd % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 4F
correct path
bilal@mohd %
```

If a given path is incorrect, the program prints the message `incorrect path` on the standard output.

```
bilal@mohd % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 3F
inccorrect path
bilal@mohd %
```

If a baseline is specified, the program prints the benchmark results on the standard output.

```
bilal@mohd % java -jar target/mazerunner.jar -i ./examples/huge.maz.txt -m bfs -b righthand
Time spent loading the maze: 2.86 ms
Method Time: 9.80 ms
Baseline Time: 6.46 ms
Speedup: 35.87
bilal@mohd %
```

