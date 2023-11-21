package maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Maze {

    private static final Random random = new Random();
    private int height;
    private int width;
    private int heightCell;
    private int widthCell;
    private String[][] mazeGrid;
    private Cell[][] cellGrid;

    private static final String FREE = "  ";
    private static final String WALL = "██";


    public Maze(int height, int width) {
        initDimensions(height, width);
        initCells();
        generateMaze();
    }

    private void initDimensions(int height, int width) {
        this.height = height;
        this.width = width;
        mazeGrid = new String[height][width];
        this.heightCell = (height - 1)/2;
        this.widthCell = (width - 1)/2;
    }

    private void initCells() {
        cellGrid = new Cell[heightCell][widthCell];
        for(int i = 0; i < heightCell; i++) {
            for(int j = 0; j < widthCell; j++) {
                cellGrid[i][j] = new Cell(i, j, false);
            }
        }
    }

    private void generateMaze() {
        generateMaze(random.nextInt(heightCell));
    }

    private void generateMaze(int startX) {
        Cell startCell = cellGrid[startX][0];
        startCell.setVisited(true);
        List<Cell> cellsVisited = new ArrayList<>();
        cellsVisited.add(startCell);

        while (!cellsVisited.isEmpty()) {
            Cell cell;
            cell = cellsVisited.remove(cellsVisited.size() - 1);
            List<Cell> neighbours = new ArrayList<>(Arrays.asList(
                    getCell(cell.getX() + 1, cell.getY()),
                    getCell(cell.getX() - 1, cell.getY()),
                    getCell(cell.getX(), cell.getY() + 1),
                    getCell(cell.getX(), cell.getY() - 1)
            ));
            neighbours.removeIf(n -> (n == null || n.isVisited() || n.isWall()));

            if(!neighbours.isEmpty()) {
                Cell selected = neighbours.get(random.nextInt(neighbours.size()));
                cell.addNeighbor(selected);
                selected.setVisited(true);
                cellsVisited.add(cell);
                cellsVisited.add(selected);
            }
        }
        createGrid();
    }

    public void draw() {
        for (String[] line: mazeGrid) {
            for (String symbol: line) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }

    private Cell getCell(int x, int y) {
        try {
            return cellGrid[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private void createGrid() {
        createDefaultWalls();

        for (int i = 0; i < heightCell; i++) {
            for (int j = 0; j < widthCell; j++) {
                Cell selected = cellGrid[i][j];
                int mazeI = 2*i + 1;
                int mazeJ = 2*j + 1;
                mazeGrid[mazeI][mazeJ] = FREE;
                if (selected.hasRightCellNeighbor()) {
                    mazeGrid[mazeI][mazeJ + 1] = FREE;
                } else {
                    mazeGrid[mazeI][mazeJ + 1] = WALL;
                }
                if (selected.hasLowerCellNeighbor()) {
                    mazeGrid[mazeI + 1][mazeJ] = FREE;
                } else {
                    mazeGrid[mazeI + 1][mazeJ] = WALL;
                }

            }
        }
        drawRemainingWalls();
        drawRemainingFree();
        insertEntranceAndExit();
    }

    private void createDefaultWalls() {
        for (int i = 0; i < height; i++) {
            mazeGrid[i][0] = WALL;
        }

        for (int j = 0; j < width; j++) {
            mazeGrid[0][j] = WALL;
        }
    }

    private void drawRemainingWalls() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mazeGrid[i][j] == null) {
                    mazeGrid[i][j] = WALL;
                }
            }
        }
    }

    private void insertEntranceAndExit() {
        int randomEntrance = random.nextInt(cellGrid.length);
        mazeGrid[2*randomEntrance + 1][0] = FREE;
        int randomExit = random.nextInt(cellGrid.length);
        while (randomExit == randomEntrance) {
            randomExit = random.nextInt(cellGrid.length);
        }
        mazeGrid[2*randomExit + 1][mazeGrid[0].length - 1] = FREE;
        if (width % 2 == 0) {
            mazeGrid[2*randomExit + 1][mazeGrid[0].length - 2] = FREE;
        }
    }


    private void drawRemainingFree() {
        if (height % 2 == 0) {
            for (int i = 0; i < heightCell; i++) {
                if (random.nextInt(2) == 1) {
                    mazeGrid[2*i + 1][mazeGrid[0].length - 2] = FREE;
                }
            }
        }

        if (width % 2 == 0) {
            for (int j = 0; j < widthCell; j++) {
                if (random.nextInt(2) == 1) {
                    mazeGrid[mazeGrid.length - 2][2*j + 1] = FREE;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Maze [cellHeight=" + heightCell + ", cellWidth=" + widthCell + ", mazeHeight=" + height
                + ", mazeWidth=" + width + "]";
    }
}
