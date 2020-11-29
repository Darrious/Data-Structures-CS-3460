
/**
 * DisjointSets
 * 
 * @author Darrious Barger
 * @version 1
 *
 */
public class DisjointSets
{
	private int dSet[]; 

	/**
	 * Constructor 
	 * Inializes disjoint with all -1 
	 *
	 * @param sets is the size of set
	 */
	public DisjointSets(int sets)
	{
		dSet = new int[sets];

		for (int i = 0; i < sets; i++)
		{
			dSet[i] = -1;
		}
	}

	/**
	 * union
	 * Combines two trees 
	 * 
	 * @param i vertice 1
	 * @param j vertice 2
	 */
	public void union(int i, int j)
	{		
		dSet[j] = i;
	}

	/**
	 * find 
	 * Finds the parent of a vertice
	 *
	 * @param i vertice 
	 */
	public int find(int i)
	{
		if (dSet[i] < 0)
			return i;
		
		return find(dSet[i]);
	}

	/**
	 * print
	 * Prints contents of DisjointSet array.
	 */
	public void print()
	{
		System.out.println("\n\nDisjointSet Array:\n---------------");
		for (int i = 0; i < dSet.length; i++)
		{
			System.out.println(i + ": " + dSet[i]);
		}
	}
}