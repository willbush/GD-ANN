import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

class Perceptron {
    /**
     * Trains a single perceptron on boolean classification task using boolean attributes for the given number
     * of iterations. The progress of the training is printed to the screen, and after the training is finished
     * the accuracy of the trained perceptron is printed when compared to the training and test set.
     *
     * @param trainSet        The set to train the perceptron with.
     * @param testSet         The set used to test the learned perceptron on.
     * @param learningRate    The learning rate used in gradient descent.
     * @param numOfIterations the number of iterations to spend on training the perceptron.
     */
    static void learn(DataSet trainSet, DataSet testSet, double learningRate, int numOfIterations) {
        final int numOfAttributes = trainSet.getAttributeNames().size();
        List<Double> weights = initializeWeights(numOfAttributes);

        int exampleIndex = 0;
        for (int i = 0; i < numOfIterations; ++i) {
            if (exampleIndex >= trainSet.getExamples().size())
                exampleIndex = 0;

            final double dotProduct = dotProduct(trainSet.getExample(exampleIndex), weights);
            final double error = trainSet.getLabel(exampleIndex) - sigmoid(dotProduct);
            final double sigDerivative = sigmoidDerivative(dotProduct);
            weights = updateWeights(weights, trainSet.getExample(exampleIndex), learningRate * sigDerivative * error);
            final double output = sigmoid(dotProduct(weights, trainSet.getExample(exampleIndex)));

            printLearningProgress(i + 1, trainSet.getAttributeNames(), weights, output);
            exampleIndex++;
        }

        System.out.println();
        printAccuracy(trainSet, weights, "Accuracy on training set (%d instances): %s%%\n");
        printAccuracy(testSet, weights, "Accuracy on test set (%d instances): %s%%");
    }

    /**
     * Network weights are initialized to zero.
     *
     * @param initialCapacity initial capacity on an array list is a minor optimization.
     * @return weights
     */
    private static List<Double> initializeWeights(int initialCapacity) {
        List<Double> weights = new ArrayList<>(initialCapacity);

        for (int i = 0; i < initialCapacity; ++i)
            weights.add(0.0);

        return weights;
    }

    /**
     * Prints the weight values and perceptron's output for the current iteration.
     */
    private static void printLearningProgress(int iterationNum, List<String> attributeNames, List<Double> weights, double output) {
        System.out.print(String.format("After iteration %d: ", iterationNum));
        for (int i = 0; i < weights.size(); ++i) {
            System.out.print(String.format("w(%s) = %.4f, ", attributeNames.get(i), weights.get(i)));
        }
        System.out.println(String.format("output = %.4f", output));
    }

    /**
     * Prints the accuracy of the perceptron at classifying the given set.
     */
    private static void printAccuracy(DataSet set, List<Double> weights, String format) {
        final int instanceCount = set.getExamples().size();
        int correctlyClassifiedSetExamples = 0;

        for (int i = 0; i < instanceCount; ++i) {
            double output = sigmoid(dotProduct(set.getExample(i), weights));
            if (Math.round(output) == Math.round(set.getLabel(i))) {
                correctlyClassifiedSetExamples++;
            }
        }
        double accuracy = (double) correctlyClassifiedSetExamples / instanceCount * 100;
        DecimalFormat df = new DecimalFormat(".#");
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        System.out.println(String.format(format, instanceCount, df.format(accuracy)));
    }

    /**
     * Updates the weights according to the weight update rule:
     * w <- w + a*d*e*x
     * where:
     * w is a vector of weights
     * a is the learning rate
     * e is the error, e = expectedOutput - actualOutput
     * x is a vector of inputs or attributes.
     *
     * @param weights    the weight values to the inputs of the perceptron.
     * @param attributes the attributes of an example (aka instance, observation)
     * @param scalar     a scalar given by the learningRate * sigDerivative * error
     * @return the list of updated weights.
     */
    private static List<Double> updateWeights(List<Double> weights, List<Double> attributes, double scalar) {
        assert weights.size() == attributes.size();
        List<Double> result = new ArrayList<>(weights.size());

        for (int i = 0; i < weights.size(); ++i) {
            double newWeight = weights.get(i) + scalar * attributes.get(i);
            result.add(i, newWeight);
        }
        return result;
    }

    /**
     * The derivative of the sigmoid function is f'(x) = f(x)(1 - f(x))
     * where f(x) is the sigmoid function.
     */
    private static double sigmoidDerivative(double t) {
        final double x = sigmoid(t);
        return x * (1 - x);
    }

    /**
     * the sigmoid math function f(x) = 1/(1 + e^(-x))
     */
    private static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    /**
     * @return the dot product result between two lists.
     */
    static double dotProduct(List<Double> a, List<Double> b) {
        assert a.size() == b.size();
        double result = 0;

        for (int i = 0; i < a.size(); ++i)
            result += a.get(i) * b.get(i);

        return result;
    }
}
