package fusion.matrix;

/**
public class HopfieldNetwork {
    private int neurons;

    public HopfieldNetwork(int neurons) {
        this.neurons = neurons;
        matrix = new Matrix(neurons, neurons);
    }

    public HopfieldNetwork(Matrix matrix) {
        this.matrix = matrix.clone();
    }

    public void train(Matrix vector) throws DimensionsDontMatchException {
        if(!vector.isVector()) throw new DimensionsDontMatchException();

        vector.bin2bip();

        Matrix pattern = vector.multiply(vector.transpose());
        this.matrix = this.matrix.add(pattern);
        this.matrix.setDiagonalToZero();
    }

    public void train(String patt) throws DimensionsDontMatchException {
        Matrix m = new Matrix(patt.length(), 1);
        for(int i = 0;i < patt.length();++i) {
            double ret = (patt.charAt(i) == '0')? 0: 1;
            m.set(i, 0, ret);
        }

        train(m);
    }

    public Matrix getMatrix() {
        return this.matrix.clone();
    }

    public double[] testPattern(double[] pattern) throws DimensionsDontMatchException {
        if(pattern.length != this.neurons) throw new DimensionsDontMatchException();

        double[] result = new double[this.neurons];

        for(int i = 0;i < this.neurons;++i) {
            double res = 0;
            for(int j = 0;j < this.neurons;++j) {
                res += pattern[j]*this.matrix.get(i, j);
            }
            result[i] = (res > 0)? 1: 0;
        }



        return result;
    }

    public static double[] stringToPattern(String s) {
        double[] patt = new double[s.length()];
        for(int i = 0;i < s.length();++i) {
            patt[i] = (s.charAt(i) == '0')? 0: 1;
        }
        return patt;
    }

    public static String patternToString(double[] patt) {
        StringBuilder str = new StringBuilder();
        for(int i = 0;i < patt.length;++i) str.append((patt[i] == 0)? "0": "1");
        return str.toString();
    }

    public double[] testPattern(String s) throws DimensionsDontMatchException {
        return testPattern(stringToPattern(s));
    }

}