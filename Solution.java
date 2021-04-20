import java.util.PriorityQueue;

public class Solution {

  public int[][] moves = {
    {1, 0}, // up
    {-1, 0}, // down
    {0, -1}, // left
    {0, 1} // right
  };

  /*
   By the problem design on binarysearch.com, we have to work
   around the given method 'public int solve(int[][] matrix)' in order for the code
   to be able to run on the website. Even though the name 'solve' does not make
   a lot of sense, it is left as it is, so that the code can be run directly on the website,
   without any modifications.
  */
  public int solve(int[][] matrix) {
    return dijkstra_findTrailWithMinEffort(matrix);
  }

  /*
   @return Minimum cost(as defined by the problem statement) for the path(trail)
           from matrix[0][0] to matrix[matrix.length-1][matrix[0].length-1].
  */
  public int dijkstra_findTrailWithMinEffort(int[][] matrix) {

    PriorityQueue<Point> minHeap = new PriorityQueue<Point>();
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];

    Point start = new Point(0, 0);
    start.cost = 0;
    minHeap.add(start);
    int minCostPath = 0;

    while (!minHeap.isEmpty()) {

      Point current = minHeap.poll();
      int row = current.row;
      int column = current.column;
      visited[row][column] = true;

      minCostPath = Math.max(minCostPath, current.cost);
      if (row == matrix.length - 1 && column == matrix[0].length - 1) {
        break;
      }

      for (int i = 0; i < moves.length; i++) {
        int new_row = row + moves[i][0];
        int new_column = column + moves[i][1];

        if (isInMatrix(matrix, new_row, new_column) && !visited[new_row][new_column]) {
          Point p = new Point(new_row, new_column);
          p.cost = Math.abs(matrix[new_row][new_column] - matrix[row][column]);
          minHeap.add(p);
        }
      }
    }

    return minCostPath;
  }

  public boolean isInMatrix(int[][] matrix, int row, int column) {
    if (row < 0 || column < 0 || row > matrix.length - 1 || column > matrix[0].length - 1) {
      return false;
    }
    return true;
  }
}

class Point implements Comparable<Point> {
  int row;
  int column;
  int cost;

  public Point(int row, int column) {
    this.row = row;
    this.column = column;
  }

  @Override
  public int compareTo(Point other) {
    return this.cost - other.cost;
  }
}
