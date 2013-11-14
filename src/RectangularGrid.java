public class RectangularGrid extends Grid {
    public RectangularGrid(int rows, int cols, int regions){
        super(rows, cols, regions);
    }
    @Override
    protected Grid createCells()  {
        super.createCells();
        return this;
    }
}
