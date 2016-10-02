import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

class Perceptron {
    static void learn(DataSet trainSet, DataSet testSet, double learningRate, int numOfIterations) {
        final int numOfAttributes = trainSet.getAttributeNames().size();
        List<Double> weights = new ArrayList<>(trainSet.getAttributeNames().size());

        for (int i = 0; i < numOfAttributes; ++i) {
            weights.add(0.0);
        }

        int exampleIndex = 0;
        for (int i = 0; i < numOfIterations; ++i) {
            if (exampleIndex >= trainSet.getExamples().size())
                exampleIndex = 0;

            double dotProduct = dotProduct(trainSet.getExample(exampleIndex), weights);
            double error = trainSet.getLabel(exampleIndex) - sigmoid(dotProduct);
            double sigDerivative = sigmoidDerivative(dotProduct);
            weights = updateWeights(weights, trainSet.getExample(exampleIndex), learningRate * sigDerivative * error);
            double output = sigmoid((dotProduct(weights, trainSet.getExample(exampleIndex))));

            printLearningProgress(i + 1, trainSet.getAttributeNames(), weights, output);
            exampleIndex++;
        }

        System.out.println();
        printAccuracy(trainSet, weights, "Accuracy on training set (%d instances): %s%%\n\n");
        printAccuracy(testSet, weights, "Accuracy on test set (%d instances): %s%%");
    }

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
        System.out.print(String.format(format, instanceCount, df.format(accuracy)));
    }

    private static void printLearningProgress(int iterationNum, List<String> attributeNames, List<Double> weights, double output) {
        System.out.print(String.format("After iteration %d: ", iterationNum));
        for (int i = 0; i < weights.size(); ++i) {
            System.out.print(String.format("w(%s) = %.4f, ", attributeNames.get(i), weights.get(i)));
        }
        System.out.println(String.format("output = %.4f", output));
    }

    private static List<Double> updateWeights(List<Double> weights, List<Double> attributes, double scalar) {
        assert weights.size() == attributes.size();
        List<Double> result = new ArrayList<>(weights.size());

        for (int i = 0; i < weights.size(); ++i) {
            double newWeight = weights.get(i) + scalar * attributes.get(i);
            result.add(i, newWeight);
        }
        return result;
    }

    private static double sigmoidDerivative(double t) {
        final double x = sigmoid(t);
        return x * (1 - x);
    }

    private static double sigmoid(double t) {
        return 1 / (1 + Math.exp(-t));
    }

    static double dotProduct(List<Double> a, List<Double> b) {
        assert a.size() == b.size();
        double result = 0;

        for (int i = 0; i < a.size(); ++i)
            result += a.get(i) * b.get(i);

        return result;
    }
}
