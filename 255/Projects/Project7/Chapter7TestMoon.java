package Labs.Project7;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

public class Chapter7TestMoon {
    private static final double EQ_DELTA = .01;

    @Test
    public void testInstanceVarsExistAndArePrivate() {
        Moon moon = new Moon();
        String[] priv_vars = {"name", "radius", "density", "distance"};
        for (String var : priv_vars) {
            assertInstanceVariablePrivate(var, moon);
            assertInstanceVariableNotStatic(var, moon);
        }
    }

    // Test Default Constructor


    @Test
    public void testDefaultConstructorIsCorrect() {
        Moon moon = new Moon();
        assertInstanceVariableIsExpected("name",
                                         moon,
                                         null,
                                         "When we use the default constructor" + " we expect the "
                                                 + "name to be `null`"
        );
        assertInstanceVariableIsExpected("radius",
                                         moon,
                                         0.0,
                                         "When we use the default " + "constructor " + "we" + " " + "expect the radius to be 0.0"
        );
        assertInstanceVariableIsExpected("density",
                                         moon,
                                         0.0,
                                         "When we use the default " + "constructor we" + " expect"
                                                 + " the radius to be 0.0"
        );
        assertInstanceVariableIsExpected("distance",
                                         moon,
                                         0.0,
                                         "When we use the default " + "constructor we" + " expect"
                                                 + " the distance to be 0.0"
        );
    }

    // Test Parameterized Constructor
    @Test
    public void testParameterizedConstructorIsCorrect() {
        Moon moon = new Moon("Moon X",
                          2342.56,
                          435.25,
                          543.92
        );
        assertInstanceVariableIsExpected("name",
                                         moon,
                                         "Moon X",
                                         "When we use the " + "parameterized constructor we "
        );
        assertInstanceVariableIsExpected("radius",
                                         moon,
                                         2342.56,
                                         "When we use the " + "parameterized constructor we "
        );
        assertInstanceVariableIsExpected("density",
                                         moon,
                                         435.25,
                                         "When we use the " + "parameterized constructor we "
        );
        assertInstanceVariableIsExpected("distance",
                                         moon,
                                         543.92,
                                         "When we use the " + "parameterized constructor we "
        );
    }

    //Test setName(String) sets
    @Test
    public void testSetNameSetsValue() {
        Moon moon = createMoon("Moon X",
                          2342.56,
                          435.25,
                          543.92
        );

        moon.setName("Moon A");
        assertEquals("When we call setName() we ", "Moon A", moon.getName());
    }

    // Test setRadius(double) sets
    @Test
    public void testSetRadiusSetsValue() {
        Moon moon = createMoon("Moon X",
                          2342.56,
                          435.25,
                          543.92
        );
        moon.setRadius(333.54);
        assertEquals("When we call setCreator() we expect the radius to be updated!",
                     333.54,
                     moon.getRadius(),
                     EQ_DELTA
        );
    }

    // Test setDensity(double)
    @Test
    public void testSetDensitySetsValue() {
        Moon moon = createMoon("Moon X",
                          2342.56,
                          435.25,
                          543.92
        );
        moon.setDensity(983.20);
        assertEquals("When we call setDensity() we expect the density to be updated!",
                     983.20,
                     moon.getDensity(),
                     EQ_DELTA
        );
    }

    // Test setDistanceFromSun(double)
    @Test
    public void testSetDistanceSetsValue() {
        Moon moon = createMoon("Moon X",
                          2342.56,
                          435.25,
                          543.92
        );
        moon.setDistance(54.8);
        assertEquals(
                "When we call setDistance() we expect the distance to be updated!",
                54.8,
                moon.getDistance(),
                EQ_DELTA
        );
    }

    // Test getName(): String
    @Test
    public void testGetNameReturnsValue() {
        Moon moon = createMoon("Moon X",
                          2342.56,
                          435.25,
                          543.92
        );
        assertEquals("When we called moon.getName() we ", "Moon X", moon.getName());
    }


    // Test getRadius(): double
    @Test
    public void testGetRadiusReturnsValue() {
        Moon moon = createMoon("Moon X",
                          2342.56,
                          435.25,
                          543.92
        );
        assertEquals("When we called moon.getRadius() we ", 2342.56, moon.getRadius(), EQ_DELTA);
    }

    // Test getDensity(): double
    @Test
    public void testGetDensityReturnsValue() {
        Moon moon = createMoon("Moon X",
                          2342.56,
                          435.25,
                          543.92
        );
        assertEquals("When we called moon.getDensity() we ", 435.25, moon.getDensity(), EQ_DELTA);
    }

    // Test getDistanceFromSun(): Double
    @Test
    public void testGetDistanceReturnsValue() {
        Moon moon = createMoon("Moon X",
                          2342.56,
                          435.25,
                          543.92
        );
        assertEquals(
                "When we called moon.getDistance we ",
                543.92,
                moon.getDistance(), EQ_DELTA
        );
    }

    // Test toString(): String
    @Test
    public void testToStringReturnsExpectedValues() {
        Moon moon = createMoon("Moon X",
                          2342.567,
                          435.254,
                          543.929
        );
        String[] expected_tok = ("Moon X 2342.57 435.25 543.93")
                .split(" ");
        String actual = moon.toString();
        for (String each : expected_tok) {
            assertTrue(String.format(
                    "When we called toString, we expected the returned value to contain %s but " + "we" + " got %s",
                    each,
                    actual
            ), actual.contains(each));
        }
    }


    private Moon createMoon(String name, double radius, double density, double distance) {
        Moon testMoon = new Moon();
        @SuppressWarnings("rawtypes") Class c = testMoon.getClass();

        try {
            Field aname = c.getDeclaredField("name");
            aname.setAccessible(true);
            aname.set(testMoon, name);

            Field aradius = c.getDeclaredField("radius");
            aradius.setAccessible(true);
            aradius.set(testMoon, radius);

            Field adensity = c.getDeclaredField("density");
            adensity.setAccessible(true);
            adensity.set(testMoon, density);

            Field adistance = c.getDeclaredField("distance");
            adistance.setAccessible(true);
            adistance.set(testMoon, distance);

        } catch (Exception e) {
            fail(e.toString());
        }

        return testMoon;
    }

    private void assertInstanceVariablePrivate(String aField, Object testObject) {
        @SuppressWarnings("rawtypes") Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertTrue(String.format(
                    "You must make your instance variables private: `%s.%s` is not private",
                    c.getSimpleName(),
                    aField
            ), Modifier.isPrivate(c.getDeclaredField(aField).getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void assertInstanceVariableNotStatic(String aField, Object testObject) {
        @SuppressWarnings("rawtypes") Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertFalse(String.format(
                    "Your instance variables must NOT be static: `%s.%s` is static!",
                    c.getSimpleName(),
                    aField
            ), Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void assertInstanceVariableIsExpected(String aField,
                                                  Object testObject,
                                                  Object expected,
                                                  String message) {
        @SuppressWarnings("rawtypes") Class c = testObject.getClass();
        try {
            Field field = c.getDeclaredField(aField);
            field.setAccessible(true);
            Object fieldValue = field.get(testObject);

            if (expected == null) {
                assertNull(message, fieldValue);
            }
            //If class is a double we have a special Junit assert to run
            else if (expected.getClass().equals(Double.class)) {
                double doubleFieldValue = (double) fieldValue;
                double doubleExpected = (double) expected;
                assertEquals(message, doubleExpected, doubleFieldValue, .01);
            }
            //Array of some kind yay
            else if (expected.getClass().isArray()) {

            } else {
                assertEquals(message, expected, fieldValue);
            }

        } catch (Exception e) {
            fail(e.toString());
        }
    }

    private Object getPrivateInstanceVariable(String varName,
                                              Object o) throws NoSuchFieldException {
        Class c = o.getClass();
        Field field = c.getDeclaredField(varName);
        field.setAccessible(true);
        try {
            return field.get(o);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Something weird happened:\n" + e.getLocalizedMessage());
        }


    }
}
