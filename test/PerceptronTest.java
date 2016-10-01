import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class PerceptronTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void tearDown() {
        out.reset();
    }

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
        final String dataSet = "Gee Fee Bee\n" +
                "0 0 1 0\n" +
                "1 0 1 0\n" +
                "0 1 1 0\n" +
                "1 1 1 1\n";
        final String expectedOutput = "After iteration 1: w(Gee) = 0.0000, w(Fee) = 0.0000, w(Bee) = -0.0375, output = 0.4906\n" +
                "After iteration 2: w(Gee) = -0.0368, w(Fee) = 0.0000, w(Bee) = -0.0743, output = 0.4723\n" +
                "After iteration 3: w(Gee) = -0.0368, w(Fee) = -0.0361, w(Bee) = -0.1103, output = 0.4635\n" +
                "After iteration 4: w(Gee) = 0.0038, w(Fee) = 0.0045, w(Bee) = -0.0698, output = 0.4846\n" +
                "After iteration 5: w(Gee) = 0.0038, w(Fee) = 0.0045, w(Bee) = -0.1059, output = 0.4735\n" +
                "After iteration 6: w(Gee) = -0.0317, w(Fee) = 0.0045, w(Bee) = -0.1414, output = 0.4568\n" +
                "After iteration 7: w(Gee) = -0.0317, w(Fee) = -0.0302, w(Bee) = -0.1762, output = 0.4486\n" +
                "After iteration 8: w(Gee) = 0.0097, w(Fee) = 0.0111, w(Bee) = -0.1348, output = 0.4715\n" +
                "After iteration 9: w(Gee) = 0.0097, w(Fee) = 0.0111, w(Bee) = -0.1696, output = 0.4577\n" +
                "After iteration 10: w(Gee) = -0.0246, w(Fee) = 0.0111, w(Bee) = -0.2039, output = 0.4431\n" +
                "After iteration 11: w(Gee) = -0.0246, w(Fee) = -0.0225, w(Bee) = -0.2375, output = 0.4354\n" +
                "After iteration 12: w(Gee) = 0.0173, w(Fee) = 0.0195, w(Bee) = -0.1956, output = 0.4604\n" +
                "After iteration 13: w(Gee) = 0.0173, w(Fee) = 0.0195, w(Bee) = -0.2291, output = 0.4430\n" +
                "After iteration 14: w(Gee) = -0.0159, w(Fee) = 0.0195, w(Bee) = -0.2623, output = 0.4309\n" +
                "After iteration 15: w(Gee) = -0.0159, w(Fee) = -0.0130, w(Bee) = -0.2947, output = 0.4237\n" +
                "After iteration 16: w(Gee) = 0.0265, w(Fee) = 0.0294, w(Bee) = -0.2523, output = 0.4510\n" +
                "After iteration 17: w(Gee) = 0.0265, w(Fee) = 0.0294, w(Bee) = -0.2846, output = 0.4293\n" +
                "After iteration 18: w(Gee) = -0.0056, w(Fee) = 0.0294, w(Bee) = -0.3168, output = 0.4201\n" +
                "After iteration 19: w(Gee) = -0.0056, w(Fee) = -0.0021, w(Bee) = -0.3483, output = 0.4133\n" +
                "After iteration 20: w(Gee) = 0.0371, w(Fee) = 0.0406, w(Bee) = -0.3055, output = 0.4433\n" +
                "After iteration 21: w(Gee) = 0.0371, w(Fee) = 0.0406, w(Bee) = -0.3366, output = 0.4166\n" +
                "After iteration 22: w(Gee) = 0.0059, w(Fee) = 0.0406, w(Bee) = -0.3678, output = 0.4105\n" +
                "After iteration 23: w(Gee) = 0.0059, w(Fee) = 0.0100, w(Bee) = -0.3984, output = 0.4041\n" +
                "After iteration 24: w(Gee) = 0.0489, w(Fee) = 0.0530, w(Bee) = -0.3554, output = 0.4370\n" +
                "After iteration 25: w(Gee) = 0.0489, w(Fee) = 0.0530, w(Bee) = -0.3854, output = 0.4048\n" +
                "After iteration 26: w(Gee) = 0.0185, w(Fee) = 0.0530, w(Bee) = -0.4158, output = 0.4020\n" +
                "After iteration 27: w(Gee) = 0.0185, w(Fee) = 0.0232, w(Bee) = -0.4455, output = 0.3960\n" +
                "After iteration 28: w(Gee) = 0.0617, w(Fee) = 0.0664, w(Bee) = -0.4024, output = 0.4319\n" +
                "After iteration 29: w(Gee) = 0.0617, w(Fee) = 0.0664, w(Bee) = -0.4312, output = 0.3938\n" +
                "After iteration 30: w(Gee) = 0.0321, w(Fee) = 0.0664, w(Bee) = -0.4609, output = 0.3944\n\n" +
                "Accuracy on training set (4 instances): 75.0%\n\n" +
                "Accuracy on test set (4 instances): 75.0%\n";
        DataSet trainingAndTest = convertToSet(dataSet); // training and test same are the same in this case.
        Perceptron.learn(trainingAndTest, trainingAndTest, 0.3, 30);
        assertEquals(expectedOutput, out.toString());
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
