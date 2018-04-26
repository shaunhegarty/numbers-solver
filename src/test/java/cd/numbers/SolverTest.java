package cd.numbers;

import cd.numbers.Solver;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {

    @Test
    public void testSolver () {

        int target = 952;
        int[] numbers = {100, 25, 50, 75, 6, 3};
        Solver solver = new Solver(target, numbers);
        long beforeTime = System.currentTimeMillis();
        solver.solve();

        long afterTime = System.currentTimeMillis();
        System.out.println("The solution took: " + (afterTime - beforeTime) + "ms");

    }

}