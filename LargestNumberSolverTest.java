package assign04;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LargestNumberSolverTest
{
    @Test
    public void insertionSort()
    {
        Integer[] actual = {54, 12, 346, 5, 87, 96, 5};
        Integer[] expected = {5, 5, 12, 54, 87, 96, 346};
        LargestNumberSolver.insertionSort(actual, Integer::compare);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void digit() {
        assertEquals(1, LargestNumberSolver.digit(130, 0));
        assertEquals(3, LargestNumberSolver.digit(130, 1));
        assertEquals(0, LargestNumberSolver.digit(130, 2));
        assertEquals(1, LargestNumberSolver.digit(131, 87687));
        assertEquals(8, LargestNumberSolver.digit(87691, 0));
        assertEquals(9, LargestNumberSolver.digit(87691, 3));
    }

    @Test
    public void findLargestNumberTest()
    {
        Integer[] tempArray = {1, 45, 9};
        BigInteger expected = new BigInteger("9451");
        assertEquals(expected, LargestNumberSolver.findLargestNumber(tempArray));
    }

    @Test
    public void findLargestIntTest()
    {
        Integer[] tempArray = {999, 639, 1, 7, 58, 9};
        assertThrows(OutOfRangeException.class, () -> { LargestNumberSolver.findLargestInt(tempArray); });
    }

    @Test
    public void findLargestLongTest()
    {
        Integer[] tempArray = {999, 639, 1, 7, 58, 9};
        assertEquals(99997639581L, LargestNumberSolver.findLargestLong(tempArray));
    }

    @Test
    public void sumTest()
    {
        List<Integer[]> tempList = new ArrayList<Integer[]>();
        Integer[] tempArray = {88,51};
        BigInteger expected = new BigInteger("106593");
        tempList.add(tempArray);
        tempArray = new Integer[]{7, 42, 97};
        tempList.add(tempArray);
        assertEquals(expected, LargestNumberSolver.sum(tempList));
    }

    @Test
    public void findKthLargestTest()
    {
        List<Integer[]> tempList = new ArrayList<Integer[]>();
        Integer[] tempArray1 = {88,51};
        Integer[] tempArray2 = {7, 42, 97};
        tempList.add(tempArray1);
        tempList.add(tempArray2);
        assertArrayEquals(tempArray2, LargestNumberSolver.findKthLargest(tempList, 0));
        assertArrayEquals(tempArray1, LargestNumberSolver.findKthLargest(tempList, 1));
    }


}