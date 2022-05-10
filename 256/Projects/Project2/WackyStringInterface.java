package Projects.Project2;

public interface WackyStringInterface {

    void setWackyString(String string);

    String getWackyString();

    String getEveryThirdCharacter();

    String getEvenOrOddCharacters(String evenOrOdd);

    int countDoubleDigits();

    boolean isValidVCUEmail();

    String standardizePhoneNumber();

    void ramifyString();

    void convertDigitsToRomanNumeralsInSubstring(int startPosition, int 
endPosition) throws MyIndexOutOfBoundsException, IllegalArgumentException;

}   