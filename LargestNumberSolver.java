package assign04;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

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
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = i; j > 0; j--)
            {
                int compare = cmp.compare(arr[j], arr[j - 1]);
                if (compare >= 0)
                    break;
                // start swapping things
                T tmp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = tmp;
            }
        }
    }

    /**
     * This method returns the largest number that can be formed by arranging the integers
     * of the given array, in any order.   If the array is empty, the largest number that
     * can be formed is 0.  This method must not alter the given array and must call your
     * insertionSort method with a Comparator or lambda expression that you design.
     * @param arr
     * @return
     */
    public static BigInteger findLargestNumber(Integer[] arr)
    {
        Integer[] tmp = arr.clone();
        insertionSort(tmp, (i1, i2) -> {
            String firstCase = i1.toString() + i2.toString(),
                    secondCase = i2.toString() + i1.toString();

            return secondCase.compareTo(firstCase);

//            int d1 = numDigits(i1), d2 = numDigits(i2);
//            int minDigits = (d1 < d2) ? d1 : d2;
//            int i;
//            for (i = 0; i < minDigits; i++) {
//                int compare = digit(i2, i) - digit(i1, i);
//                if (0 != compare)
//                    return compare;
//            }
//
//            if (d1 == d2) return 0;
//
////            int longerNum = (d1 > d2) ? i1 : i2;
////            int compare = digit(longerNum, i) - digit(longerNum, 0);
//            if (d1 > d2) {
//                // if i1's next digit is <= its first:
//                // i2 is bigger
//                // otherwise
//                // i1 is bigger
//                int compare = digit(i1, i) - digit(i1, 0);
//                if (0 == compare)
//                    return 1;
//                return -compare;
//            } else {
//                // if i2's
//                int compare = digit(i1, i) - digit(i1, 0);
//                if (0 == compare)
//                    return 1;
//                return -compare;
//            }


        });

        StringBuilder sb = new StringBuilder();
        for (var item :
                tmp) {
            sb.append(item);
        }

        return new BigInteger(sb.toString());
    }

    public static int digit(final int of, int i)
    {
        if (0 == of) return 0;

        int j = numDigits(of), tmp = of;

        int index = j - 1 - i;
        if (index <= 0) return of % 10;
        for (int k = 0; k < index; k++) {
            tmp /= 10;
        }
        return tmp % 10;
    }

    private static int numDigits(int in) {
        int digits = 0;
        while (in > 0) {
            in /= 10;
            digits++;
        }
        return digits;
    }

    /**
     * This method returns the largest int value that can be formed by arranging the integers
     * of the given array, in any order.  An OutOfRangeException is
     * thrown if the largest number that can be formed is too large for the int data type.  Logic
     * for solving the problem of determining the largest number should not appear again in this
     * method — call an existing public method or a helper method.  This method must not alter
     * the given array.
     * @param arr
     * @return
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
     * @param arr
     * @return
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
     * @param list
     * @return
     */
    public static BigInteger sum(List<Integer[]> list)
    {
        BigInteger sum = new BigInteger("0");
        BigInteger[] largestNumbers = findLargestNumber(list);
        for(BigInteger i: largestNumbers)
            sum=sum.add(i);
        return sum;
    }

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
     * @param list
     * @param k
     * @return
     * @throws IllegalArgumentException
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException
    {
        LargestNumber[] ln = new LargestNumber[list.size()];
        for(int i = 0; i<list.size(); i++)
        {
            ln[i] = new LargestNumber(findLargestNumber(list.get(i)),list.get(i));
        }
        insertionSort(ln, (l1, l2) -> -l1.largestNumber.compareTo(l2.largestNumber));
        return ln[k].numbers;
    }

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
     * @param filename
     * @return
     */
    public static List<Integer[]> readFile(String filename)
    {
        return null;
    }
}
