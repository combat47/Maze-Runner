package maze;

import java.util.*;

public class Main {
    public static Maze maze;
    public static void main(String[] args) {
        maze = generate();
        maze.draw();

    }

    public static Maze generate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of a new maze:");
        String[] input = scanner.nextLine().split("\\s");
        int[] size = new int[2];
        for (int i = 0; i < input.length; i++) {
            size[i] = Integer.parseInt(input[i]);
        }
        return new Maze(size[0], size[1]);
    }
}
