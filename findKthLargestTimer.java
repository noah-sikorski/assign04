package assign04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class findKthLargestTimer {

    public static void main(String[] args) {
        Random rng = new Random();

        boolean first = true;

        final int nStep = 2_500;

        int timesToLoop = 5_000;

        // For each problem size n . . .
        for (int n = 2_500; n <= 25_000; n += nStep) {
            // create a list of n Integer[] of length 5
            List<Integer[]> test = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                Integer[] tmp = new Integer[3];
                for (int i = 0; i < tmp.length; i++) {
                    tmp[i] = rng.nextInt(809);
                }
                test.add(tmp);
            }

            long startTime, midpointTime, stopTime;

            // First, spin computing stuff until one second has gone by
            // This allows this thread to stabilize
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1_000_000_000) { // empty block
            }

            // Now, run the test
            startTime = System.nanoTime();
            for (int i = 0; i < timesToLoop; i++) {
                // since indexing into an arraylist is a constant time operation,
                // it shouldn't matter what we put for k here, so use 0 for convenience
                LargestNumberSolver.findKthLargest(test, 0);
            }
            midpointTime = System.nanoTime();
            // Run a loop to capture the cost of running the "timesToLoop" loop
            for (int i = 0; i < timesToLoop; i++) {
                // empty loop
            }
            stopTime = System.nanoTime();

            if (false) {    // ignore the first run since it gives wildly inaccurate results
                n -= nStep;
                first = false;
                continue;
            }

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
            System.out.println(n + "\t" + averageTime);

        }
    }

}
