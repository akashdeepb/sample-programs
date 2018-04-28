import java.util.ArrayList;

public class GameOfLife {

  private class Cell {
    private ArrayList<Cell> neighbors;
    private boolean wasAlive;
    private boolean isAlive;

    public Cell(boolean isAlive) {
      this.wasAlive = isAlive;
      this.isAlive = isAlive;
      neighbors = new ArrayList<Cell>();
    }

    public void addNeighbor(Cell neighbor) {
      this.neighbors.add(neighbor);
    }

    public boolean wasAlive() {
      return this.wasAlive;
    }

    public boolean isAlive() {
      return this.isAlive;
    }

    public void clearState() {
      this.wasAlive = this.isAlive;
    }

    private int numOfLivingNeighbors() {
      int numOfLivingNeighbors = 0;
      for(Cell neighbor : this.neighbors) {
        if (neighbor.isAlive()) {
          numOfLivingNeighbors++;
        }
      }
      return numOfLivingNeighbors;
    }

    public void transition() {
      numOfLivingNeighbors = this.numOfLivingNeighbors();
      if (this.wasAlive() && numOfLivingNeighbors < 2) {
        this.isAlive = false;
      } else if (this.wasAlive() && (numOfLivingNeighbors == 2 || numOfLivingNeighbors == 3)) {
        this.isAlive = 1;
      } else if (this.wasAlive() && numOfLivingNeighbors == 4) {
        this.isAlive = 0;
      } else if (!this.wasAlive() && numOfLivingNeighbors == 3) {
        this.isAlive = 1;
      }
    }
  }

  private class Grid {
    int width;
    Cell[][] grid;

    public Grid(int width) {
      this.width = width;
      this.grid = new Cell[width][width];
    }

    private void populate() {
      for (int row = 0; row < this.grid.length; row++) {
        for (int col = 0; col < this.grid[row].length; col++) {
          boolean rand = Math.random() < .15;
          this.grid[row][col] = new Cell(rand);
        }
      }
    }

    private void link() {
      for (int row = 0; row < this.grid.length; row++) {
        for (int col = 0; col < this.grid[row].length; col++) {
          previousRow = (row - 1) % this.width;
          nextRow = (row + 1) % this.width;
          previousCol = (col - 1) % this.width;
          nextCol = (col + 1) % this.width;
          this.grid[row][col].addNeighbor(this.grid[previousRow][previousCol]);
          this.grid[row][col].addNeighbor(this.grid[previousRow][nextCol]);
          this.grid[row][col].addNeighbor(this.grid[nextRow][previousCol]);
          this.grid[row][col].addNeighbor(this.grid[nextRow][nextCol]);
        }
      }
    }

  }

  public static void main(String[] args) {
    // TODO: Play game
  }
}
