/**
 * The entry point of the application.
 */
public class Main {
    private static final String PROGRAM_USAGE = "The program requires exactly 4 arguments:\n" +
            "1st argument: the path to the training set\n" +
            "2nd argument: the path to the test set\n" +
            "3rd argument: learning rate\n" +
            "4th argument: the number of iterations.\n\n" +
            "Example usage:\n" +
            "java Main ../resources/dataSet1/train2.dat ../resources/dataSet1/test2.dat 0.3 800";

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println(PROGRAM_USAGE);
            System.exit(1);
        }
        try {
            final DataSet trainSet = DataSet.fromFile(args[0]);
            final DataSet testSet = DataSet.fromFile(args[1]);
            final double learningRate = Double.parseDouble(args[2]);
            final int numOfIterations = Integer.parseInt(args[3]);

            Perceptron.learn(trainSet, testSet, learningRate, numOfIterations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
