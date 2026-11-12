public class GridTraversalEngine {
    private final int dimension;
    private final double[][] grid;

    public GridTraversalEngine(int dimension) {
        this.dimension = dimension;
        this.grid = new double[dimension][dimension];
        populateGrid();
    }

    private void populateGrid() {
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                grid[row][col] = ((row + col) % 100) * 0.25;
            }
        }
    }
}