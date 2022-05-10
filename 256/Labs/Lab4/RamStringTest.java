package Labs.Lab4;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class RamStringTest {
    /*WackyStringInterface normalString;

    @Before
    public void setUp() throws Exception {
        normalString = new RamString("Let's Go Rams!");
    }


    // Tests for the parameterized constructor
    @Test
    public void testRamStringParameterizedConstructor1() {
        assertTrue("Let's Go Rams!".equals(normalString.getWackyString()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRamStringParameterizedConstructor2() {
        RamString tryNull = new RamString(null);
    }

    // Tests for getEveryThirdCharacter method
    @Test
    public void testGetEveryThirdCharacter1() {
        assertTrue("t  m".equals(normalString.getEveryThirdCharacter()));
    }

    @Test
    public void testGetEveryThirdCharacter2() {
        RamString temp = new RamString(" ");
        assertTrue("".equals(temp.getEveryThirdCharacter()));
    }

    @Test
    public void testGetEveryThirdCharacter3() {
        RamString temp = new RamString("a");
        assertTrue("".equals(temp.getEveryThirdCharacter()));
    }
    
    // Tests for getEvenOrOddCharacters method
    @Test
    public void testGetEvenOrOddCharacters1() {
        assertTrue("e' oRm!".equals(normalString.getEvenOrOddCharacters("even")));
    }

    @Test
    public void testGetEvenOrOddCharacters2() {
        assertTrue("LtsG as".equals(normalString.getEvenOrOddCharacters("odd")));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetEvenOrOddCharacters3() {
        normalString.getEvenOrOddCharacters("");
    }
    
    // Tests for countDoubleDigits method
    @Test
    public void testCountDoubleDigits1() {
        assertEquals(0, normalString.countDoubleDigits());
    }

    @Test
    public void testCountDoubleDigits2() {
        RamString temp = new RamString("Computer Science @ VCU 50 g00d!");
        assertEquals(1, temp.countDoubleDigits());
    }

    @Test
    public void testCountDoubleDigits3() {
        RamString temp = new RamString("12a12");
        assertEquals(2, temp.countDoubleDigits());
    }

    // Tests for isValidVCUEmail method
    @Test
    public void testIsValidVCUEmail1() {
        assertEquals(false, normalString.isValidVCUEmail());
    }

    @Test
    public void testIsValidVCUEmail2() {
        RamString temp = new RamString("a@vcu.edu");
        assertEquals(true, temp.isValidVCUEmail());
    }

    @Test
    public void testIsValidVCUEmail3() {
        RamString temp = new RamString("@vcu.edu");
        assertEquals(false, temp.isValidVCUEmail());
    }

    @Test
    public void testIsValidVCUEmail4() {
        RamString temp = new RamString("a@myemail.vcu.edu");
        assertEquals(true, temp.isValidVCUEmail());
    }

    @Test
    public void testIsValidVCUEmail5() {
        RamString temp = new RamString("RamFan@gmail.com");
        assertEquals(false, temp.isValidVCUEmail());
    }

    // Tests for ramifyString method
    @Test
    public void testRamifyString1() {
        String temp = normalString.ramifyString();
        assertEquals("Let's Go Rams!", temp);
    }

    @Test
    public void testRamifyString2() {
        String temp = "0";
        assertEquals("Go Rams", ramifyString(temp));
    }

    @Test
    public void testRamifyString3() {
        String temp = "a";
        assertEquals("a", ramifyString(temp));
    }

    @Test
    public void testRamifyString4() {
        String temp = "00";
        assertEquals("CS@VCU", ramifyString(temp));
    }

    // Tests for convertDigitsToRomanNumberalsInSubstring method
    @Test
    public void testConversion1() {
        normalString.convertDigitsToRomanNumeralsInSubstring(1, 2);
    }

    @Test
    public void testConversion2() {
        normalString.convertDigitsToRomanNumeralsInSubstring(1, 3);
    }
 
    @Test (expected = IndexOutOfBoundsException.class)
    public void testConversion3() {
        normalString.convertDigitsToRomanNumeralsInSubstring(3,1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConversion4() {
        normalString.convertDigitsToRomanNumeralsInSubstring(2, 1);
    }*/
}
