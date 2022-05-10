package Labs.Lab8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DogNamesLab {
	private static String promptForFileName() {
		System.out.println("Enter the file name: ");
		@SuppressWarnings("resource")
		Scanner keyIn = new Scanner(System.in);
		return keyIn.next();
	}

	private static Scanner openFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		while (!file.exists()) {
			file = new File(promptForFileName());
		}
		return new Scanner(file);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		// Read data file to build data structure
		ArrayList<Dog> doglist = new ArrayList<>();
		
		try {
			// verify file and create file Scanner
			 Scanner fileReader = openFile("Dog_Names.csv");

			//  Discard header line
			 fileReader.nextLine();
			 
			 while(fileReader.hasNextLine()) {
			 	String line = fileReader.nextLine();
			 	int commaIndex = line.indexOf(',');
			 	String name = line.substring(0, commaIndex).trim();
			 	int count = Integer.parseInt(line.substring(commaIndex+1).trim());
			 	doglist.add(new Dog(name, count));
			 }
			 fileReader.close();
		}
		catch(FileNotFoundException noFile){
			System.out.println("There was an error opening or reading from the file.");
			System.exit(0);
		}

		Scanner readInput = new Scanner(System.in);
		String prompt = "\nWhat do you want to do?\n" 
				+ "\t1. Check a dog name\n" + "\t2. See all the dog names\n"
				 + "\t3. Play a game\n" + "\t4. Exit"
				 		+ ".\n"
				+ "Enter the number corresponding to your choice.";
		
		System.out.println(prompt);
		int option = readInput.nextInt();
		
		switch(option) {
			case 1:
				System.out.println("Enter a dog’s name?");
				String name = in.nextLine();
				int nameCount = getCountForDog(doglist, name);
				System.out.println(name + " is registered " + nameCount + " times.");
				break;
			case 2:
				System.out.println(getDogNamesAlphabetically(doglist));
				break;
			case 3:
				playGuessingGame(doglist, in);
				break;
			default: System.out.println("Invalid option.");
		}
		in.close();
	}

	public static int getCountForDog(ArrayList<Dog> dogs, String name) {
		int count = 0;

		for(Dog dog : dogs){
			if(dog.getDogName().equalsIgnoreCase(name)){
				count = dog.getCount();
			} 
		}
		return count;
	}
	
	public static String getDogNamesAlphabetically(ArrayList<Dog> dogs) {
		Collections.sort(dogs, new Dog());
        String temp = "";
        
		for(Dog dog : dogs){
            temp += dog.getDogName() + "\n";
        }
        return temp;
	}

	public static void playGuessingGame(ArrayList<Dog> dogs, Scanner readIn) {
		int attempts = 0, correct = 0;
		String answer = "Y";
		
		do{
			Dog dog1 = dogs.get(new Random().nextInt(dogs.size()));
			Dog dog2 = dogs.get(new Random().nextInt(dogs.size()));
			attempts++;

			System.out.println("Which name is more popular for Anchorage dogs? (Type 1 or 2)\n");
			System.out.println("1. " + dog1.getDogName() + "\n2. "+ dog2.getDogName() + "\n");

			
			int choice = readIn.nextInt();

			if(choice == 1){
				if(dog1.getCount() > dog2.getCount()){
					correct++;
					System.out.println("Yes, that's right.");
				} 
				else{
					System.out.println("Nope, the more popular dog name is " + dog2.getDogName());
				} 
				System.out.println("Do you want to play again? (Y/N)");
			} 
			
			if(choice == 2){
				if(dog1.getCount() < dog2.getCount()){
					correct++;
					System.out.println("Yes, that's right.");
				} 
				else{
					System.out.println("Nope, the more popular dog name is " + dog1.getDogName());
				} 
				System.out.println("Do you want to play again? (Y/N)");
			} 
			
			readIn.nextLine();
			answer = readIn.nextLine();

			if(answer.equalsIgnoreCase("N")){
				System.out.println("You guessed correctly " + correct + " out of " + attempts + " times.");
			}

		} while(answer.equalsIgnoreCase("Y"));
	}
}