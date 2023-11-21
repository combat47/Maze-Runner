package maze;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final int x;
    private final int y;
    private boolean visited;
    private final List<Cell> neighbors;
    private final boolean wall;
    private final boolean inPath;

    public Cell(int x, int y, boolean wall) {
        this.x = x;
        this.y = y;
        this.wall = wall;
        this.visited = false;
        this.neighbors = new ArrayList<>();
        this.inPath = false;
    }

    public void addNeighbor(Cell otherCell) {
        if(!neighbors.contains(otherCell)) {
            neighbors.add(otherCell);
        }

        if(!otherCell.neighbors.contains(this)) {
            otherCell.neighbors.add(this);
        }
    }


    public boolean hasRightCellNeighbor() {
        return neighbors.stream()
                .anyMatch(c -> c.getX() == this.x && c.getY() == this.y + 1);
    }

    public boolean hasLowerCellNeighbor() {
        return neighbors.stream()
                .anyMatch(c -> c.getX() == this.x + 1 && c.getY() == this.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isWall() {
        return wall;
    }

    @Override
    public String toString() {
        return "Cell [x=" + x + ", y=" + y + ", visited=" + visited + ", wall=" + wall + ", inPath=" + inPath + "]";
    }

}
