/**
 * Lab2.java
 * 
 */

import java.util.Random;
import java.util.Scanner;
 
/**
 * Counting out-of-order pairs in an array.
 * 
 * This program tests and compares the times for two different algorithms for
 * computing the number of out-of-order pairs in an array.
 *
 * @author Alice McRae 
 * @version 8/20/2020 
 */
public class Lab2
{
    public static final int DOUBLE_NESTED_LOOP = 1;
    public static final int COUNT_AS_YOU_SORT = 2;
    public static final int CHANGE_VALUES = 3;
    public static final int CHANGE_SIZE_AND_VALUES = 4;
    public static final int PRINT_ARRAY = 5;
    public static final int QUIT = 6;
    public static final int NUM_PER_LINE = 5;
    public static final int MAX = 500000;

    
    /**
     * Fills an int array with random integers.
     * 
     * @param a    the array to hold the integers
     * @param ran  to get a random integer
     */
    public static void fill(int [] a, Random ran)
    {
        for (int i = 0; i < a.length; i++)
        {
            a[i] = ran.nextInt(MAX);
        }
    }
    
    /**
     * Ask user for array size and allocates.
     * 
     * @param cin   for keyboard input 
     * @return   the array
     */
    public static int [] fixArraySize(Scanner cin)
    {
        int [] a;
        int numElements;
        System.out.print("How many elements do you want: ");
        numElements = cin.nextInt();
        a = new int[numElements];
        return a;
    }
    
    
    /**
     * Prints the contents of an integer array.
     * 
     * @param a    A list of integers
     */
    public static void printArray(int [] a)
    {
        System.out.println();
        for (int i = 0; i < a.length; i++)
        {
            System.out.printf("%12d\t", a[i]);
            if (i % NUM_PER_LINE == NUM_PER_LINE - 1)
            {
                System.out.println();
            }
        }
        if (a.length % NUM_PER_LINE != 0)
        {
            System.out.println();
        }
    }
    
    
    /**
     * Asks the user what to do next.
     * 
     * @param cin   for keyboard input
     * @return      the menu item chosen by the user
     */
    
    public static int menu(Scanner cin)
    {
        int choice;

        System.out.println();
        System.out.println("***********************************************");
        System.out.println("           ARRAY FILL ALGORITHMS  ");
        System.out.println("***********************************************");
        System.out.println("       1.  Algorithm #1 ");
        System.out.println("       2.  Algorithm #2 ");
        System.out.println("       3.  Change the array values");
        System.out.println("       4.  Change size and values of the array");
        System.out.println("       5.  Print the array");
        System.out.println("       6.  Quit  ");
        System.out.println("***********************************************");
        System.out.print("\n\nEnter your choice: ");
        choice = cin.nextInt();
        return choice;
    }
   
    /**
     * Asks the user what to do next.
     * 
     * @param args   unused
     */
    public static void main(String args[])
    { 

        int choice;
        int [] a = null;
        long starttime = 0;
        long finishtime;
        Scanner cin = new Scanner(System.in);
        Random randomGenerator = new Random();
        int answer = 0;

        a = fixArraySize(cin);
        fill(a, randomGenerator);

        choice = menu(cin);
        while (choice != QUIT)
        {
            if (choice ==  DOUBLE_NESTED_LOOP 
                || choice ==  COUNT_AS_YOU_SORT)
            {
                starttime = System.currentTimeMillis();
            }
            switch (choice)
            {
                case DOUBLE_NESTED_LOOP: 
                    answer = OutOfOrder.countOutOfOrder1(a);
                    break;
                case COUNT_AS_YOU_SORT:  
                    answer = OutOfOrder.countOutOfOrder2(a);
                    break;
                case CHANGE_SIZE_AND_VALUES:
                    a = fixArraySize(cin);
                    fill(a, randomGenerator);
                    break;
                case CHANGE_VALUES:
                    fill(a, randomGenerator);
                    break;
                case PRINT_ARRAY:
                    printArray(a);
                    break;
                default:
                    System.out.println("Invalid choice: Select again\n");
            }
            if (choice ==  DOUBLE_NESTED_LOOP 
                || choice ==  COUNT_AS_YOU_SORT)
            {
                finishtime = System.currentTimeMillis();
                System.out.println("There were " + answer + " out-of-order.");
                System.out.println("The time to complete is " 
                     + (finishtime - starttime) + " time units.\n\n\n\n");
            }
            choice = menu(cin);
        }
    }

}
