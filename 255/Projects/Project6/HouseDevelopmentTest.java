/* **********************************************************
 * House Development Test
 * **********************************************************
 * Main method to test the House and Development classes
 * Krish Patel
 * 11/9/2021
 * CMSC 255 Section 004
 * **********************************************************/

package Labs.Project6;

public class HouseDevelopmentTest
{

    public static void main(String[] main){
        /* *******************************************************
         * Main method creating objects of development and houses
         * *******************************************************/
        Development development1 = new Development("Freeze Zone", "North Polar Ice Cap", 2023, 20);
        Development development2 = new Development("Crater Field", "Korolev crater", 2045, 100);

        House house1 = new House("Caroline Budwell", 15, 2754.99, Bedrooms.TWO_BEDROOM, Baths.TWO, Colors.GREEN);
        House house2 = new House("Sam Zu", 2, 2500.00, Bedrooms.THREE_BEDROOM, Baths.THREE, Colors.WHITE);
        House house3 = new House("Zach Whitten", 27, 789.45, Bedrooms.STUDIO, Baths.ONE, Colors.GRAY);

        /* ***************************************************
         * Adding a House object to the development ArrayList
         * ***************************************************/
        development1.addHouse(house1);
        development1.addHouse(house2);
        development2.addHouse(house3);

        /* *******************************************************************************************
         * Printing the developments accordingly, using the toString methods created within the class
         * *******************************************************************************************/
        System.out.println(development1.toString());
        System.out.println(development2.toString());
    }
}
