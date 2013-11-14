public class RectangularGrid extends Grid {
    public RectangularGrid(int rows, int cols, int regions){
        super(rows, cols, regions);
    }
    @Override
    protected void createCells()  {
        for(int col = leftmostColumn(); col <= rightmostColumn(); col++)
            for(int row = topmostRow(); row <= bottommostRow(); row++){
                Cell topCell = (row == topmostRow() ? Cell.border : cellAt(col, row - 1));
                Cell leftCell = (col == leftmostColumn() ? Cell.border : cellAt(col - 1, row));
                Cell c = createCellWithNeighbours(topCell, leftCell);
                c.setRegion(getRandomRegion());
                cells[calculateIndex(col, row, this.rows)] = c;
            }
    }
}
