package assign04;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class that provide methods for finding and working with the largest number
 * that can be formed from an array of Integers, if every number in the array
 * were concatenated together to form one larger number.
 *
 * @author Jayden Ferrin and Noah Sikorski
 * @version February 4, 2023
 */

public class LargestNumberSolver
{
    /**
     * This generic method sorts the input array using an insertion sort and the input Comparator object.
     * @param arr Array to be sorted
     * @param cmp Comparator to be used
     * @param <T> Type of array
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp)
    {
        for (int i = 1; i < arr.length; i++)
        {
            T tmp = arr[i];
            int j;
            for (j = i; j > 0; j--)
            {
                int compare = cmp.compare(tmp, arr[j - 1]);
                if (compare >= 0)
                    break;
                // copy items up in the array until we find a spot for tmp
                arr[j] = arr[j - 1];
//                T tmp = arr[j];
//                arr[j] = arr[j - 1];
//                arr[j - 1] = tmp;
            }
            // swap tmp into place
            arr[j] = tmp;
        }
    }

    /**
     * This method returns the largest number that can be formed by arranging the integers
     * of the given array, in any order.   If the array is empty, the largest number that
     * can be formed is 0.  This method must not alter the given array and must call your
     * insertionSort method with a Comparator or lambda expression that you design.
     * @param arr array to find the largest number, arr is not altered by the method
     * @return a BigInteger representing the largest
     */
    public static BigInteger findLargestNumber(Integer[] arr)
    {
        Integer[] tmp = arr.clone();
        insertionSort(tmp, (i1, i2) -> {
            String firstCase = i1.toString() + i2.toString(),
                    secondCase = i2 + i1.toString();

            return secondCase.compareTo(firstCase);
        });

        StringBuilder sb = new StringBuilder();
        for (var item :
                tmp) {
            sb.append(item);
        }

        return new BigInteger(sb.toString());
    }

    /**
     * This method returns the largest int value that can be formed by arranging the integers
     * of the given array, in any order.  An OutOfRangeException is
     * thrown if the largest number that can be formed is too large for the int data type.  Logic
     * for solving the problem of determining the largest number should not appear again in this
     * method — call an existing public method or a helper method.  This method must not alter
     * the given array.
     * @param arr array to find the largest number, arr is not altered by the method
     * @return int representation of the largest number, if it can fit into an int type
     * @throws OutOfRangeException
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException
    {
        BigInteger largestInteger= findLargestNumber(arr);
        if(largestInteger.bitLength()>32)
            throw new OutOfRangeException("Int");
        return largestInteger.intValue();
    }

    /**
     * This method behaves the same as the previous method, but for data type long instead of data type int.
     * @param arr array to find the largest number, arr is not altered by the method
     * @return long representation of the largest number, if it can fit into a long type
     * @throws OutOfRangeException
     */
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException
    {
        BigInteger largestInteger= findLargestNumber(arr);
        if(largestInteger.bitLength()>64)
            throw new OutOfRangeException("long");
        return largestInteger.longValue();
    }

    /**
     * This method sums the largest numbers that can be formed by each array in the given list.
     * This method must not alter the given list.
     * @param list List of Integers to find the largest number of each of them
     * @return The sum of the largest numbers that can be formed with the arrays in the list
     */
    public static BigInteger sum(List<Integer[]> list)
    {
        BigInteger sum = new BigInteger("0");
        BigInteger[] largestNumbers = findLargestNumber(list);
        for(BigInteger i: largestNumbers)
            sum = sum.add(i);
        return sum;
    }

    /**
     * Helper method to find the largest number of multiple arrays
     * @param list List of Integers to find the largest number of each of them
     * @return Array of BigIntegers representing the largest numbers of the arrays
     * in the input, in the order that they were given
     */
    private static BigInteger[] findLargestNumber(List<Integer[]> list)
    {
        BigInteger[] largestNumber = new BigInteger[list.size()];
        for(int i=0; i<list.size();i++)
        {
            largestNumber[i]=findLargestNumber(list.get(i));
        }
        return largestNumber;
    }

    /**
     * This method determines the kth largest number that can be formed by each array in the given list.
     * E.g., if k=0 returns the largest overall, if k=list.size()-1 returns the smallest overall.
     * This method returns the original array that represents the kth largest number, not the kth largest
     * number itself.  An IllegalArgumentException Links to an external site.is thrown if k is not a valid
     * position in the list.  This method must not alter the given list and must call your insertionSort
     * method with a Comparator or lambda expression that you design.
     * @param list List of Integers to find the largest number of each of them
     * @param k How many levels under the largest largest number of list.
     * @return The kth largest number that can be formed from one of the Integer arrays from list
     * @throws IllegalArgumentException if k refers to an index outside of list
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException
    {
        if (k < 0 || k >= list.size())
            throw new IllegalArgumentException("k is out of bounds");

        LargestNumber[] ln = new LargestNumber[list.size()];
        for(int i = 0; i<list.size(); i++)
        {
            ln[i] = new LargestNumber(findLargestNumber(list.get(i)),list.get(i));
        }
        insertionSort(ln, (l1, l2) -> -l1.largestNumber.compareTo(l2.largestNumber));
        return ln[k].numbers;
    }

    /**
     * Convenience class to be able to provide a custom sorting to Integer arrays
     * based on their largest number as a BigInteger
     */
    private static class LargestNumber
    {
        BigInteger largestNumber;
        Integer[] numbers;
        public LargestNumber(BigInteger largestNumber, Integer[] number)
        {
            this.largestNumber = largestNumber;
            this.numbers = number;
        }
    }

    /**
     * This method generates list of integer arrays from an input file, such that each line corresponds to
     * one array of integers separated by blank spaces, and returns an empty list if the file does not exist.
     * @param filename Path to a file to be read
     * @return List of Integer arrays ready to be plugged into another method.
     */
    public static List<Integer[]> readFile(String filename)
    {
        List<Integer[]> numbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while (null != line) {
                // parse it
                String[] strs = line.split("\\s");
                Integer[] ints = new Integer[strs.length];
                for (int i = 0; i < ints.length; i++) {
                    ints[i] = Integer.parseInt(strs[i]);
                }
                numbers.add(ints);
                line = br.readLine();
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return numbers;
    }
}
