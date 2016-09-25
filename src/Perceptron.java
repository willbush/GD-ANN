import java.util.ArrayList;
import java.util.List;

class Perceptron {
    static void learn(DataSet dataSet, double learningRate, int numOfIterations) {
        final int numOfAttributes = dataSet.getAttributeNames().size();
        List<Double> weights = new ArrayList<>(dataSet.getAttributeNames().size());

        for (int i = 0; i < numOfAttributes; ++i) {
            weights.add(0.0);
        }

        int exampleIndex = 0;
        for (int i = 0; i < numOfIterations; ++i) {
            if (exampleIndex >= dataSet.getExamples().size())
                exampleIndex = 0;

            double dotProduct = dotProduct(dataSet.getExample(exampleIndex), weights);
            double error = dataSet.getLabel(exampleIndex) - sigmoid(dotProduct);
            double sigDerivative = sigmoidDerivative(dotProduct);
            weights = updateWeights(weights, dataSet.getExample(exampleIndex), learningRate * sigDerivative * error);
            double output = sigmoid((dotProduct(weights, dataSet.getExample(exampleIndex))));

            System.out.println(String.format("output: %.4f", output));
            exampleIndex++;
        }
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
