// Darrious Barger

import java.util.Random;
import java.util.Scanner;

public class Gems
{
	//This method generates random gem values between 0 and 50000
	public int[] generateVals(int total)
	{
		Random r = new Random();
		int gemsVal[] = new int[total];


		for(int i = 0; i < gemsVal.length; i++)
		{
			gemsVal[i] = r.nextInt(50000);
		}

		System.out.println("Gem Values:");
		for(int i = 0; i < gemsVal.length; i++)
		{
			System.out.print(gemsVal[i] + " ");
		}

		return gemsVal;

	}
	
	// I implemented a bubble sort algorithm to sort the gems
	public int[] sortGems(int[] gems)
	{
		boolean sorted = false;
		int temp = 0;

		while(true)
		{
			sorted = true;
			for (int i = 0; i < gems.length; i++)
			{
				if (i < gems.length - 1)
				{
					//System.out.println("i = " + gems[i] + " i+1 = " gems[i+1]);

					if (gems[i+1] < gems[i])
					{
						sorted = false;
						temp = gems[i+1];
						gems[i+1] = gems[i];
						gems[i] = temp;
					}
				}
			}
			if(sorted == true)
			{
				break; // I know this is frowned upon
			}

			

		}

		/* Prints sorted gems
		System.out.print("\n\n");
		for (int i = 0; i < gems.length; i++)
		{
			System.out.print(gems[i] + " ");
		}
		*/


		return gems;
	}

	// This method starts at the smallest value of the list and takes turns adding to each pile
	public int[][] divide(int[] gems)
	{
		int[] pile1 = new int[gems.length];
		int[] pile2 = new int[gems.length];
		int sum1 = 0, sum2 = 0;

		for (int i = 0; i < gems.length; i+=2)
		{
			pile1[i] = gems[i];

			if ((i+1) < gems.length)
			{
					pile2[i+1] = gems[i+1];
				
			}

			sum1 += gems[i];
			if ((i+1) < gems.length)
			{
				sum2 += gems[i+1];
			}
		}


		System.out.println("\n\nPile #1:");
		for (int g : pile1)
		{
			if(g!=0)
				System.out.print(g + " ");

		}
		System.out.println("\nSum = " + sum1);


		System.out.println("\nPile #2:");
		for (int g : pile2)
		{	
			if(g!=0)
				System.out.print(g + " ");

		}
	    System.out.println("\nSum = " + sum2);

		int[][] piles = {pile1, pile2};
		return piles;

	}
	
}



