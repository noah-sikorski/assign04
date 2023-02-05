package assign04;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LargestNumberSolverTest
{
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
        assertEquals(tempArray2, LargestNumberSolver.findKthLargest(tempList, 0));
        assertEquals(tempArray1, LargestNumberSolver.findKthLargest(tempList, 1));
    }


}