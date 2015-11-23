package fusion.matrix;

/**
 * Created by admir on 11/20/2015.
 */
public class Matrix {

    private double[][] matrix;
    private int rows, cols;

    /**
     * First constructor
     * Creates an empty matrix of the size rows x cols
     *
     * @param rows number of rows
     * @param cols number of cols
     */
    public Matrix(int rows, int cols) {
        this.matrix = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Second constructor
     * Creates a Matrix object out of a double 2d-array
     * @param matrix double-dimensioned array that represents a matrix
     */
    public Matrix(double[][] matrix) {
        this.matrix = cloneDoubleMatrix(matrix);
        this.rows = this.matrix.length;
        this.cols = this.matrix[0].length;
    }

    public Matrix(Matrix matrix) {
        this.matrix = cloneDoubleMatrix(matrix.get());
        this.rows = this.matrix.length;
        this.cols = this.matrix[0].length;
    }

    /**
     * It sets a value of the matrix at the given coordinate
     * @param x row coordinate
     * @param y column coordinate
     * @param value the double value to be set at the (x, y) position
     */
    public void set(int x, int y, double value) throws ArrayIndexOutOfBoundsException {
        this.matrix[x][y] = value;
    }

    /**
     * Sets all the matrix members to the given value
     * @param value
     */
    public void set(double value) {
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                this.matrix[i][j] = value;
    }

    /**
     * Adds a double value to all members of the matrix
     * @param value the value to be added
     */
    public void add(double value) {
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                this.matrix[i][j] += value;
    }

    /**
     * Multiplies the whole matrix with a double value
     * @param value the value to be added
     */
    public void multiply(double value) {
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                this.matrix[i][j] *= value;
    }

    /**
     * Divides the whole matrix with a double value
     * @param value the value to be added
     */
    public void divide(double value) {
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                this.matrix[i][j] /= value;
    }

    /**
     * Returns the value at the given coordinates
     * @param x row coordinate
     * @param y column coordinate
     * @return double
     */
    public double get(int x, int y) throws ArrayIndexOutOfBoundsException {
        return this.matrix[x][y];
    }

    /**
     * Return the whole double 2d-array
     * @return double[][]
     */
    public double[][] get() {
        return cloneDoubleMatrix(this.matrix);
    }

    /**
     * Return the sum of all matrix members
     * @return double
     */
    public double getSum() {
        return sumDoubleMatrix(this.matrix);
    }

    /**
     * Ima da Era popunjava ove gluposti, puca mi vise kurac od pisanja komentara
     * @return
     */
    public int getRows() {
        return rows;
    }

    /**
     * Return the number of columns in the matrix
     * @return
     */
    public int getCols() {
        return cols;
    }

    /**
     * Creates a matrix with one row and returns it
     * @param index the index of the row you want
     * @return Matrix object made of the row
     */
    public Matrix getRow(int index) throws ArrayIndexOutOfBoundsException {
        Matrix row = new Matrix(1, this.cols);
        for(int i = 0; i < this.cols; ++i)
            row.set(0, i, this.matrix[index][i]);
        return row;
    }

    /**
     * Creates a matrix with one row and returns it
     * @param index the index of the row you want
     * @return Matrix object made of the row
     */
    public Matrix getColumn(int index) throws ArrayIndexOutOfBoundsException {
        Matrix col = new Matrix(this.rows, 1);
        for(int i = 0; i < this.rows; ++i)
            col.set(i, 0, this.matrix[i][index]);
        return col;
    }

    /**
     * Sets everything to zero
     */
    public void clear() {
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                this.matrix[i][j] = 0;
    }

    /**
     * Checks if everything is zero
     * @return boolean
     */
    public boolean isClear() {
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                if(matrix[i][j] != 0) return false;

        return true;
    }

    /**
     * Checks if the matrix equals the given argument
     * Treba ga implementirat sa greskom, meni mrsko u trenutku
     * @param matrix Matrix the other matrix
     * @return
     */
    public boolean equals(Matrix matrix) {
        if(matrix.getCols() != this.cols || matrix.getRows() != this.rows) return false;
        for(int i = 0; i < rows; ++i)
            for(int j = 0; j < cols; ++j)
                if(this.matrix[i][j] != matrix.get(i, j)) return false;

        return true;
    }

    @Override
    public Matrix clone() {
        Matrix m = new Matrix(this.matrix);
        return m;
    }

    /**
     * Ovo sam napravio ovako za brzi ispis, mjenjaj ko hoce sebi do mile volje
     * @return
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                str.append(String.format("%5g ", matrix[i][j]));
            }
            str.append('\n');
        }
        return str.toString();
    }

    /**
     * Clones a two-dimensional double array
     * @param matrix
     * @return double[][] clone
     */
    public static double[][] cloneDoubleMatrix(double[][] matrix) {
        double[][] clone = new double[matrix.length][matrix[0].length];
        for(int i = 0; i < clone.length; ++i)
            for(int j = 0; j < clone[0].length; ++j)
                clone[i][j] = matrix[i][j];

        return clone;
    }

    /**
     * Sums all the values in the double 2d-array
     * @param matrix
     * @return double
     */
    public static double sumDoubleMatrix(double[][] matrix) {
        double result = 0;
        for(int i = 0; i < matrix.length; ++i)
            for(int j = 0; j < matrix[0].length; ++j)
                result += matrix[i][j];

        return result;
    }

    /**
     * Checks if the dimensions are the same
     * @param a
     * @param b
     * @return
     */
    public static boolean equalsDim(Matrix a, Matrix b) {
        return (a.getRows() == b.getRows() && a.getCols() == b.getCols());
    }

    /**
     * Adds two matrices and returns a new one as the result
     * @param a first Matrix
     * @param b second Matrix
     * @return Matrix the result of the addition
     * @throws DimensionsDontMatchException
     */
    public static Matrix add(Matrix a, Matrix b) throws DimensionsDontMatchException {
        if(!equalsDim(a, b)) {
            throw new DimensionsDontMatchException(a, b);
        }

        Matrix result = new Matrix(a.getRows(), a.getCols());
        for(int i = 0; i < result.getRows(); ++i)
            for(int j = 0; j < result.getCols(); ++j)
                result.set(i, j, a.get(i, j) + b.get(i, j));

        return result;
    }

    /**
     * Add function overloaded for a Matrix as an argument
     * Returns a new Matrix
     * @param b
     * @return
     * @throws DimensionsDontMatchException
     */
    public Matrix add(Matrix b) throws DimensionsDontMatchException {
        return add(this, b);
    }

    /**
     * Substracts
     * Returns a new Matrix
     * @param b
     * @return
     * @throws DimensionsDontMatchException
     */
    public Matrix substract(Matrix b) throws DimensionsDontMatchException {
        b.multiply(-1.0); // lijen sam za implementacija ovako ako nista lijepo izgleda
        return add(this, b);
    }

    /**
     * To be implemented... Naravno ako neko ima volje nek iskuca putem brzeg algoritma
     * @param a
     * @param b
     * @return
     */
    public static Matrix multiply(Matrix a, Matrix b) throws DimensionsDontMatchException {
        if(a.getRows() != b.getCols() || a.getCols() != b.getRows()) throw new DimensionsDontMatchException();

        Matrix result = new Matrix(a.getRows(), b.getCols());

        for(int i = 0;i < a.getRows();++i) {
            Matrix vec = a.getRow(i);

            for(int j = 0;j < b.getCols();++j) {
                Matrix vec2 = b.getColumn(j);
                double val = vec.dotProduct(vec2);
                result.set(i, j, val);
            }
        }

        return result;
    }

    /**
     * Multiplies the object with matrix b
     * @param b
     * @return
     * @throws DimensionsDontMatchException
     */
    public Matrix multiply(Matrix b) throws DimensionsDontMatchException {
        return multiply(this, b);
    }

    /**
     * Transposes the matrix
     * @param m
     * @return transposed Matrix object
     */
    public static Matrix transpose(Matrix m) {
        Matrix n = new Matrix(m.getCols(), m.getRows());
        for(int i = 0;i < m.getRows();++i)
            for(int j = 0;j < m.getCols();++j)
                n.set(j, i, m.get(i, j));

        return n;
    }

    /**
     * Transposes the matrix
     * @return transposed Matrix object
     */
    public Matrix transpose() {
        return transpose(this);
    }

    /**
     * checks if it is a vector
     * @return
     */
    public boolean isVector() {
        if(this.rows == 1 || this.cols == 1) return true;
        return false;
    }

    /**
     * Calculates the dotProduct of two matrices
     * @param a
     * @param b
     * @return
     * @throws DimensionsDontMatchException
     */
    public static double dotProduct(Matrix a, Matrix b) throws DimensionsDontMatchException {
        if(!a.isVector() && !b.isVector()) throw new DimensionsDontMatchException();
        if(a.getRows() == 1) {
            Matrix tmp = new Matrix(a);
            a = b.clone();
            b = tmp.clone();
        }
        if(a.getRows() != b.getCols()) throw new DimensionsDontMatchException();

        double result = 0;

        for(int i = 0;i < a.getRows();++i)
            result += a.get(i, 0)*b.get(0, i);

        return result;
    }

    /**
     * Return the dotProduct
     * @param b
     * @return
     */
    public double dotProduct(Matrix b) throws DimensionsDontMatchException {
        return dotProduct(this, b);
    }

    /**
     * Converts bipolar value(-1, 1) to binary(1, 0)
     * @param a
     * @return
     */
    public static double bip2bin(double a) {
        return (a+1)/2;
    }

    /**
     * Converts bipolar binary(1, 0) to value(-1, 1)
     * @param a
     * @return
     */
    public static double bin2bip(double a) {
        return 2*a - 1;
    }

    /**
     * Sets the matrix values along the top left - bottom right diagonal to zero
     */
    public void setDiagonalToZero() {
        for(int i = 0;i < this.rows;++i)
            this.matrix[i][i] = 0;
    }

    public void bin2bip() {
        for(int i = 0;i < this.rows;++i)
            for(int j = 0;j < this.cols;++j)
                this.matrix[i][j] = bin2bip(this.matrix[i][j]);
    }

}
