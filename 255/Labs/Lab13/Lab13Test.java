package Labs.Lab13;

import Labs.Lab13.Team;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.Timeout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.Assert.*;


public class Lab13Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(3); // 3 seconds max per method tested

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    //Lab13 Tests

    @Test

    public void fullProgramTest() throws FileNotFoundException {
        File outputFile = generateOutputFile();
        Lab13.processFile(generateInputFile(),outputFile);

        assertTrue("Output file does not exist", outputFile.exists());

        Scanner outputScan;
        int i = 1;
        try {
            outputScan = new Scanner(outputFile);

            assertEquals("When looking at the first line of the output file, we","Maximum goals Scored: Monaco 15",outputScan.nextLine()); i++;
            assertEquals("When looking at the second line of the output file, we","Minimum goals Scored: Athletic Bilbao 1",outputScan.nextLine()); i++;
            assertEquals("When looking at the third line of the output file, we","Average shots per game: 15.565",outputScan.nextLine());
            outputScan.close();
        } catch (FileNotFoundException e) {
            fail("outputFile not found");
        } catch (NoSuchElementException e){
            fail("Output file line " + i + " and beyond are missing ");
        }


    }

    @Test
    public void commandLineArgumentTest(){
        File outputFile = generateOutputFile();
        String[] args = {generateInputFile().getAbsolutePath(),outputFile.getAbsolutePath()};
        Lab13.main(args);

        assertTrue("Output file does not exist", outputFile.exists());

        Scanner outputScan;
        int i = 1;
        try {
            outputScan = new Scanner(outputFile);

            assertEquals("When looking at the first line of the output file, we","Maximum goals Scored: Monaco 15",outputScan.nextLine()); i++;
            assertEquals("When looking at the second line of the output file, we","Minimum goals Scored: Athletic Bilbao 1",outputScan.nextLine()); i++;
            assertEquals("When looking at the third line of the output file, we","Average shots per game: 15.565",outputScan.nextLine());
            outputScan.close();
        } catch (FileNotFoundException e) {
            fail("outputFile not found");
        } catch (NoSuchElementException e){
            fail("Output file line " + i + " and beyond are missing ");
        }


    }

    //Team Tests

    @Test
    public void Team_instanceCountTest(){
        Team testTeam = new Team();
        @SuppressWarnings("rawtypes")
        Class c = testTeam.getClass();
        try {
            assertEquals(
                    "You must only have the instance variables specified. When looking at the number of instance variables in Card we",
                    3, c.getDeclaredFields().length);
        }
        catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    @Test
    public void Team_instanceVariablesTest(){
        Team testTeam = new Team();
        instanceVariablePrivate("name",testTeam);
        instanceVariablePrivate("numGoals",testTeam);
        instanceVariablePrivate("numShots",testTeam);

        instanceVariableStatic("name",testTeam);
        instanceVariableStatic("numGoals",testTeam);
        instanceVariableStatic("numShots",testTeam);

        instanceVariableCorrectType("name",String.class,testTeam);
        instanceVariableCorrectType("numGoals",int.class,testTeam);
        instanceVariableCorrectType("numShots",double.class,testTeam);
    }

    @Test
    public void Team_DefaultConstructorTest() {
        Team testTeam = new Team();
        testVariable("name",testTeam,"","When checking the value of name we",0);
        testVariable("numGoals",testTeam,0,"When checking the value of numGoals we",0);
        testVariable("numShots",testTeam,0.0,"When checking the value of numShots we",0);
    }

    @Test
    public void Team_ParameterizedConstructorTest() {
        Team testTeam = new Team("Boston Revolution",3,10.0);
        testVariable("name",testTeam,"Boston Revolution","When checking the value of name we",0);
        testVariable("numGoals",testTeam,3,"When checking the value of numGoals we",0);
        testVariable("numShots",testTeam,10.0,"When checking the value of numShots we",0);
    }

    @Test
    public void Team_getNameTest() {
        Team testTeam = createTeam("DC United",5,12.0);
        assertEquals("With a Team object who's name instance variable is \"DC United\", when calling getName we","DC United",testTeam.getName());
    }

    @Test
    public void Team_setNameTest() {
        Team testTeam = createTeam("DC United",5,12.0);
        testTeam.setName("USWNT");
        testVariable("name",testTeam,"USWNT","After calling teams's setName method with an argument of \"USWNT\", for the value of name we",0);
    }

    @Test
    public void Team_getNumGoalsTest() {
        Team testTeam = createTeam("DC United",5,12.0);
        assertEquals("With a Team object who's numGoals instance variable is 5, when calling getNumGoals we",5,testTeam.getNumGoals());
    }

    @Test
    public void Team_setNumGoalsTest() {
        Team testTeam = createTeam("DC United",5,12.0);
        testTeam.setNumGoals(7);
        testVariable("numGoals",testTeam,7,"After calling teams's setNumGoals method with an argument of 7, for the value of numGoals we",0);
    }

    @Test
    public void Team_getNumShotsTest() {
        Team testTeam = createTeam("DC United",5,12.0);
        assertEquals("With a Team object who's numShots instance variable is 12, when calling getNumShots we",12.0,testTeam.getNumShots(),.001);
    }

    @Test
    public void Team_setNumShotsTest() {
        Team testTeam = createTeam("DC United",5,12.0);
        testTeam.setNumShots(20);
        testVariable("numShots",testTeam,20.0,"After calling teams's setNumShots method with an argument of 20, for the value of numShots we",0);
    }


    private File generateInputFile(){
        final String INPUT_FILENAME = "foo.txt";
        File inputFile = null;
        try {
            inputFile = folder.newFile(INPUT_FILENAME);


            PrintWriter write = new PrintWriter(inputFile);
            write.print("Manchester United,10,20,\n" +
                    "Paris Saint Germain,14,18.5,\n" +
                    "Borussia Dortmund,5,15.5,\n" +
                    "Monaco,15,17.3,\n" +
                    "Juventus,8,17,\n" +
                    "Bayern Munich,5,16,\n" +
                    "Inter,6,15,\n" +
                    "Liverpool,8,18.3,\n" +
                    "Barcelona,3,19,\n" +
                    "Athletic Bilbao,1,11,\n" +
                    "Real Madrid,5,19,\n" +
                    "Huddersfield,3,10.7,\n" +
                    "RasenBallsport Leipzig,4,20.5,\n" +
                    "Leganes,2,13,\n" +
                    "Real Sociedad,6,14,\n" +
                    "Lyon,9,13.5,\n" +
                    "Napoli,5,16,\n" +
                    "Atletico Madrid,7,8.5,\n" +
                    "AC Milan,5,17,\n" +
                    "Torino,4,11.5,");
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputFile;

    }

    private File generateOutputFile(){
        final String OUTPUT_FILENAME = "bar.txt";
        File outputFile = null;
        try {
            outputFile = folder.newFile(OUTPUT_FILENAME);
            PrintWriter write = new PrintWriter(outputFile);
            write.print("");
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;

    }

    private Team createTeam(String aName, int aNumGoals, double aNumShotsPerGame){
        Team testTeam = new Team();
        @SuppressWarnings("rawtypes")
        Class c = testTeam.getClass();

        try {
            Field name = c.getDeclaredField("name");
            name.setAccessible(true);
            name.set(testTeam, aName);

            Field numGoals = c.getDeclaredField("numGoals");
            numGoals.setAccessible(true);
            numGoals.set(testTeam, aNumGoals);

            Field numShots = c.getDeclaredField("numShots");
            numShots.setAccessible(true);
            numShots.set(testTeam, aNumShotsPerGame);

        } catch (Exception e) {
            fail(e.toString());
        }

        return testTeam;
    }

    private void instanceVariablePrivate(String aField, Object testObject) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertTrue("You must make your instance variables private.", Modifier.isPrivate(c.getDeclaredField(aField).getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void instanceVariableStatic(String aField, Object testObject) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertEquals("Your instance variables must NOT be static.", false,
                    Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void instanceVariableCorrectType(String aField, Class<?> aClass,  Object testObject) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertEquals("You must make the speed instance variable of type"+ aClass.toString() +".", aClass, c.getDeclaredField(aField).getType());

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void testVariable(String aField, Object testObject, Object expected, String message, int descendantLevel){
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();

        for(int i = 0; i < descendantLevel; i++){
            c = c.getSuperclass();
        }

        try {
            Field field = c.getDeclaredField(aField);
            field.setAccessible(true);
            Object fieldValue = field.get(testObject);

            if(expected == null){
                assertNull(message,fieldValue);
            }
            //If class is a double we have a special Junit assert to run
            else if(expected.getClass().equals(Double.class)){
                double doubleFieldValue = (double) fieldValue;
                double doubleExpected = (double) expected;
                assertEquals(message, doubleExpected, doubleFieldValue, .01);
            }
            //Array of some kind yay
            else if(expected.getClass().isArray()){

            }
            else if(expected.getClass().equals(ArrayList.class)){

            }
            else{
                assertEquals(message, expected, fieldValue);
            }

        }
        catch (Exception e) {
            fail(e.toString());
        }
    }

}
