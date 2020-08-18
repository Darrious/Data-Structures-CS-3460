// Darrious Barger

import java.util.Random;
import java.util.Scanner;

public class Gems
{
	public int[] generateVals(int total)
	{
		Random r = new Random();
		int gemsVal[] = new int[total];


		for(int i = 0; i < gemsVal.length; i++)
		{
			gemsVal[i] = r.nextInt(50000);
		}

		for(int i = 0; i < gemsVal.length; i++)
		{
			System.out.print(gemsVal[i] + " ");
		}

		return gemsVal;

	}
	

	
	
}



