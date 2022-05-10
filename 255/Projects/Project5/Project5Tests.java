package Labs.Project5;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;

public class Project5Tests {

    //region Variable setup and helper methods

    // I use this so I can run locally and verify output
    static final boolean DEBUG = false;
    static final double DELTA = .05;

    public MoonSamples unit;

    // I like to add this no matter what so if the submission loops,
    // Gradescope doesn't get stuck.

//    @Rule
//    public Timeout globalTimeout = Timeout.seconds(10);


    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    // System independent newline
    public final String newline = System.getProperty("line.separator");


    @Before
    public void initUnit() {
        this.unit = new MoonSamples();
    }

    @Before
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
    //endregion


    /* *******************************
     *
     *  Tests that all methods work: 0pts
     *  Helper tests for students' debugging
     *
     * ******************************** */

    //region Test that methods exist

    @Test
    public void testGetElementsMethodExists() {
        doesMethodExist("getElements", String.class);
    }


    @Test
    public void testGetSamplesMethodExists() {
        doesMethodExist("getSamples", String.class);

    }


    @Test
    public void testsearchForLifeMethodExists() {
        doesMethodExist("searchForLife", double[][].class);

    }


    @Test
    public void testsearchHighestElementsMethodExists() {
        doesMethodExist("searchHighestElements", double[][].class, String[].class, int.class);

    }


    @Test
    public void testsearchHighestSampleMethodExists() {
        doesMethodExist("searchHighestSample", double[][].class, String[].class, String.class);

    }

    //endregion

    /* *******************************
     *
     *  Tests that all methods work: 20pts
     *
     * ******************************** */
    //region Test Methods work



    @Test
    public void testGetElementsMethodWorks() {
        String ELEMENTS = "Helium,Oxygen,Iodine,Carbon,Boron,Lithium";
        assertTrue(Arrays.deepEquals(unit.getElements(ELEMENTS), ELEMENTS.split(",")));
    }


    @Test
    public void testGetSamplesMethodWorks() {
        String values_string =   "1.0,.9,.25,6.5,4.3,.22<>" +
                ".9,1.0,.7,4.1,3.5,.34<>" +
                ".25,.7,1.0,.6,1.2,2.7";
        double[][] actual = unit.getSamples(values_string);
        double[][] expected = {{1.0,.9,.25,6.5,4.3,.22},{.9,1.0,.7,4.1,3.5,.34},{.25,.7,1.0,.6,1.2,2.7}};
        assertTrue(
                String.format("When calling getSamples with %s, we expected %s but got %s",
                        values_string, Arrays.deepToString(expected), Arrays.deepToString(actual)),
                Arrays.deepEquals(actual, expected));
    }


    //endregion


    /* *******************************
     *
     *  Tests that all methods work: 30pts
     *
     * ******************************** */
    //region Test Output is correct

    @Test
    public void testSearchForLifeGeneratesEmptyArrayForInvalidSamples(){

        double[][] expectedSamples = {{1.0,.9,.25,1.4,2.3,3.2},{.9,1.0,.7,1.2,.4,.45},{.25,.7,1.0,4.3,5.2,1.2}};

        int[] actual = unit.searchForLife(expectedSamples);
        int[] expected = new int[0]; // empty string array
        int expectedSize = 0;   // The result of searchCompatibility should return an array that is resized to only contain
        // the matching elements

        assertEquals(
                "We expect that the result should match no elements!",
                actual.length, expectedSize
        );
    }


    @Test
    public void testSearchForLifeReturnsArrayThatFitsLikeAGlove(){

        double[][] samples = {
                {19.3, 16.75, 33.6, 45.9, 55.3, 85.4},   // Sample 1
                {.75, 1, .5, .8, .5, .6},   // Sample 2
                {75.6, 45.5, 32, 83.3, 93.4, 54.2},    // Sample 3
        };

        int[] actual = unit.searchForLife(samples);
        int expectedSize = 2;   // The result of searchCompatibility should return an array that is resized to only contain
        // the matching elements

        assertEquals(
                "We expect that the result should ONLY have the same number of elements as matches!",
                actual.length, expectedSize
        );
    }


    @Test
    public void testSearchForLifeForGoodMatchesIncludesFlavorsWithScoresAbove350(){

        double[][] samples = {
                {19.3, 16.75, 33.6, 45.9, 55.3, 85.4},   // Sample 1
                {.75, 1, .5, .8, .5, .6},               // Sample 2
                {75.6, 45.5, 32, 83.3, 93.4, 54.2},    // Sample 3
        };
        int[] actual = unit.searchForLife(samples);
        int[] expected = {1, 3};
        assertEquals(
                "We expect that the result should ONLY have the same number of elements as matches!",
                actual[0], expected[0]
        );
        assertEquals(
                "We expect that the result should ONLY have the same number of elements as matches!",
                actual[1], expected[1]
        );
        assertEquals(
                "We expect that the result should ONLY have the same number of elements as matches!",
                actual.length, expected.length
        );
    }

    @Test
    public void testSearchHighestElementsReturnsHighestElementsForSample(){
        String[] elements = {"carbon-dioxide","magnesium","sodium","potassium","chloride","water"};
        double[][] samples = {
                {19.3, 16.75, 33.6, 45.9, 55.3, 85.4},   // Sample 1
                {.75, 1, .5, .8, .5, .6},               // Sample 2
                {75.6, 45.5, 32, 83.3, 93.4, 54.2},    // Sample 3
        };
        String expected = "magnesium and potassium";
        String actual = unit.searchHighestElements(samples, elements, 2);
        TestHelpers.assertContains("When the user searchHighestElements with sample 2 we ", expected, actual);
    }


    @Test
    public void testSearchHighestSampleReturnsSampleWithHighestValueForElement(){
        String[] elements = {"carbon-dioxide","magnesium","sodium","potassium","chloride","water"};
        double[][] samples = {
                {19.3, 16.75, 33.6, 45.9, 55.3, 85.4},   // Sample 1
                {.75, 1, .5, .8, .5, .6},               // Sample 2
                {75.6, 45.5, 32, 83.3, 93.4, 54.2},    // Sample 3
        };
        String element = "sodium";
        int actual = unit.searchHighestSample(samples, elements, element);
        int expected = 1;

        // We assert that the result SHOULD return the first sample: `expected`
        assertEquals(expected, actual);

    }

    //endregion


    // Checks to see if a method with a given name exists. Method must accepts an arguments
    // specified in argTypes, specified by type of argument, not the name of the argument var
    public void doesMethodExist(String methodName, Class<?>... argTypes) {
        try {
            Method declaredMethods = unit.getClass().getMethod(methodName, argTypes);
        } catch (NoSuchMethodException ex) {
            fail(String.format(
                    "We could not find a method \"%s\". Check your spelling and the arguments it accepts.",
                    methodName
            ));
        }
    }
    //endregion

} // End of Tests class

class TestHelpers {
    public static String getInputStringFrom(String... input) {
        return String.join("\n", input);
    }

    public static String getInputStringFrom(int... input) {
        ArrayList<String> stringInput = new ArrayList<>(input.length);
        for (int num : input)
            stringInput.add(Integer.toString(num));
        return String.join("\n", stringInput);
    }

    public static void assertContains(String message, String expected, String actual) {
        assertTrue(String.format("%s: \n Expected: %s\n Actual: %s", message, expected, actual),
                actual.contains(expected));
    }
}