public class ToroidalGrid extends Grid {
    public ToroidalGrid(int rows, int cols, int regions){
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
        connectEdges();
    }

    private void connectEdges() {
        //upper edge
        for(int col = leftmostColumn(); col <= rightmostColumn(); col++)
            cellAt(col, topmostRow()).setTop(cellAt(col, bottommostRow()));
        //lower edge
        for(int col = leftmostColumn(); col <= rightmostColumn(); col++)
            cellAt(col, bottommostRow()).setBottom(cellAt(col, topmostRow()));
        //left edge
        for(int row = topmostRow(); row <= bottommostRow(); row++)
            cellAt(leftmostColumn(), row).setLeft(cellAt(rightmostColumn(), row));
        //right edge
        for(int row = topmostRow(); row <= bottommostRow(); row++)
            cellAt(rightmostColumn(), row).setRight(cellAt(leftmostColumn(), row));
    }
}
