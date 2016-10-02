import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class PerceptronTest {
    private static final String TRAIN_SET_1_PATH = "resources/dataSet1/train2.dat";
    private static final String TEST_SET_1_PATH = "resources/dataSet1/test2.dat";
    private static final String TRAIN_SET_2_PATH = "resources/dataSet2/train5.dat";
    private static final String TEST_SET_2_PATH = "resources/dataSet2/test5.dat";
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static DataSet trainSet1;
    private static DataSet testSet1;
    private static DataSet trainSet2;
    private static DataSet testSet2;

    @BeforeClass
    public static void setup() {
        try {
            trainSet1 = DataSet.fromFile(TRAIN_SET_1_PATH);
            testSet1 = DataSet.fromFile(TEST_SET_1_PATH);
            trainSet2 = DataSet.fromFile(TRAIN_SET_2_PATH);
            testSet2 = DataSet.fromFile(TEST_SET_2_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void arrange() {
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
    public void learn_handlesDataSet2WithThirtyIterationsAndPointThreeLearningRate() throws IOException {
        final int numOfIterations = 30;
        final double learningRate = 0.3;
        final String outputPath = "resources/dataSet2/sample-output/t5a0.3i30.txt";
        final String expectedOutput = new Scanner(new File(outputPath)).useDelimiter("\\Z").next();

        Perceptron.learn(trainSet2, testSet2, learningRate, numOfIterations);
        assertEquals(expectedOutput, out.toString());
    }

    @Test
    public void learn_handlesDataSet2WithSixtyIterationsAndPointThreeLearningRate() throws IOException {
        final int numOfIterations = 60;
        final double learningRate = 0.3;
        final String outputPath = "resources/dataSet2/sample-output/t5a0.3i60.txt";
        final String expectedOutput = new Scanner(new File(outputPath)).useDelimiter("\\Z").next();

        Perceptron.learn(trainSet2, testSet2, learningRate, numOfIterations);
        assertEquals(expectedOutput, out.toString());
    }

    @Test
    public void learn_handlesDataSet2WithThirtyIterationsAndPointNineLearningRate() throws IOException {
        final int numOfIterations = 30;
        final double learningRate = 0.9;
        final String outputPath = "resources/dataSet2/sample-output/t5a0.9i30.txt";
        final String expectedOutput = new Scanner(new File(outputPath)).useDelimiter("\\Z").next();

        Perceptron.learn(trainSet2, testSet2, learningRate, numOfIterations);
        assertEquals(expectedOutput, out.toString());
    }

    @Test
    public void learn_handlesDataSet2WithSixtyIterationsAndPointNineLearningRate() throws IOException {
        final int numOfIterations = 60;
        final double learningRate = 0.9;
        final String outputPath = "resources/dataSet2/sample-output/t5a0.9i60.txt";
        final String expectedOutput = new Scanner(new File(outputPath)).useDelimiter("\\Z").next();

        Perceptron.learn(trainSet2, testSet2, learningRate, numOfIterations);
        assertEquals(expectedOutput, out.toString());
    }

    @Test
    public void learn_handlesDataSet1With400IterationsAndPointThreeLearningRate() throws IOException {
        final int numOfIterations = 400;
        final double learningRate = 0.3;
        final String outputPath = "resources/dataSet1/sample-output/t2a0.3i400.txt";
        final String expectedOutput = new Scanner(new File(outputPath)).useDelimiter("\\Z").next();

        Perceptron.learn(trainSet1, testSet1, learningRate, numOfIterations);
        assertEquals(expectedOutput, out.toString());
    }

    @Test
    public void learn_handlesDataSet1With800IterationsAndPointThreeLearningRate() throws IOException {
        final int numOfIterations = 800;
        final double learningRate = 0.3;
        final String outputPath = "resources/dataSet1/sample-output/t2a0.3i800.txt";
        final String expectedOutput = new Scanner(new File(outputPath)).useDelimiter("\\Z").next();

        Perceptron.learn(trainSet1, testSet1, learningRate, numOfIterations);
        assertEquals(expectedOutput, out.toString());
    }

    @Test
    public void learn_handlesDataSet1With400IterationsAndPointNineLearningRate() throws IOException {
        final int numOfIterations = 400;
        final double learningRate = 0.9;
        final String outputPath = "resources/dataSet1/sample-output/t2a0.9i400.txt";
        final String expectedOutput = new Scanner(new File(outputPath)).useDelimiter("\\Z").next();

        Perceptron.learn(trainSet1, testSet1, learningRate, numOfIterations);
        assertEquals(expectedOutput, out.toString());
    }

    @Test
    public void learn_handlesDataSet1With800IterationsAndPointNineLearningRate() throws IOException {
        final int numOfIterations = 800;
        final double learningRate = 0.9;
        final String outputPath = "resources/dataSet1/sample-output/t2a0.9i800.txt";
        final String expectedOutput = new Scanner(new File(outputPath)).useDelimiter("\\Z").next();

        Perceptron.learn(trainSet1, testSet1, learningRate, numOfIterations);
        assertEquals(expectedOutput, out.toString());
    }
}
