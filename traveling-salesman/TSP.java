import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
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
	private MinimumSpanningTree msp;
	private ArrayList<Edge> minTree;
	private int numToVisit;

	/**
	 * Constructor
	 * 
	 * @param fileName name of file containing cords
	 * @param n number of cities to visit
	 */
	public TSP(String fileName)
	{
		try
		{
			// Opening file
			File file = new File(fileName);
			Scanner fileScan = new Scanner(file);

			// First line of file is # of cities
			int numCities = fileScan.nextInt();
			msp = new MinimumSpanningTree(numCities, (numCities*numCities)-numCities);

	
			cordinates = new int[numCities][2];
			distances = new double[numCities][numCities];
			readCords(fileScan);
			calculateDistances();

			minTree = msp.kruskal();


		}

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
				if (i != j) msp.addEdge(i, j, (int)distances[i][j]);

				System.out.println(i + " " + j + " " + distances[i][j]);
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
		String fileName = "./test-files/city1.dat";
		TSP t = new TSP(fileName);

	}
}