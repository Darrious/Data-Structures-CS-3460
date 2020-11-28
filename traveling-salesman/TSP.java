import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * TSP
 *
 * @author Darrious Barger
 * @version Darrious Barger
 */
public class TSP
{
	private int[][] cordinates;
	private double[][] distances;
	private boolean[] visitedTour1;
	private int numToVisit;

	/**
	 * Constructor
	 * 
	 * @param fileName name of file containing cords
	 * @param n number of cities to visit
	 */
	public TSP(String fileName, int n)
	{
		try
		{
			// Opening file
			File file = new File(fileName);
			Scanner fileScan = new Scanner(file);

			// First line of file is # of cities
			int numCities = fileScan.nextInt();
			int i = 0;

			numToVisit = n;
			cordinates = new int[numCities][2];
			distances = new double[numCities][numCities];
			visitedTour1 = new boolean[numCities];
			readCords(fileScan);
			calculateDistances();
			findTour();

		}

        // Catch exceptios=ns
		catch(FileNotFoundException e)
		{
			System.out.println("ERROR: Invalid file name/path");
			System.exit(0);
		}

		catch(Exception e)
		{
			System.out.println("ERROR: Cannot open/read file");
			e.printStackTrace();
			System.exit(0);
		}
		
	}

	public void findTour()
	{
		// Find cities with shortest distance
		int shorti = 0;
		int shortj = 0;
		double shortest = distances[0][1];

		// These loops find the cities with the shortest distance between them
		System.out.println("\n\nDistances\n----------");
		for (int i = 0; i < distances.length; i++)
		{
			for (int j = 0; j < distances[0].length; j++)
			{
				System.out.println(i +"-->"+ j + " | " + distances[i][j]);
				if (distances[i][j] < shortest && i!=j)
				{
					shortest = distances[i][j];
					shorti = i;
					shortj = j;
				}
			}
		}

		//System.out.println("SHORTEST: " + shortest);

		int i=0;
		int currentCity = shortj;
		int firstCity = shorti;
		visitedTour1[shorti] = true;
		double tourLength = distances[shorti][shortj];
		


		// We start on the cities with the shortest distance. Then use an algorithm to find the shortest to the next city
		while (i < numToVisit-1)
		{	

			System.out.println( "\n"+(i+1) + ". CITY " + shorti + "-->" + shortj + " | DISTANCE: "+distances[shorti][shortj] +"\n");
			shortest = distances[currentCity][0];
			visitedTour1[shortj] = true;
			tourLength += distances[shorti][shortj];
			currentCity = shortj;

			for (int j = 0; j < distances.length; j++)
			{
				//System.out.println("OPTIONS & j: " + distances[currentCity][j] + " " + j);
				if (distances[currentCity][j] < shortest && visitedTour1[j] != true && currentCity != j)
				{

					shortest = distances[currentCity][j];
					shorti = currentCity;
					shortj = j;
					
					//System.out.println("SHORTEST: " + shortest);
				} 
			}

			
			i++;
		}

		System.out.println("\n" + numToVisit + ". CITY " + currentCity + "-->" + firstCity + " | DISTANCE: "+distances[shorti][shortj]+ "\n");
		System.out.println("TOUR TOTAL: " + tourLength );

	}


	/**
	 * readCords
	 *
	 * Reads cordinates from a file into a 2d array
	 *
	 * @param fileScan scanner for the file containing the cordinates
	 */
	public void readCords(Scanner fileScan)
	{
		int i = 0;
		// Reading in cordinates
		while (fileScan.hasNext())
		{
			if (fileScan.hasNextInt())
			{
				// If there is an int, there will always be two
				cordinates[i][0] = fileScan.nextInt();
				cordinates[i][1] = fileScan.nextInt();

				System.out.println(i+". " + cordinates[i][0] + " " + cordinates[i][1]);
				i++;
			}

			else
			{
				fileScan.nextLine();
			}				
		}

	}
	
	/**
	 * calculateDistances
	 *
	 * Calculates the distances between two cities and stores the result
	 * in a 2d array
	 */
	public void calculateDistances()
	{
		for (int i = 0; i < cordinates.length; i++)
		{
			double xi = cordinates[i][0];
			double yi = cordinates[i][1];

			for (int j = 0; j < cordinates.length; j++)
			{
				double xj = cordinates[j][0];
				double yj = cordinates[j][1];

				distances[i][j] = Math.sqrt((xi-xj)*(xi-xj) + (yi-yj)*(yi-yj));
				//System.out.println(distances[i][j]);
			}	
		}

	}	


	/**
	 * Main
	 * 
	 * @param args coommand line arguments
	 */
	public static void main(String[] args)
	{	
		String fileName = "./test-files/city3.dat";
		TSP t = new TSP(fileName, 26);

	}
}