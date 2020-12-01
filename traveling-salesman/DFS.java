
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;


/**
 * Program1
 *
 * @author Darrious Barger
 * @version 1
 *
 * */

public class DFS
{
    private AdjacencyLists adjList;
    private ArrayList<Integer> dfsResults;
   
   
    public ArrayList<Integer> dfsUtil(int root, boolean visited[])
    {
        visited[root] = true;
        //System.out.print(root + " ");

        dfsResults.add(root);
        Iterator<Integer> itr = adjList.neighborIterator(root);

        while (itr.hasNext())
        {
            int n = itr.next();

            if (!visited[n])
            {
                dfsUtil(n, visited);
            }
        }

        return dfsResults;
    }

    public ArrayList<Integer> dfs(AdjacencyLists adj, int root, int i)
    {
        adjList = adj;
        dfsResults = new ArrayList<Integer>(i);

        boolean visited[] = new boolean[i];
        return dfsUtil(root, visited);

    }

    
}
