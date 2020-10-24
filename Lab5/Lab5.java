import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;



/**
 * This class makes use of the StateMachine class to read a Java file and iterate through all words
 * that are not keywords. The words are stored in a TreeMap along with their line numbers. 
 * 
 * @author Darrious Barger
 * @version 1
 */
public class Lab5
{
  	private BufferedReader fileIn;
  	private StateMachine reader;
  	private HashSet<String> keywords;
  	private TreeMap<String, LinkedList<Integer>> correctWords;

  	/**
  	 * Constructor takes the name of a filel and creates a buffered reader using it.
  	 * It also initializes objects and calls helper function
  	 *
  	 * @param fileName is the name of a file (Java file)
  	 */
  	public Lab5(String fileName)
  	{
       	try
       	{
       		keywords = new HashSet<String>();

       		// We will use this TreeMap to store all words that are not keywords
       		// as well their line numbers (in linked list).
       		correctWords = new TreeMap<String, LinkedList<Integer>>();
          	fileIn = new BufferedReader(new FileReader(fileName));
          	reader = new StateMachine(fileIn);

           	insertKeywords(); // inserKeywords() and findWords() are helper functions
           	findWords();
           	fileIn.close();
        }

        catch (IOException e) 
        {

           System.out.println ("File not found. Exiting");
        } 

  	}


  	/**
  	 * Finds all words that are not a keyword and stores them in the
  	 * TreeMap along with their line number
  	 */
  	private void findWords()
  	{	
  		try
  		{   
  			// While file has more words
  			while(reader.hasToken())
  			{
  				String token = reader.getToken(); // Getting next word


  				// If the word is not a keyword
  				if (!keywords.contains(token))
  				{
  					
  					// If we have already seen this word
  					// Add just the line number
  					if (correctWords.containsKey(token))
  					{

  						correctWords.get(token).add(reader.getLineNumber());
  					}

  					// If we have not seen this number, add it to the treeMap
  					// and add its line number
  				    else
  					{ 					
  				     	correctWords.put(token, new LinkedList<Integer>());
  						correctWords.get(token).add(reader.getLineNumber());
  					}
  				}
  			}
 
  		}

  		catch (Exception e)
  		{
  			e.printStackTrace();
  		}
  	}

  	/**
  	 * This is a helper method that takes a file and inserts all words into a HashSet
  	 * The file contains Java keywords.
  	 */
	private void insertKeywords()
  	{
      	try
      	{
        	//Read input file for keywords
        	File file = new File("keywords.dat");
        	Scanner fileReader = new Scanner(file);

        	while (fileReader.hasNextLine())
      		{
      			// Add keywords to HashSet
        		keywords.add(fileReader.nextLine());
      		}

      	} 
      
      	catch(Exception e)
      	{
        	e.printStackTrace();

      	} 
  	}

  	/**
  	 * Method that prints out values in the TreeMap. This includes the words and line number
  	 */
  	public void printWords()
  	{

  		for(Map.Entry<String, LinkedList<Integer>> entry : correctWords.entrySet())
  		{
  			String key = entry.getKey();
  			LinkedList<Integer> value = entry.getValue();

 		    System.out.println(key + " => " + value);
  		}
  	}

  	/**
  	 * Main method
  	 * @param args 
  	 */
  	public static void main(String [] args)
  	{
       
       	if (args.length < 1) 
       	{
       	   System.out.println ("Usage: java Tester <filename>");
           System.exit(0);
       	}
        
       	Lab5 tester = new Lab5(args[0]);
       	tester.printWords();
	}
}

