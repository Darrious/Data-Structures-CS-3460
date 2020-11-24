// Darrious Barger

public class DisjointSets
{
	private int dSet[]; 

	public DisjointSets(int sets)
	{
		dSet = new int[sets];

		for (int i = 0; i < sets; i++)
		{
			dSet[i] = -1;
		}
	}

	public void union(int i, int j)
	{
		dSet[j] = i;
	}

	public int find(int i)
	{
		while (i > 0)
		{
			i = dSet[i];
		}

		return i;
	}
}