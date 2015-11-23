package fusion.matrix;

/**
 * Created by admir on 11/20/2015.
 */
public class Runner {
    public static void main(String[] args) {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        double[][] matrix2 = {{7, 8}, {9, 10}, {11, 12}};
        Matrix a = new Matrix(matrix);
        Matrix b = new Matrix(matrix2);

        try {
            HopfieldNetwork net = new HopfieldNetwork(8);
            // Treniram ga na pattern 111000 i na pattern 00000111
            net.train("11100000");
            net.train("00000111");

            // testiram na 00000101
            String res = HopfieldNetwork.patternToString(net.testPattern("00000101"));

            System.out.println(res);

        } catch(DimensionsDontMatchException e) {
            System.out.println("neko je glup");
        }

    }
}
