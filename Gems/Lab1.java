// Darrious Barger

import java.util.Scanner;

public class Lab1
{
	public static void main(String[] args)
	{   
		Gems gem = new Gems();
		Scanner input = new Scanner(System.in);
		int total = 0;
        
        //Prompt user for number of gems
		System.out.println("How many gems are in the bag?");
		total = input.nextInt();

		//Create array the size of the number of gems
		//This is used to hold the value of each gem
		int gemsVal[] = gem.generateVals(total);


	}
}