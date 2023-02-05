package assign04;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
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

    @Test
    public void readFileNotExist() {
        assertEquals(new ArrayList<Integer[]>(), LargestNumberSolver.readFile("skljdkljdsfkjlds"));
    }

    @Test
    public void readFile() {
//        File fil = new File("src/assign04/idk.txt");
//        try {
//            fil.createNewFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        Integer[] expected = {410, 21, 93, 80, 69, 379, 20, 60, 432, 13, 72, 62, 70, 83, 9, 3, 14, 11, 62, 55, 34, 83, 80, 99, 56, 25, 79, 51, 51, 70, 79, 20, 34, 67, 40, 51, 41, 94, 89, 116, 874, 554, 137, 371, 17, 77, 97, 58, 83, 97, 26, 17, 54, 96, 33};
        var actual = LargestNumberSolver.readFile("src/assign04/integers.txt");
        assertArrayEquals(expected, actual.get(0));
    }


}