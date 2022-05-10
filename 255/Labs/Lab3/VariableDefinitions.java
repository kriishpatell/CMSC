public class VariableDefinitions
{
	public static void main(String[] args)
	{
		//initial declaration of numPeople variable
		byte numPeople = 3;

		System.out.println("The numPeople variable has a value of " + numPeople);

		//modified value of the numPeople variable
		numPeople = 6;

		System.out.println("The numPeople variable now has a value of " + numPeople);

		//initial declaration of numPeople constants
		final int NUM_PEOPLE_1 = 42;
		final float NUM_PEOPLE_2 = 100;
		final double NUM_PEOPLE_3 = 144;
		final byte NUM_PEOPLE_4 = 8; 

		System.out.println();
		System.out.println(NUM_PEOPLE_1);
		System.out.println(NUM_PEOPLE_2);
		System.out.println(NUM_PEOPLE_3);
		System.out.println(NUM_PEOPLE_4);

		//Attempting to change a constant value leads to an error and Java will not allow the file to compile
		//NUM_PEOPLE_1 = 12;
	}
}
