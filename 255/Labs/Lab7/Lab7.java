package Labs.Lab7;

import java.util.Scanner;

public class Lab7 {
  
   public static void main(String[] args) {
     // variable declarations for part 1
     String title;
     String firstName;
     String lastName;
     Scanner in = new Scanner(System.in);

     // prompt for input for part 1
     System.out.print("Enter a title:");
     title = in.next();
     System.out.print("Enter your first name:");
     firstName = in.next();
     System.out.print("Enter a your last name:");
     lastName = in.next();

     // call the method for part 1
     greeting(title, firstName, lastName);
     
     // variable declarations for part 2
     int number1;
     int number2;
     
     // user prompts for part 2
     System.out.print("Enter first number:");
     number1 = in.nextInt();
     System.out.print("Enter second number:");
     number2 = in.nextInt();
     
     // call the methods max and sumTo inside the println statement
     System.out.println("The largest number is " + max(number1, number2));
     System.out.println("The sum of the numbers is " + sumTo(number1, number2));
 }// end of main method

 /******************** greeting method goes here*********************/
 public static void greeting(String title, String firstName, String lastName)
 {
     System.out.println();
     System.out.println("Dear " + title + " " + firstName + " " + lastName + ",");
     System.out.println();
 }
  //***********************end of method*************************/
  
  //******************** max method goes here*********************/
  public static int max(int num1, int num2)
  {
      return Math.max(num1, num2);
  }
  //***********************end of method*************************/

  //******************** sumTo method goes here*********************/
  public static int sumTo(int num1, int num2)
  {
      int sum = 0;

      if (num1 < num2) {
          while (num1 <= num2) {
              sum = sum + num1;
              num1++;
          }
      } else {
          while (num2 <= num1) {
              sum = sum + num2;
              num2++;
          }
      }
      return sum;
  }
  //***********************end of method*************************/
}
