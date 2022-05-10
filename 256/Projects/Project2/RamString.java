package Projects.Project2;

public class RamString implements WackyStringInterface {
    private String normalString;

    public RamString(){
        normalString = "Let's Go Rams and CS@VCU!";
    }

    public RamString(String normalString) throws IllegalArgumentException {
        if(normalString == null){
            throw new IllegalArgumentException("Invalid string");
        }
        this.normalString = normalString;
    }

    public String getEveryThirdCharacter(){
        String temp = "";
        for(int i = 0; i < normalString.length(); i++){
            if(i % 3 == 2){
                temp += (normalString.charAt(i));
            }
        }
        return temp;
    }

    public String getEvenOrOddCharacters(String evenOrOdd) throws IllegalArgumentException {
        String temp = "";
        
        if(evenOrOdd != "even" && evenOrOdd != "odd"){
            throw new IllegalArgumentException("Invalid input");
        }

        if(evenOrOdd == "even"){
            for(int i = 0; i < getWackyString().length(); i++){
                if((i+1) % 2 == 0){
                    temp += normalString.charAt(i);
                }
            }
        } else if (evenOrOdd == "odd"){
            for(int i = 0; i < getWackyString().length(); i++){
                if((i+1) % 2 == 1){
                    temp += normalString.charAt(i);
                }
            }
        }
        return temp;
    }

    public int countDoubleDigits(){
        int count = 0;
        for (int i = 0; i<normalString.length()-2; i++)
        {
            if (normalString.charAt(i)==normalString.charAt(i+1) && normalString.charAt(i)!=normalString.charAt(i+2) &&
            (Character.isDigit(normalString.charAt(i+2)) == false) && Character.isDigit(normalString.charAt(i)) == true) {
                if (i>0 && Character.isDigit(normalString.charAt(i-1)) == false)
                    count++;
                if (i==0)
                    count++;
            }
        }
        if (normalString.length()>3)
            if (normalString.charAt(normalString.length()-1) == normalString.charAt(normalString.length()-2) &&
                Character.isDigit(normalString.charAt(normalString.length()-3)) == false){
                    count++;
                }
        return count;
    }

    public boolean isValidVCUEmail() {
        if(normalString.indexOf('@') != 0 && (normalString.contains("@vcu.edu") || normalString.contains("@mymail.vcu.edu"))){
            return true;
        } else {
            return false;
        }
    }
    
    public String standardizePhoneNumber() {
        String phone;
        String number = "";
        int i = 0;
        while(i < normalString.length()){
            if(Character.isDigit(normalString.charAt(i))){
                number = number + normalString.charAt(i);
            }
            i++;
        }
        if(number.length() != 10 || number.length() == 0){
            return("This WackyString is not a phone number.");
        }
        phone = "(" + number.substring(0,3) + ") " + number.substring(3,6) + "-" + number.substring(6,10);
        return phone;
    }

    public void setWackyString(String string){
        if(string == null){
            throw new IllegalArgumentException("String cannot be null");
        } else {
            normalString = string;
        }
    }

    public String getWackyString(){
        return normalString;
    }

    public void ramifyString(){
        String begin;
        String end;
        String vcu = "Go Rams";
        String rams = "CS@VCU";
        outerloop: 
            for(int i = 0;i<normalString.length();i++)
            {    
                if(normalString.charAt(i) == '0' && ((i+1 < normalString.length()) && (normalString.charAt(i+1) =='0')) && 
                        (i+2 < normalString.length() && normalString.charAt(i+2) == '0')) 
                {
                    if(i+3 > normalString.length())
                    {
                        i=normalString.length();
                        break;
                    }
                    i+=3;
                    while(i<normalString.length() && normalString.charAt(i) == '0') 
                    {
                        i++;
                        if(i == normalString.length())
                        {
                            break outerloop;
                        }
                    }
                }
                
                if(normalString.charAt(i) =='0' && (((i+1 < normalString.length()) && (normalString.charAt(i+1) !='0')) || i+1 >= normalString.length()))
                {
                    begin = normalString.substring(0,i);
                    end = normalString.substring(i+1, normalString.length());
                    normalString = begin + vcu + end;
                }
               
                if(normalString.charAt(i) == '0' && ((i+1 < normalString.length()) && (normalString.charAt(i+1) =='0')))
                {
                    begin = normalString.substring(0,i);
                    end = normalString.substring(i+2, normalString.length());
                    normalString = begin + rams + end;  
                }
            }
    }

    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws MyIndexOutOfBoundsException, IllegalArgumentException {
        if(startPosition < 1 || startPosition > getWackyString().length() || (endPosition < 1 || endPosition > getWackyString().length()))
        {
            throw new MyIndexOutOfBoundsException();
        }
        if(startPosition > endPosition)
        {
            throw new IllegalArgumentException();
        }
        String wack = getWackyString();
        String replace = wack.replace("1","I");
        int less = startPosition - 1;
        while(less < endPosition)
        {
            if(getWackyString().charAt(less)=='1' || getWackyString().charAt(less)=='2' || getWackyString().charAt(less)=='3'|| getWackyString().charAt(less)=='4'|| getWackyString().charAt(less)=='5'|| getWackyString().charAt(less)=='6'|| getWackyString().charAt(less)=='7'|| getWackyString().charAt(less)=='8'|| getWackyString().charAt(less)=='9'){
                replace = wack.replace("1","I")
                              .replace("2","II")
                              .replace("3","III")
                              .replace("4","IV")
                              .replace("5","V")
                              .replace("6","VI")
                              .replace("7","VII")
                              .replace("8","VIII")
                              .replace("9","IX");
                break;
            }
            less++;
        }
        wack = replace;
        setWackyString(wack);
    }  
}
