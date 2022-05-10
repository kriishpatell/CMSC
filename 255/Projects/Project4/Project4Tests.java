package Labs.Project4;

import Labs.Project4.MoonSearch;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;

public class Project4Tests {

    // region Variable setup and helper methods
    // I use this so I can run locally and verify output
    static final double DELTA = .1;

    // System independent newline
    public final String newline = ("\\r?\\n");
    public MoonSearch unit;

    // I like to add this no matter what so if the submission loops,
    // Gradescope doesn't get stuck.
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);

    Pattern number_match_pattern = Pattern.compile("\\d+(\\.\\d*)?\\b");
    String[] names = {"Moon A", "Moon B", "Moon C", "Moon D",
            "Moon E"};
    double[] radii = {2726.70, 1762.04, 1556.74, 1966.90, 1479.84};
    double[] density = {283737.78, 342030.04, 152593.58, 103621.97, 379450.12};
    double[] distance = {737.2, 30.5, 593.3, 621.97, 9450.6};
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    @Before
    public void initUnit() {
        this.unit = new MoonSearch();
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

    public String[] getOutputForInputOf(String input, boolean getLowerCase) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        unit.main(new String[0]);
        String[] rawOutput = outContent.toString().split(newline);
        if (getLowerCase) {
            for (int i = 0; i < rawOutput.length; i++) {
                rawOutput[i] = rawOutput[i].toLowerCase();
            }
        }
        return rawOutput;
    }

    public String[] getOutputFromProcessInputForInputOf(String input, boolean getLowerCase) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        unit.main(new String[0]);
        String[] rawOutput = outContent.toString().split(newline);
        if (getLowerCase) {
            for (int i = 0; i < rawOutput.length; i++) {
                rawOutput[i] = rawOutput[i].toLowerCase();
            }
        }
        return rawOutput;
    }

    public String[] getOutputForInputOf(String input) {
        return getOutputForInputOf(input, true);
    }

    public String getCalculationResult(String input) throws NoSuchMatchException {
        Matcher matcher = number_match_pattern.matcher(input);
        if (!matcher.find()) {
            throw new NoSuchMatchException(String.format("\"%s\" does not contain a recognizable "
                    + "number!", input));
        }
        String g = matcher.group(0);
        return g;
    }

    // endregion

    /* *******************************
     *
     *  Tests that all methods exist: 30pts
     *
     * ******************************** */
    // region Test Methods Exists

    @Test
    public void testCalcAvgMethodExists() {
        doesMethodExist("calcAvg", double[].class);
    }

    @Test
    public void testFindHighValueMethodExists() {
        doesMethodExist("findHighValue", double[].class);
    }

    @Test
    public void testfindLeastValueMethodExists() {
        doesMethodExist("findLeastValue", double[].class);
    }

    @Test
    public void testFindHighestTwoMethodExists() {
        doesMethodExist("findHighestTwo", String[].class, double[].class);
    }

    @Test
    public void testFindLeastTwoMethodExists() {
        doesMethodExist("findLeastTwo", String[].class, double[].class);
    }

    @Test
    public void testFineMoonMethodExists() {
        doesMethodExist("findMoon", String[].class, String.class);
    }

    public void doesMethodExist(String methodName, Class<?>... argTypes) {
        try {
            Method declaredMethods = unit.getClass().getMethod(methodName, argTypes);
        } catch (NoSuchMethodException ex) {
            fail(String.format("We could not find a method \"%s\". Check your spelling and the " + "arguments it accepts.", methodName));
        }
    }
    // endregion

    /* ***************************************
     * Tests that all methods work properly: 20 pts
     *************************************** */
    // region Test Methods Work Properly
    @Test
    public void testProcessInputMethodCorrect() {
        String[] lines = getOutputFromProcessInputForInputOf("Moon B", true);
        try {
            TestHelpers.assertContains("We expect the first line to be ", "average radius", lines[0]);
            TestHelpers.assertContains("We expect the second line to be ", "average density", lines[1]);
            TestHelpers.assertContains("We expect the third line to be ", "highest radius", lines[2]);
            TestHelpers.assertContains("We expect the fourth line to be ", "lowest distance", lines[3]);
            TestHelpers.assertContains("We expect the fifth line to be ", "highest two moons for " +
                    "radii", lines[4]);
            TestHelpers.assertContains("We expect the ninth line to be ", "lowest two moons", lines[7]);
            TestHelpers.assertContains("We expect the thirteenth line to be ", "enter a moon", lines[10]);
            TestHelpers.assertContains("We expect the fourteenth line to be ", "moon b is not a moon " +
                    "in the array", lines[11]);
        } catch (IndexOutOfBoundsException ex) {
            fail(String.format("We expect 14 lines of output from your program but only got %s",
                    lines.length));
        }
    }

    @Test
    public void testCalcAvgWorks() {
        double expected = 7532238.166;
        double actual = unit.calcAvg(new double[]{100.3048, 30128349.52, 482.450, 20.391});
        assertEquals("Your calculation is not what we expected...", expected, actual, .005);
    }

    @Test
    public void testFindHighValueMethodCorrect() {
        double expectedHighest = 108673558.048;
        double expectedLowest = 1034.2;
        double[] fakeValues = {102942.02, expectedLowest, 1039.0344829, expectedHighest,
                049726484.01938};
        double actual = unit.findHighValue(fakeValues);
        assertEquals(String.format("When we give pass %s to the findHighValue() method, we ",
                fakeValues), expectedHighest, actual, .01);
    }

    @Test
    public void testFindLeastValueMethodCorrect() {
        double expectedHighest = 108673558.048;
        double expectedLowest = 1034.2;
        double[] fakeValues = {102942.02, expectedLowest, 1039.0344829, expectedHighest,
                049726484.01938};
        double actual = unit.findLeastValue(fakeValues);
        assertEquals(String.format("When we give pass %s to the findLeastValue() method, we ",
                fakeValues), expectedLowest, actual, .01);
    }

    @Test
    public void testFindLeastTwoMethodCorrect() {
        List<String> results = Arrays.asList(unit.findLeastTwo(names, radii));
        String[] expected = new String[]{"Moon E", "Moon C"};
        for (String s : expected) {
            assertTrue(String.format("We expect `%s` in the results but get `%s`", s, results),
                    results.contains(s));
        }
    }

    @Test
    public void testFindHighestTwoMethodCorrect() {
        List<String> results = Arrays.asList(unit.findHighestTwo(names, radii));
        String[] expected = new String[]{"Moon A", "Moon D"};
        for (String s : expected) {
            assertTrue(String.format("We expect `%s` in the results but get `%s`", s, results),
                    results.contains(s));
        }
    }

    @Test
    public void testFindPlanetMethodCorrect() {
        String moon = "Moon C";
        assertEquals(String.format("When we search `%s` for %s we ", names, moon), true,
                unit.findMoon(names, moon));

        moon = "Moon X";
        assertEquals(String.format("When we search `%s` for %s we ", names, moon), false,
                unit.findMoon(names, moon));
    }
    // endregion

    /* ****************************
     * Test output is rounded: 5 pts
     **************************** */
    // region Test output is rounded
    @Test
    public void testOutputIsRoundedCorrectly() {
        String input = TestHelpers.getInputStringFrom("Moon X");
        String[] output = getOutputForInputOf(input, true);
        try {
            // Normalizing the string...
            // Remove ',' from the string, in case they formatted the thousand's separators ("1,
            // 000" vs "1000")...
            for (int i = 0; i < 4; i++) {
                output[i] = output[i].replace(",", "");
                String line = output[i];
                String line_calculation = getCalculationResult(line);
                try {
                    String decimal = String.valueOf(line_calculation).split("\\.")[1]; // Get the
                    // decimal part of the regex match only
                    assertTrue(String.format("When we check '%s' we expect the number to be " +
                                    "printed " + "to EXACTLY one decimal place"
                            , line), decimal.length() == 1);
                } catch (IndexOutOfBoundsException ex) { //  Thrown in there is no decimal place
                    // in the result
                    fail("You should be printing all non-address numbers to 1 decimal place, i" + ".e" + "., '10.0'");
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            fail("We expected there to be at least 3 lines of output!");
        }
    }
    // endregion
} // End of Tests class

class NoSuchMatchException extends RuntimeException {
    /**
     * A custom exception that is raised when a regex does not find an expected match.
     *
     * @param errMsg Error message
     */
    public NoSuchMatchException(String errMsg) {
        super(errMsg);
    }
}




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

