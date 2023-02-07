package assign04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LargestNumberSolverTest
{
    Integer[] intArrSmall;
    Integer[] intArrMedium;
    Integer[] intArrLarge;
    @BeforeEach
    void setup()
    {
        intArrSmall = new Integer[]{37, 82, 42, 1};
        intArrMedium = new Integer[]{54, 12, 346, 5, 87, 96, 5};
        intArrLarge = new Integer[]{410, 21, 93, 80, 69, 379, 20, 60, 432, 13, 72, 62, 70, 83, 9, 3, 14, 11, 62, 55, 34, 83, 80, 99, 56, 25, 79, 51, 51, 70, 79, 20, 34, 67, 40, 51, 41, 94, 89, 116, 874, 554, 137, 371, 17, 77, 97, 58, 83, 97, 26, 17, 54, 96, 33};
    }

    @Test
    public void insertionSort()
    {
        Integer[] expected = {5, 5, 12, 54, 87, 96, 346};
        LargestNumberSolver.insertionSort(intArrMedium, Integer::compare);

        assertArrayEquals(expected, intArrMedium);
    }

    @Test
    public void findLargestNumberTest()
    {
        assertEquals(new BigInteger("8242371"), LargestNumberSolver.findLargestNumber(intArrSmall));
        assertEquals(new BigInteger("9687555434612"), LargestNumberSolver.findLargestNumber(intArrMedium));
    }


    @Test
    public void testFindLargestInt()
    {
        assertEquals(8242371 ,LargestNumberSolver.findLargestInt(intArrSmall));
    }

    @Test
    public void testIntOutOfRangeException()
    {
        assertThrows(OutOfRangeException.class, () -> { LargestNumberSolver.findLargestInt(intArrMedium); });
    }

    @Test
    public void findLargestLongTest()
    {
        assertEquals(8242371L, LargestNumberSolver.findLargestLong(intArrSmall));
        assertEquals(9687555434612L, LargestNumberSolver.findLargestLong(intArrMedium));
    }

    @Test
    public void testLongOutOfRangeException()
    {
        assertThrows(OutOfRangeException.class, () -> { LargestNumberSolver.findLargestInt(intArrLarge); });
    }

    @Test
    public void sumTest()
    {
        BigInteger expected = new BigInteger("9687563676983");
        List<Integer[]> tempList = new ArrayList<Integer[]>();
        tempList.add(intArrSmall);
        tempList.add(intArrMedium);
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
        var actual = LargestNumberSolver.readFile("src/assign04/integers.txt");
        assertArrayEquals(intArrLarge, actual.get(0));
    }


}