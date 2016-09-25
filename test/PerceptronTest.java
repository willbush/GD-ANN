import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class PerceptronTest {
    @Test(expected = DataSet.EmptyFileException.class)
    public void canThrowIfGivenEmptyFile() throws IOException {
        final String emptyFileTest = "resources/dataFormatTest/emptyFile.dat";
        DataSet.fromFile(emptyFileTest);
    }

    @Test
    public void canCorrectlyParseFileWithExtraWhiteSpace() throws IOException {
        final String dataFormatTest = "resources/dataFormatTest/extraWhiteSpace.dat";
        final String expected = "A B C\n" +
                "0 0 0 0\n" +
                "0 0 1 0\n" +
                "0 1 0 0\n" +
                "0 1 1 0\n" +
                "1 0 0 0\n" +
                "1 0 1 0\n" +
                "1 1 0 0\n" +
                "1 1 1 1\n";
        assertEquals(expected, DataSet.fromFile(dataFormatTest).toString());
    }

    @Test
    public void canGetDotProduct() {
        assertEquals(0.3, Perceptron.dotProduct(Arrays.asList(0.0, 0.0, 1.0), Arrays.asList(0.1, 0.2, 0.3)), 0.001);
        assertEquals(0.6, Perceptron.dotProduct(Arrays.asList(1.0, 1.0, 1.0), Arrays.asList(0.1, 0.2, 0.3)), 0.001);
    }

    @Test
    public void createPerceptron() {
        final String dataSet = "A B C\n" +
                "0 0 1 0\n" +
                "1 0 1 0\n" +
                "0 1 1 0\n" +
                "1 1 1 1\n";
        Perceptron.learn(convertToSet(dataSet), 0.3, 30);
    }

    private static DataSet convertToSet(String data) {
        final String whitespaceRegex = "\\s+";
        String[] lines = data.split("\\n");
        List<List<Double>> observations = new LinkedList<>();
        List<String> attributeNames = Arrays.asList(lines[0].split(whitespaceRegex));
        List<Double> labels = new ArrayList<>(observations.size());

        for (int i = 1; i < lines.length; ++i) {
            String[] rowValues = lines[i].split(whitespaceRegex);
            labels.add(rowValues[rowValues.length - 1].equals("1") ? 1.0 : 0.0);

            List<Double> attributeValues = new ArrayList<>(rowValues.length);

            for (int j = 0; j < rowValues.length - 1; ++j) {
                attributeValues.add(rowValues[j].equals("1") ? 1.0 : 0.0);
            }

            observations.add(attributeValues);
        }
        return new DataSet(attributeNames, observations, labels);
    }
}
