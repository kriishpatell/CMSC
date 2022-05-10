package Labs.Project6;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class Project6DevelopmentTests {

    //region DevelopmentTests
    public void Development_instanceCountTest() {
        Development testDevelopment = new Development();
        @SuppressWarnings("rawtypes") Class c = testDevelopment.getClass();
        try {
            assertEquals(
                    "You must only have the instance variables specified. When looking at " + 
                            "the " + "number of instance variables we",
                    5,
                    c.getDeclaredFields().length
            );
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    @Test
    public void Development_instanceVariablesTest() {
        Development testDevelopment = new Development();
        instanceVariablePrivate("name", testDevelopment);
        instanceVariablePrivate("location", testDevelopment);
        instanceVariablePrivate("yearEst", testDevelopment);
        instanceVariablePrivate("numLots", testDevelopment);
        instanceVariablePrivate("houses", testDevelopment);

        instanceVariableStatic("name", testDevelopment);
        instanceVariableStatic("location", testDevelopment);
        instanceVariableStatic("yearEst", testDevelopment);
        instanceVariableStatic("numLots", testDevelopment);
        instanceVariableStatic("houses", testDevelopment);

        instanceVariableCorrectType("name", String.class, testDevelopment);
        instanceVariableCorrectType("location", String.class, testDevelopment);
        instanceVariableCorrectType("yearEst", int.class, testDevelopment);
        instanceVariableCorrectType("numLots", int.class, testDevelopment);
        instanceVariableCorrectType("houses", ArrayList.class, testDevelopment);
    }

    @Test
    public void Development_defaultConstructorTest() {
        Development testDevelopment = new Development();

        testVariable("name",
                     testDevelopment,
                     null,
                     "When checking the value of name we"
        );
        testVariable("location", 
        			       testDevelopment, 
        			       null, 
        			       "When checking the value of location we");

        testVariable("yearEst",
                     testDevelopment,
                     0,
                     "When checking the value of yearEst" + " we"
        );
        testVariable("numLots",
                     testDevelopment,
                     0,
                     "When checking the value of " + "numLots we"
        );
        testVariable("houses",
                     testDevelopment,
                     new ArrayList<>(),
                     "When checking the value of " + "houses we"
        );

    }

    @Test
    public void Development_parameterizedConstructorTest() {
        Development testDevelopment = new Development("Foreign Fields", "Far, Far, Away", 2003, 203);

        testVariable("name",
                     testDevelopment,
                     "Foreign Fields",
                     "When checking the value of " + "name we"
        );
        testVariable("location", 
        			 testDevelopment, 
        			 "Far, Far, Away", 
        			 "When checking the value of location we");

        testVariable("yearEst",
                     testDevelopment,
                     2003,
                     "When checking the value of " + "yearEst we"
        );
        testVariable("numLots",
                     testDevelopment,
                     203,
                     "When checking the value of " + "numLots we"
        );
        testVariable("houses",
                     testDevelopment,
                     new ArrayList<>(),
                     "When checking the value of " + "houses we"
        );

    }

    @Test
    public void Development_getNameTest() {
        ArrayList<House> someHouse = new ArrayList<>();
        Development testDevelopment = createDevelopment("Foreign Fields", "Far, Far, Away", 2003, 203, someHouse
        );
        assertEquals(
                "With a Development object who's name instance variable is `Foreign Fields`, when calling getName we",
                "Foreign Fields",
                testDevelopment.getName()
        );
    }

    @Test
    public void Development_setNameTest() {
        ArrayList<House> someHouse = new ArrayList<>();
        Development testDevelopment = createDevelopment("Foreign Fields", "Far, Far, Away", 2003, 203, someHouse
        );

        testDevelopment.setName("Cat's Meow");
        testVariable("name",
                     testDevelopment,
                     "Cat's Meow",
                     "After calling Development's setName method with an argument of `Cat's Meow`, for the value of name we"
        );
    }

    @Test
    public void Development_getLocation() {
        ArrayList<House> someHouse = new ArrayList<>();
        Development testDevelopment = createDevelopment("Foreign Fields", "Far, Far, Away", 2003, 203, someHouse
        );
        assertEquals(
                "With a Development object who's location instance variable is `Far, Far, Away`, when " + "calling getLocation we",
                "Far, Far, Away",
                testDevelopment.getLocation()
        );
    }

    @Test
    public void Development_setLocation() {
        ArrayList<House> someHouse = new ArrayList<>();
        Development testDevelopment = createDevelopment("Foreign Fields", "Far, Far, Away", 2003, 203, someHouse
        );

        testDevelopment.setLocation("Raven's Bluff");
        testVariable("location",
                     testDevelopment,
                     "Raven's Bluff",
                     "After calling Development's setLocation method with an argument of " + 
                     "`Raven's Bluff`, for the value of location we"
        );
    }


    @Test
    public void Development_getYearEstTest() {
        ArrayList<House> someHouse = new ArrayList<>();
        Development testDevelopment = createDevelopment("Foreign Fields", "Far, Far, Away", 2003, 203, someHouse
        );
        assertEquals(
                "With a Development object who's yearEst instance variable is 2003, " + "when calling getYearEst we",
                2003,
                testDevelopment.getYearEst()
        );
    }

    @Test
    public void Development_setYearEstTest() {
        ArrayList<House> someHouse = new ArrayList<>();
        Development testDevelopment = createDevelopment("Foreign Fields", "Far, Far, Away", 2003, 203, someHouse
        );

        testDevelopment.setYearEst(2013);
        testVariable("yearEst",
                     testDevelopment,
                     2013,
                     "After calling Development's setYearEst method with an argument of " + 
                             "`2013`, for the value of yearEst we"
        );
    }

    @Test
    public void Development() {
        ArrayList<House> someHouse = new ArrayList<>();
        Development testDevelopment = createDevelopment("Foreign Fields", "Far, Far, Away", 2003, 203, someHouse
        );
        assertEquals(
                "With a Development object who's numLots instance variable is 203, " + "when"
                        + " calling getNumLots we",
                203,
                testDevelopment.getNumLots()
        );
    }

    @Test
    public void Development_setNumLotsTest() {
        ArrayList<House> someHouse = new ArrayList<>();
        Development testDevelopment = createDevelopment("Foreign Fields", "Far, Far, Away", 2003, 203, someHouse
        );
        testDevelopment.setNumLots(199);
        testVariable("numLots",
                     testDevelopment,
                     199,
                     "After calling :Development's setNumLots method with an argument of 199,"
                             + " for the value of numLots we"
        );
    }

    @Test
    public void Development_getNumHousesTest() {
        ArrayList<House> someHouse = new ArrayList<>();
        someHouse.add(createHouse("House A",
                                          56,
                                          345.6,
                                          Bedrooms.STUDIO,
                                          Baths.ONE,
                                          Colors.YELLOW
        ));
        someHouse.add(createHouse("House B",
                                          89,
                                          7973.2,
                                          Bedrooms.TWO_BEDROOM,
                                          Baths.THREE,
                                          Colors.BRICK
        ));
        Development testDevelopment = createDevelopment("Foreign Fields", "Far, Far, Away", 2003, 203, someHouse
        );
        assertEquals(
                "With a Development object who's houses instance variable is has two " + 
                        "elements, when calling getNumHouses we",
                2,
                testDevelopment.getNumHouses()
        );
    }

    @Test
    public void Development_getHousesTest() {
        ArrayList<House> someHouse = new ArrayList<>();
        someHouse.add(createHouse("House A",
                                          56,
                                          345.6,
                                          Bedrooms.STUDIO,
                                          Baths.ONE,
                                          Colors.YELLOW
        ));
        someHouse.add(createHouse("House B",
                                          89,
                                          7973.2,
                                          Bedrooms.TWO_BEDROOM,
                                          Baths.THREE,
                                          Colors.BRICK
        ));

        ArrayList<House> expectedHouse = new ArrayList<>();
        expectedHouse.add(createHouse("House A",
                                          56,
                                          345.6,
                                          Bedrooms.STUDIO,
                                          Baths.ONE,
                                          Colors.YELLOW
        ));
        expectedHouse.add(createHouse("House B",
                                          89,
                                          7973.2,
                                          Bedrooms.TWO_BEDROOM,
                                          Baths.THREE,
                                          Colors.BRICK
        ));
        Development testDevelopment = createDevelopment("Foreign Fields", "Far, Far, Away", 2003, 203, someHouse
        );

        testHouseArray(
                "With a Development object who's houses instance variable has two " + "elements,"
                        + " when calling getHouses we",
                expectedHouse,
                testDevelopment.getHouses()
        );
    }

    @Test
    public void Development_addHouseOneHouseTest() {
        ArrayList<House> someHouse = new ArrayList<>();

        ArrayList< House> expectedHouse = new ArrayList<>();
        expectedHouse.add(createHouse("House A",
                                          56,
                                          345.6,
                                          Bedrooms.STUDIO,
                                          Baths.ONE,
                                          Colors.YELLOW
        ));
        Development testDevelopment = createDevelopment("Foreign Fields", "Far, Far Away", 2003, 203, someHouse
        );


        testDevelopment.addHouse(createHouse("House A",
                                          56,
                                          345.6,
                                          Bedrooms.STUDIO,
                                          Baths.ONE,
                                          Colors.YELLOW
        ));

        testVariable("houses",
                     testDevelopment,
                     expectedHouse,
                     "After a Development object adds a single house, when checking " + "the " + "houses instance variable we"
        );

    }

    @Test
    public void Development_orderHousesFourHouseTest() {

        ArrayList<House> expectedHouse = new ArrayList<>();

        expectedHouse.add(createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        ));
        expectedHouse.add(createHouse("House B",
                                          8,
                                          7973.2,
                                          Bedrooms.TWO_BEDROOM,
                                          Baths.TWO,
                                          Colors.GREEN
        ));
        expectedHouse.add(createHouse("House C",
                                          21,
                                          239.6,
                                          Bedrooms.STUDIO,
                                          Baths.ONE,
                                          Colors.WHITE
        ));
        expectedHouse.add(createHouse("House D",
                                          3,
                                          829.4,
                                          Bedrooms.THREE_BEDROOM,
                                          Baths.THREE,
                                          Colors.BRICK
        ));


        Development testDevelopment = createDevelopment("Pluto Spot",
                                               "Wild, Wild West",
                                               2040,
                                               23,
                                               new ArrayList<House>()
        );


        testDevelopment.addHouse(createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        ));
        testDevelopment.addHouse(createHouse("House B",
                                          8,
                                          7973.2,
                                          Bedrooms.TWO_BEDROOM,
                                          Baths.TWO,
                                          Colors.GREEN
        ));
        testDevelopment.addHouse(createHouse("House C",
                                          21,
                                          239.6,
                                          Bedrooms.STUDIO,
                                          Baths.ONE,
                                          Colors.WHITE
        ));
        testDevelopment.addHouse(createHouse("House D",
                                          3,
                                          829.4,
                                          Bedrooms.THREE_BEDROOM,
                                          Baths.THREE,
                                          Colors.BRICK
        ));


        testVariable("houses",
                     testDevelopment,
                     expectedHouse,
                     "After a Development object adds four houses, when checking " + "the " + 
                             "houses instance variable " + "we"
        );

    }

    @Test
    public void Development_toStringTest() {
        ArrayList<House> someHouse = new ArrayList<>();
        someHouse.add(createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        ));
        Development testDevelopment = createDevelopment("Pluto Spot",
                                               "Wild, Wild, West",
                                               2040,
                                               23,
                                               someHouse
        );

        String expected1 =
                        "Pluto Spot\n" +
                        "Wild, Wild, West\n" +
                        "2040\n" +
                        "23\n" +
                        "Houses:\n" + "\n" +
                        "\tHouse A" +
                        "\n\t5" +
                        "\n\t345.60" +
                        "\n\tONE_BEDROOM" +
                        "\n\tONE" +
                        "\n\tYELLOW";
        String actual1 = testDevelopment.toString().replace("\r\n", "\n");
        assertTrue(actual1.contains(expected1));


        someHouse = new ArrayList<>();
        someHouse.add(createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        ));
        someHouse.add(createHouse("House B",
                                          8,
                                          7973.2,
                                          Bedrooms.TWO_BEDROOM,
                                          Baths.TWO,
                                          Colors.GREEN
        ));
        testDevelopment = createDevelopment("Foreign Fields", 
                                              "Far, Far Away", 
                                              2003, 
                                              203, 
                                              someHouse
        );

        String expected2 =
                "Foreign Fields\nFar, Far Away\n2003\n203\nHouses:\n" + "\n" + 
                        "\tHouse A\n\t5\n\t345.60\n\tONE_BEDROOM\n\tONE\n\tYELLOW\n" + "\n" + 
                        "\tHouse B\n\t8\n\t7973.20\n\tTWO_BEDROOM\n\tTWO\n\tGREEN";
        String actual2 = testDevelopment.toString().replace("\r\n", "\n");
        assertTrue(actual2.contains(expected2));
    }
    //endregion
    //TODO HouseTests

    @Test
    public void House_getPurchaser() {
        House expectedHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        assertEquals("When we call getPurchaser on the house we",
                     expectedHouse.getPurchaser(),
                     "House A"
        );
    }

    @Test
    public void House_setPurchaser() {
        House expectedHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        expectedHouse.setPurchaser("Cat's Meow");
        testVariable("purchaser",
                     expectedHouse,
                     "Cat's Meow",
                     "When we call setPurchaser with the name `Cat's Meow` we"
        );
    }

    @Test
    public void House_getLotNumber() {
        House testHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        assertEquals("When we call getLotNumber on the house we",
                     testHouse.getLotNumber(),
                     5
        );
    }

    @Test
    public void House_setLotNumber() {
        House expectedHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        expectedHouse.setLotNumber(7);
        testVariable("lotNumber",
                     expectedHouse,
                     7,
                     "When we call setLotNumber with the number 7 we"
        );
    }

    @Test
    public void House_getSquareFootage() {
        House expectedHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        assertEquals("When we call getSquareFootage on the house we",
                     expectedHouse.getSquareFootage(),
                     345.6, .001
        );
    }

    @Test
    public void House_setSquareFootage() {
        House expectedHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        expectedHouse.setSquareFootage(783.45);
        testVariable("squareFootage",
                     expectedHouse,
                     783.45,
                     "When we call setSquareFootage with the value 783.45 we"
        );
    }

    @Test
    public void House_getBedrooms() {
        House expectedHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        assertEquals("When we call getBedrooms on the house we",
                     expectedHouse.getBedrooms(), Bedrooms.ONE_BEDROOM
        );
    }

    @Test
    public void House_setBedrooms() {
        House expectedHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        expectedHouse.setBedrooms(Bedrooms.TWO_BEDROOM);
        testVariable("bedrooms",
                     expectedHouse,
                     Bedrooms.TWO_BEDROOM,
                     "When we call setBedrooms with Bedrooms.TWO_BEDROOM we"
        );
    }

    @Test
    public void House_getBaths() {
        House expectedHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        assertEquals("When we call getBaths on the house we",
                     expectedHouse.getBaths(), Baths.ONE
        );
    }

    @Test
    public void House_setBaths() {
        House expectedHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        expectedHouse.setBaths(Baths.TWO);
        testVariable("baths",
                     expectedHouse,
                     Baths.TWO,
                     "When we call setBaths with Baths.TWO we"
        );
    }

    @Test
    public void House_getColors() {
        House expectedHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        assertEquals("When we call getColors on the house we",
                     expectedHouse.getColors(), Colors.YELLOW
        );
    }

    @Test
    public void House_setColors() {
        House expectedHouse = createHouse("House A",
                                          5,
                                          345.6,
                                          Bedrooms.ONE_BEDROOM,
                                          Baths.ONE,
                                          Colors.YELLOW
        );
        expectedHouse.setColors(Colors.GREEN);
        testVariable("colors",
                     expectedHouse,
                     Colors.GREEN,
                     "When we call setColors with Colors.GREEN we"
        );
    }


    private Development createDevelopment(String aName,
                                    String aLocation,
                                    int aYearEst,
                                    int aNumLots,
                                    ArrayList<House> aHouses
    ) {
        Development testDevelopment = new Development();
        @SuppressWarnings("rawtypes") Class c = testDevelopment.getClass();

        try {
            Field name = c.getDeclaredField("name");
            name.setAccessible(true);
            name.set(testDevelopment, aName);

            Field location = c.getDeclaredField("location");
            location.setAccessible(true);
            location.set(testDevelopment, aLocation);

            Field yearEst = c.getDeclaredField("yearEst");
            yearEst.setAccessible(true);
            yearEst.set(testDevelopment, aYearEst);

            Field numLots = c.getDeclaredField("numLots");
            numLots.setAccessible(true);
            numLots.set(testDevelopment, aNumLots);

            Field houses = c.getDeclaredField("houses");
            houses.setAccessible(true);
            houses.set(testDevelopment, aHouses);

        } catch (Exception e) {
            fail(e.toString());
        }

        return testDevelopment;
    }

    private House createHouse(String aPurchaser,
                                      int aLotNumber,
                                      double aSquareFootage,
                                      Bedrooms aBedrooms,
                                      Baths aBaths,
                                      Colors aColors
    ) {
        House testHouse = new House();
        @SuppressWarnings("rawtypes") Class c = testHouse.getClass();

        try {
            Field purchaser = c.getDeclaredField("purchaser");
            purchaser.setAccessible(true);
            purchaser.set(testHouse, aPurchaser);

            Field lotNumber = c.getDeclaredField("lotNumber");
            lotNumber.setAccessible(true);
            lotNumber.set(testHouse, aLotNumber);

            Field squareFootage = c.getDeclaredField("squareFootage");
            squareFootage.setAccessible(true);
            squareFootage.set(testHouse, aSquareFootage);

            Field bedrooms = c.getDeclaredField("bedrooms");
            bedrooms.setAccessible(true);
            bedrooms.set(testHouse, aBedrooms);

            Field baths = c.getDeclaredField("baths");
            baths.setAccessible(true);
            baths.set(testHouse, aBaths);

            Field colors = c.getDeclaredField("colors");
            colors.setAccessible(true);
            colors.set(testHouse, aColors);

        } catch (Exception e) {
            fail(e.toString());
        }

        return testHouse;
    }

    private void instanceVariablePrivate(String aField,
                                         Object testObject
    ) {
        @SuppressWarnings("rawtypes") Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertTrue(String.format("You must make your instance variables private: %s is not " + "private",
                                     aField
            ), Modifier.isPrivate(c.getDeclaredField(aField).getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void instanceVariableStatic(String aField,
                                        Object testObject
    ) {
        @SuppressWarnings("rawtypes") Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertEquals("Your instance variables must NOT be static.",
                         false,
                         Modifier.isStatic(c.getDeclaredField(aField).getModifiers())
            );

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void instanceVariableCorrectType(String aField,
                                             Class<?> aClass,
                                             Object testObject
    ) {
        @SuppressWarnings("rawtypes") Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertEquals(
                    "You must make the " + aField + " instance variable of type" + aClass.toString() + ".",
                    aClass,
                    c.getDeclaredField(aField).getType()
            );

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void testVariable(String aField,
                              Object testObject,
                              Object expected,
                              String message
    ) {
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

            } else if (expected.getClass().equals(ArrayList.class)) {
                //CUSTOM FOR PROJECT6TESTS!!!
                testHouseArray(message, (ArrayList) expected, (ArrayList) fieldValue);
            } else {
                assertEquals(message, expected, fieldValue);
            }

        } catch (Exception e) {
            fail(e.toString());
        }
    }

    private void testHouseArray(String message,
                                    ArrayList expected,
                                    ArrayList actual
    ) {
        assertEquals(message + " looked at the size and ", expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            if (!HouseIsEqual(expected.get(i), actual.get(i))) {
                assertEquals(message, expected, actual);
            }
        }
    }

    private boolean HouseIsEqual(Object o1,
                                     Object o2
    ) {
        @SuppressWarnings("rawtypes") Class c = o1.getClass();
        try {
            Field purchaserFieldo1 = c.getDeclaredField("purchaser");
            purchaserFieldo1.setAccessible(true);
            Object purchasero1 = purchaserFieldo1.get(o1);

            Field purchaserFieldo2 = c.getDeclaredField("purchaser");
            purchaserFieldo2.setAccessible(true);
            Object purchasero2 = purchaserFieldo2.get(o2);

            Field lotNumberFieldo1 = c.getDeclaredField("lotNumber");
            lotNumberFieldo1.setAccessible(true);
            Object lotNumbero1 = lotNumberFieldo1.get(o1);

            Field lotNumberFieldo2 = c.getDeclaredField("lotNumber");
            lotNumberFieldo2.setAccessible(true);
            Object lotNumbero2 = lotNumberFieldo2.get(o2);

            Field squareFootageFieldo1 = c.getDeclaredField("squareFootage");
            squareFootageFieldo1.setAccessible(true);
            Object squareFootageo1 = squareFootageFieldo1.get(o1);

            Field squareFootageFieldo2 = c.getDeclaredField("squareFootage");
            squareFootageFieldo2.setAccessible(true);
            Object squareFootageo2 = squareFootageFieldo2.get(o2);

            Field bedroomsFieldo1 = c.getDeclaredField("bedrooms");
            bedroomsFieldo1.setAccessible(true);
            Object bedroomso1 = bedroomsFieldo1.get(o1);

            Field bedroomsFieldo2 = c.getDeclaredField("bedrooms");
            bedroomsFieldo2.setAccessible(true);
            Object bedroomso2 = bedroomsFieldo2.get(o2);

            Field bathsFieldo1 = c.getDeclaredField("baths");
            bathsFieldo1.setAccessible(true);
            Object bathso1 = bathsFieldo1.get(o1);

            Field bathsFieldo2 = c.getDeclaredField("baths");
            bathsFieldo2.setAccessible(true);
            Object bathso2 = bathsFieldo2.get(o2);

            Field colorsFieldo1 = c.getDeclaredField("colors");
            colorsFieldo1.setAccessible(true);
            Object colorso1 = colorsFieldo1.get(o1);

            Field colorsFieldo2 = c.getDeclaredField("colors");
            colorsFieldo2.setAccessible(true);
            Object colorso2 = colorsFieldo2.get(o2);

            if (purchasero1.equals(purchasero2) && lotNumbero1.equals(lotNumbero2) && squareFootageo1.equals(
                    squareFootageo2) && bedroomso1.equals(bedroomso2) && bathso1.equals(bathso2) && colorso1.equals(colorso2) 
            ) {
                return true;
            } else {
                return false;
            }


        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }

        return false;
    }

}