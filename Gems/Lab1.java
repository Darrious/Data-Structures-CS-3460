// Darrious Barger

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Lab1
{
	public static void main(String[] args) throws FileNotFoundException
	{   
		Gems gem = new Gems();
		Scanner input = new Scanner(System.in);
		int total = 0, choice = 0;
        
		// Menu
		while (choice != 1 && choice != 2)
		{
			System.out.println("Read from file - 1\nRandom Gems - 2");
			choice = input.nextInt();
		}
        


		if(choice == 1)
		{
			// Prompts user for file path
			System.out.println("Enter file path (./filename if the file is in this directory):" );
			String fileName = input.nextLine();
			ArrayList<Integer> gemList = new ArrayList<Integer>();
			fileName = input.nextLine();
			
			// Creating File scanner
			File file = new File(fileName);
			Scanner fileReader = new Scanner(file);
					
			// Add gem values to arraylist
			while(fileReader.hasNextLine())
			{
				gemList.add(fileReader.nextInt());
			}

			fileReader.close();
			int[] gemsVal = new int[gemList.size()];


			// Convert array list to array.. I think I'm making this complicated
			int i = 0;
		    System.out.println("\nGem Values: ");
			for (int g : gemList)
			{
				System.out.print(g + " ");
				gemsVal[i++] = g;
			}


			// This holds the gems after going through the bubble sort algorithm
			int gemsSorted[] = gem.sortGems(gemsVal);

			// This holds a two dimensial array of the piles
			int gemsDivided[][] = gem.divide(gemsSorted);


		}

		if(choice == 2)
		{
	        //Prompt user for number of gems
			System.out.println("\nHow many gems are in the bag?");
			total = input.nextInt();

			//Create array the size of the number of gems
			//This is used to hold the value of each gem
			int gemsVal[] = gem.generateVals(total);

			// This holds the gems after going through the bubble sort algorithm
			int gemsSorted[] = gem.sortGems(gemsVal);

			// This holds a two dimensial array of the piles
			int gemsDivided[][] = gem.divide(gemsSorted);
		}
	

	}
}