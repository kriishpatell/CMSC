package Labs.Project7;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Chapter7TestMoonStudy {

    private static final double EQ_DELTA = .01;
    private final String newLine = System.lineSeparator();
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    private ByteArrayOutputStream outStream;

    private void writeLinesToFile(String[] lines, File f) {
        try {
            PrintWriter pw = new PrintWriter(f);
            for (String line : lines) {
                pw.write(String.format("%s%s", line, newLine));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            fail("Something weird happened, please reach out to a TA.");
        }
    }

    private void assertContains(String message, String expected, String actual) {
        assertTrue(
                String.format("%s: \n Expected: %s\n Actual: %s", message, expected, actual),
                actual.contains(expected)
        );
    }

    private String normalizeNewlines(String actual) {
        return actual.replace("\r\n", "\n").replace("\r", "\n");
    }

    @Before
    public void setUpStreams() {
        outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    // assert openFile(File) throws FileNotFoundException for no such file
    @Test(expected = FileNotFoundException.class)
    public void testOpenFile_throwsFileNotFound_ifInvalidFile() throws FileNotFoundException {
        File f = new File("ThisIsInvalidFilePath");

        MoonStudy.openFile(f);
        fail("We expected openFile() to throw FileNotFoundException if passed an invalid " +
                     "file!");
    }


    // assert openFile(File) returns list of lines in file
    @Test
    public void testOpenFile_returnsListOfLinesInFile() throws IOException {
        String[] lines = {"This is line 1", "This is line 2"};
        File f = tempFolder.newFile();
        writeLinesToFile(lines, f);

        List<String> actual = MoonStudy.openFile(f);
        assertEquals(
                "When we write 2 lines to the file, we expect the number of lines returned: ",
                2,
                actual.size()
        );
        assertEquals(
                "When reading from the file we expect the first line to be ",
                lines[0],
                actual.get(0)
        );
        assertEquals(
                "When reading from the file we expect the second line to be ",
                lines[1],
                actual.get(1)
        );
    }


    // assert createObjects(ArrayList<String>) replaces non-int & negative moon value with 0
    @Test
    public void testCreateObjects_replacesNonNumericMoonValueWith0() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Moon X\tHello\t543.2\t645.88");
        ArrayList<Moon> moons = MoonStudy.createObjects(lines);

        assertEquals(
                "When we create a moon object with the radius of 'Hello' we expect ",
                0,
                moons.get(0).getRadius(),
                EQ_DELTA
        );
        assertEquals(
                "When we create a moon object with the radius of 'Hello' we still expect other values to be correct ",
                543.2,
                moons.get(0).getDensity(),
                EQ_DELTA
        );

    }

    @Test
    public void testCreateObjects_replacesNegativeMoonValueWith0() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Moon X\t-318.98\t543.2\t645.88");
        ArrayList<Moon> moons = MoonStudy.createObjects(lines);
        assertEquals(
                "When we create a moon object with the radius of '-318.98' we expect ",
                0,
                moons.get(0).getRadius(),
                .001
        );
        assertEquals(
                "When we create a moon object with the radius of '-318.98' we still expect other values to be correct ",
                543.2,
                moons.get(0).getDensity(),
                .001
        );
    }


    // assert findMean(ArrayList<Moon>, String) calculates average for given moon attribute
    @Test
    public void testFindMean_calculatesAvgValueForMoonAttribute() {
        ArrayList<Moon> moons = new ArrayList<>();
        moons.add(new Moon());
        moons.add(new Moon());
        moons.add(new Moon());
        moons.get(0).setRadius(103302.30);
        moons.get(1).setRadius(2048672.50);
        moons.get(2).setRadius(2094254.35);

        double actual = MoonStudy.findMean(moons, "radius");
        assertEquals("When we calculate the average of radius we expect ", 1415409.7166, actual, .01);
    }


    // assert findLowestMoon(ArrayList<Moon>, String, double) returns list of moons w/
    // population below value passed in
    @Test
    public void testFindLowestMoonsByValue_returnsMoonsWithValueBelow() {
        ArrayList<Moon> moons = new ArrayList<>();
        Moon moon1 = new Moon("MoonName_75", 123.50, 75.50, 343.60);
        Moon moon2 = new Moon("MoonName_100", 3453.50, 100.60, 8786.60);
        Moon moon3 = new Moon("MoonName_150", 56.50, 150.70, 3434.60);
        Moon moon4 = new Moon("MoonName_175", 532.60, 175.30, 34.60);
        Moon moon5 = new Moon("MoonName_124", 34.60, 124.20, 342.20);
        moons.add(moon1);
        moons.add(moon2);
        moons.add(moon3);
        moons.add(moon4);
        moons.add(moon5);
        ArrayList<Moon> actual = MoonStudy.findLowestMoons(moons, 125, "density");
        assertEquals(
                "When we call findLowestMoons with 3 moons lower than the value we " + "expect"
                        + " only 3 moons returned but we got",
                3,
                actual.size()
        );
        assertTrue(String.format(
                "When we call findLowestMoons we expect %s to be in " + "the returned "
                        + "list %s",
                "MoonName_124",
                actual.toString()
        ), actual.contains(moon5));
        assertTrue(String.format(
                "When we call findLowestMoons we expect %s to be in " + "the returned "
                        + "list %s",
                "MoonName_100",
                actual.toString()
        ), actual.contains(moon2));
        assertFalse(String.format(
                "When we call findLowestMoons we expect %s to not be in " + "the returned "
                        + "list %s",
                "MoonName_150",
                actual.toString()
        ), actual.contains(moon3));

    }


    // assert findMeanMoon(ArrayList<Moon>, String, double) returns the moon closest
    // to the value passed in
    @Test
    public void testFindMeanMoonByValue_returnsMoonClosestToValue() {
        ArrayList<Moon> moons = new ArrayList<>();
        Moon moon1 = new Moon("MoonName_75", 123.5, 75.5, 343.6);
        Moon moon2 = new Moon("MoonName_100", 3453.5, 100.6, 8786.6);
        Moon moon3 = new Moon("MoonName_150", 56.5, 150.7, 3434.6);
        Moon moon4 = new Moon("MoonName_175", 532.6, 175.3, 34.6);
        Moon moon5 = new Moon("MoonName_124", 34.6, 124.2, 342.2);
        moons.add(moon1);
        moons.add(moon2);
        moons.add(moon3);
        moons.add(moon4);
        moons.add(moon5);
        Moon actual = MoonStudy.findMeanMoon(moons, "distance", 343);
        assertTrue(String.format(
                "When we call findMeanMoon we expect %s to be the moon returned %s",
                "MoonName_75",
                actual.toString()
        ), actual.getName().equals("MoonName_75"));
    }


    // assert findHighValue(ArrayList<Moon>, String) returns the highest value of the given moon attribute
    @Test
    public void testFindHighValue_returnsHighestValueOfMoonAttribute() {
        ArrayList<Moon> moons = new ArrayList<>();
        Moon moon1 = new Moon("MoonName_75", 123.45, 75.5, 34.5);
        Moon moon2 = new Moon("MoonName_100", 543.45, 100.6, 63.4);
        Moon moon3 = new Moon("MoonName_150", 654.22, 150.7, 87.5);
        Moon moon4 = new Moon("MoonName_175", 234.55, 175.3, 95.7);
        Moon moon5 = new Moon("MoonName_125", 654.66, 125.2, 32.6);
        moons.add(moon1);
        moons.add(moon2);
        moons.add(moon3);
        moons.add(moon4);
        moons.add( moon5);

        double actual = MoonStudy.findHighValue(moons, "density");
        double expected = 175.3;
        assertEquals(
                String.format("When we call findHighValue on the density attribute with %s we expect ", moons),
                expected,
                actual,
                EQ_DELTA
        );
    }

    // assert outputToFile (String, ArrayList<Moon>, PrintWriter) correctly formatas and prints
    @Test
    public void testOutputToFile_withMoonList_printsData() {
        OutputStream out = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(out);
        ArrayList<Moon> values = new ArrayList<>();
        values.add(new Moon("MoonName_75", 123.534, 75.675, 343.659));
        values.add(new Moon("MoonName_100", 3453.598, 100.614, 8786.676));
        try {
            MoonStudy.outputToFile("The moons above the value are: ", values, pw);
        } catch (FileNotFoundException ex) {
            fail("Something weird happened. Please contact a TA.");
        } finally {
            pw.close();
        }
        String actual = normalizeNewlines(out.toString());
        String expected = "The moons above the value are: MoonName_75 123.53 75.68 343.66 " +
                "MoonName_100 3453.60 100.61 8786.68\n\n";

        assertEquals(String.format(
                "When we call outputToFile with a list of moons we expect %s but get %s",
                expected,
                actual
        ), expected.replace("\\s+", " ").trim(), actual.trim());

    }


    // assert outputToFile (String, Moon, PrintWriter) correctly formats and prints
    @Test
    public void testOutputToFile_withMoon_printsData() {
        OutputStream out = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(out);
        Moon moon = new Moon("MoonName_75", 123.534, 75.675, 343.659);

        try {
            MoonStudy.outputToFile("The moon closest to the mean is: ", moon, pw);
        } catch (FileNotFoundException ex) {
            fail("Something weird happened. Please contact a TA.");
        } finally {
            pw.close();
        }
        String actual = normalizeNewlines(out.toString());
        String expected = "The moon closest to the mean is: MoonName_75 123.53 75.68 343.66\n\n";
        assertEquals(
                "When we call outputToFile with a moon we expect ",
                expected.replace("\\s+", " ").trim(),
                actual.trim()
        );
        assertEquals(
                "Check your whitespace, your text looks correct but your spaces/newlines don't " + "match expected... ",
                expected,
                actual
        );
    }

    
    // assert outputToFile (String, double, PrintWriter) correctly formats and prints
    @Test
    public void testOutputToFile_withDouble_printsData() {
        OutputStream out = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(out);

        try {
            MoonStudy.outputToFile("The average value is: ", 129039.39, pw);
        } catch (FileNotFoundException ex) {
            fail("Something weird happened. Please contact a TA.");
        } finally {
            pw.close();
        }
        String actual = normalizeNewlines(out.toString());
        String expected = "The average value is: 129039.39\n\n";
        assertEquals(
                "When we call outputToFile with a double we expect ",
                expected.replace("\\s+", " ").trim(),
                actual.trim()
        );
        assertEquals(
                "Check your whitespace, your text looks correct but your spaces/newlines don't " + "match expected... ",
                expected,
                actual
        );
    }

    

}
