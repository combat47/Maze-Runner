package maze;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(10);
        maze.displayMaze();
    }
}

class Maze implements Serializable {
    private final int mazeSize;
    private final int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}

    }; // the array of the maze to print
    final String WALL = "██";
    final String EMPTY = "  ";

    public Maze(int mazeSize) {
        this.mazeSize = mazeSize;
    }

    public void displayMaze() {
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                if (maze[i][j] == 1) {
                    System.out.print(WALL);
                } else {
                    System.out.print(EMPTY);
                }
            }
            System.out.println();
        }
    }
}
