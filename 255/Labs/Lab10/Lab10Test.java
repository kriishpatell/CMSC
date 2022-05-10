package Labs.Lab10;

import Labs.Lab10.Fan;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

public class Lab10Test {


    @Test
    public void InstanceConstantsCountTest(){
        Fan testFan = new Fan();
        @SuppressWarnings("rawtypes")
        Class c = testFan.getClass();
        try {
            assertEquals(
                    "You must only have the instance variables specified. When looking at the number of instance variables we",
                    7, c.getDeclaredFields().length);
        }
        catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    @Test
    public void ConstantsTest(){
        Fan testFan = new Fan();
        @SuppressWarnings("rawtypes")
        Class c = testFan.getClass();
        try {
            c.getDeclaredField("SLOW");
            c.getDeclaredField("MEDIUM");
            c.getDeclaredField("FAST");

            assertEquals("You must make your constant variables public.", true,
                    Modifier.isPublic(c.getDeclaredField("SLOW").getModifiers()));
            assertEquals("You must make your constant variables public.", true,
                    Modifier.isPublic(c.getDeclaredField("MEDIUM").getModifiers()));
            assertEquals("You must make your constant variables public.", true,
                    Modifier.isPublic(c.getDeclaredField("FAST").getModifiers()));

            assertEquals("Your constant variables must be static.", true,
                    Modifier.isStatic(c.getDeclaredField("SLOW").getModifiers()));
            assertEquals("Your constant variables must be static.", true,
                    Modifier.isStatic(c.getDeclaredField("MEDIUM").getModifiers()));
            assertEquals("Your constant variables must be static.", true,
                    Modifier.isStatic(c.getDeclaredField("FAST").getModifiers()));

            assertEquals("Your constant variables must be final.", true,
                    Modifier.isFinal(c.getDeclaredField("SLOW").getModifiers()));
            assertEquals("Your constant variables must be final.", true,
                    Modifier.isFinal(c.getDeclaredField("MEDIUM").getModifiers()));
            assertEquals("Your constant variables must be final.", true,
                    Modifier.isFinal(c.getDeclaredField("FAST").getModifiers()));

            assertEquals("You must make the SLOW constant variable of type int.", (int.class),
                    c.getDeclaredField("SLOW").getType());
            assertEquals("You must make the MEDIUM constant variable of type int.", (int.class),
                    c.getDeclaredField("MEDIUM").getType());
            assertEquals("You must make the FAST constant variable of type int.", (int.class),
                    c.getDeclaredField("FAST").getType());

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage().toString() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    @Test
    public void InstanceVariablesTest(){
        Fan testFan = new Fan();
        @SuppressWarnings("rawtypes")
        Class c = testFan.getClass();
        try {
            c.getDeclaredField("speed");
            c.getDeclaredField("on");
            c.getDeclaredField("radius");
            c.getDeclaredField("color");

            assertEquals("You must make your instance variables private.", true,
                    Modifier.isPrivate(c.getDeclaredField("speed").getModifiers()));
            assertEquals("You must make your instance variables private.", true,
                    Modifier.isPrivate(c.getDeclaredField("on").getModifiers()));
            assertEquals("You must make your instance variables private.", true,
                    Modifier.isPrivate(c.getDeclaredField("radius").getModifiers()));
            assertEquals("You must make your instance variables private.", true,
                    Modifier.isPrivate(c.getDeclaredField("color").getModifiers()));

            assertEquals("Your instance variables must NOT be static.", false,
                    Modifier.isStatic(c.getDeclaredField("speed").getModifiers()));
            assertEquals("Your instance variables must NOT be static.", false,
                    Modifier.isStatic(c.getDeclaredField("on").getModifiers()));
            assertEquals("Your instance variables must NOT be static.", false,
                    Modifier.isStatic(c.getDeclaredField("radius").getModifiers()));
            assertEquals("Your instance variables must NOT be static.", false,
                    Modifier.isStatic(c.getDeclaredField("color").getModifiers()));

            assertEquals("You must make the speed instance variable of type int.", (int.class),
                    c.getDeclaredField("speed").getType());
            assertEquals("You must make the on instance variable of type boolean.", (boolean.class),
                    c.getDeclaredField("on").getType());
            assertEquals("You must make the radius instance variable of type double.", (double.class),
                    c.getDeclaredField("radius").getType());
            assertEquals("You must make the color instance variable of type String.", (String.class),
                    c.getDeclaredField("color").getType());

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage().toString() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    @Test
    public void fanDefaultConstructorTest() {
        Fan testFan = new Fan();

        testSpeed(testFan,1,"When checking the value of speed we");
        testOn(testFan,false,"When checking the value of on we");
        testRadius(testFan,5.0, "When checking the value of radius we");
        testColor(testFan,"blue","When checking the value of color we");
    }

    @Test
    public void parameterizedFanConstructor() {
        Fan testFan = new Fan(3,false,25.0,"Yellow");

        testSpeed(testFan,3,"When checking the value of speed we");
        testOn(testFan,false,"When checking the value of on we");
        testRadius(testFan,25.0, "When checking the value of radius we");
        testColor(testFan,"Yellow","When checking the value of color we");
    }

    @Test
    public void getSpeedTest() {
        Fan testFan = createFan(3,false,25.0,"Yellow");
        assertEquals("With a Fan object who's speed instance variable is 3, when calling getSpeed we",3,testFan.getSpeed());
    }

    @Test
    public void setSpeedTest() {
        Fan testFan = createFan(3,false,25.0,"Yellow");
        testFan.setSpeed(2);
        testSpeed(testFan,2,"After calling Fan's setSpeed method with an argument of 2, for the value of speed we");
    }

    @Test
    public void isOnTest() {
        Fan testFan = createFan(3,false,25.0,"Yellow");
        assertEquals("With a Fan object who's on instance variable is false, when calling isOn we",false,testFan.isOn());
    }

    @Test
    public void setOnTest() {
        Fan testFan = createFan(3,false,25.0,"Yellow");
        testFan.setOn(true);
        testOn(testFan,true,"After calling Fan's setOn method with an argument of true, for the value of on we");
    }

    @Test
    public void getRadiusTest() {
        Fan testFan = createFan(3,false,25.0,"Yellow");
        assertEquals("With a Fan object who's radius instance variable is 25.0, when calling getRadius we",25.0,testFan.getRadius(),.01);
    }

    @Test
    public void setRadiusTest() {
        Fan testFan = createFan(3,false,25.0,"Yellow");
        testFan.setRadius(15.55);
        testRadius(testFan,15.55,"After calling Fan's setRadius method with an argument of 15.55, for the value of radius we");
    }

    @Test
    public void getColorTest() {
        Fan testFan = createFan(3,false,25.0,"Yellow");
        assertEquals("With a Fan object who's color instance variable is Yellow, when calling getColor we","Yellow",testFan.getColor());
    }

    @Test
    public void setColorTest() {
        Fan testFan = createFan(3,false,25.0,"Yellow");
        testFan.setColor("Red");
        testColor(testFan,"Red","After calling Fan's setColor method with an argument of Red, for the value of color we");
    }

    @Test
    public void toStringTest() {
        Fan testFan = createFan(3,false,25.0,"Yellow");
        assertEquals("fan is off", testFan.toString());

        testFan = createFan(3,true,25.0,"Yellow");
        assertEquals("fan is 3, Yellow, and size 25.0", testFan.toString());
    }


    private Fan createFan(int aSpeed, boolean isOn, double aRadius, String aColor){
        Fan testFan = new Fan();
        @SuppressWarnings("rawtypes")
        Class c = testFan.getClass();

        try {
            Field speed = c.getDeclaredField("speed");
            speed.setAccessible(true);
            speed.set(testFan, aSpeed);

            Field on = c.getDeclaredField("on");
            on.setAccessible(true);
            on.set(testFan, isOn);

            Field radius = c.getDeclaredField("radius");
            radius.setAccessible(true);
            radius.set(testFan, aRadius);

            Field color = c.getDeclaredField("color");
            color.setAccessible(true);
            color.set(testFan, aColor);

        } catch (Exception e) {
            fail(e.toString());
        }

        return testFan;
    }

    private void testSpeed(Fan testFan, int expected, String message){
        @SuppressWarnings("rawtypes")
        Class c = testFan.getClass();
        try {
            Field speed = c.getDeclaredField("speed");
            speed.setAccessible(true);
            int speedValue = (int) speed.get(testFan);
            assertEquals(message, expected, speedValue);
        }
        catch (Exception e) {
            fail(e.toString());
        }
    }

    private void testOn(Fan testFan, boolean expected, String message){
        @SuppressWarnings("rawtypes")
        Class c = testFan.getClass();
        try {
            Field on = c.getDeclaredField("on");
            on.setAccessible(true);
            boolean onValue = (boolean) on.get(testFan);
            assertEquals(message, expected, onValue);
        }
        catch (Exception e) {
            fail(e.toString());
        }
    }

    private void testRadius(Fan testFan, double expected, String message){
        @SuppressWarnings("rawtypes")
        Class c = testFan.getClass();
        try {
            Field radius = c.getDeclaredField("radius");
            radius.setAccessible(true);
            double radiusValue = (double) radius.get(testFan);
            assertEquals(message, expected, radiusValue, .01);
        }
        catch (Exception e) {
            fail(e.toString());
        }
    }

    private void testColor(Fan testFan, String expected, String message){
        @SuppressWarnings("rawtypes")
        Class c = testFan.getClass();
        try {
            Field color = c.getDeclaredField("color");
            color.setAccessible(true);
            String colorValue = (String) color.get(testFan);
            assertEquals(message, expected, colorValue);
        }
        catch (Exception e) {
            fail(e.toString());
        }
    }

}
