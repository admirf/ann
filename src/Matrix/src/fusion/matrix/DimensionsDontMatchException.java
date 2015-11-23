package fusion.matrix;

/**
 * Created by admir on 11/20/2015.
 */
    public DimensionsDontMatchException() {xDiff = -1; yDiff = -1;}
    public DimensionsDontMatchException(Matrix a, Matrix b) {
        xDiff = java.lang.Math.abs(a.getCols() - b.getCols());
    }
    public int getRowDifference() {return yDiff;}
    public int getColDifference() {return xDiff;}
}
