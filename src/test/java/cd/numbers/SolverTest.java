package cd.numbers;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SolverTest {

    @Test
    public void testSolver () {

        int target = 403;
        int[] numbers = {29, 4, 3, 79};
//        Solver solver = new Solver(target, numbers);
//        long beforeTime = System.currentTimeMillis();
//        Map<Integer, List<Solution>> solved = solver.solve();
//        long afterTime = System.currentTimeMillis();
//        for(int i = 0; i < 10; i++) {
//            if(solved.containsKey(i)){
//                for(Solution solution : solved.get(i)) {
//                    System.out.println(solution);
//                }
//                break;
//            }
//        }
//        System.out.println("The solution took: " + (afterTime - beforeTime) + "ms");

        Numbers solver = new Numbers(target, numbers);
        solver.solve();
        System.out.println(solver.getSolution() + ": " + solver.getSolnString());

    }

    @Test
    public void testSolver1() {
        int target = 952;
        int[] numbers = {100, 25, 50, 75, 6, 3};
        Solver solver = new Solver(target, numbers);
        Map<Integer, List<Solution>> solved = solver.solve();

        Solution solution1 = new Solution();
        solution1.operate(Operator.ADD, 100);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.ADD, 3);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.MULTIPLY, 6);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.MULTIPLY, 75);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.DIVIDE, 50);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.ADD, 25);
        assertTrue(testContainsSolution(solved, solution1, target));

        solution1.operate(Operator.SUBTRACT, 50);
        assertFalse(testContainsSolution(solved, solution1, target));

        Solution solution2 = new Solution();
        solution2.operate(Operator.ADD, 100);
        solution2.operate(Operator.ADD, 6);
        solution2.operate(Operator.MULTIPLY, 3);
        solution2.operate(Operator.MULTIPLY, 75);
        solution2.operate(Operator.SUBTRACT, 50);
        solution2.operate(Operator.DIVIDE, 25);
        assertTrue(testContainsSolution(solved, solution2, target));

        solution2.operate(Operator.SUBTRACT, 50);
        assertFalse(testContainsSolution(solved, solution2, target));
    }

    @Test
    public void testSolver2() {
        int target = 877;
        int[] numbers = {9, 75, 6, 10, 6, 3};
        Solver solver = new Solver(target, numbers);
        Map<Integer, List<Solution>> solved = solver.solve();

        Solution solution1 = new Solution();
        solution1.operate(Operator.ADD, 6);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.MULTIPLY, 75);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.SUBTRACT, 10);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.MULTIPLY, 6);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.SUBTRACT, 9);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.DIVIDE, 3);
        assertTrue(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.SUBTRACT, 37);
        assertFalse(testContainsSolution(solved, solution1, target));

    }

    @Test
    public void testSolver3() {
        int target = 403;
        int[] numbers = {25, 4, 3, 75, 7};
        Solver solver = new Solver(target, numbers);
        Map<Integer, List<Solution>> solved = solver.solve();

        Solution solution1 = new Solution();
        solution1.operate(Operator.ADD, 75);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.ADD, 7);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.MULTIPLY, 4);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.ADD, 25);
        assertFalse(testContainsSolution(solved, solution1, target));
        solution1.operate(Operator.SUBTRACT, 3);
        assertTrue(testContainsSolution(solved, solution1, target));

        //FIXME This Test will fail
        // because the solution object cannot currently represent A solution of the form: (75 + 7) * 4 + 25 * 3 which
        // works out as 403 but cannot be returned by the program

        // Possible resolution: Allow Operation objects to take Solution and have them be operable.
        // Calculate values will work with the overall value of the solution
        // toString should use the string representation of the inner solution when printing the overall solution
        // Need to work on how to have algorithm cover all of these solutions

    }

    public boolean testContainsSolution(Map<Integer, List<Solution>> completeSolutions, Solution solution, int target) {
        int difference = Math.abs(solution.getValue() - target);
        if(completeSolutions.containsKey(difference)) {
            List<Solution> solutionsForValue = completeSolutions.get(difference);
            return solutionsForValue.contains(solution);
        } else {
            return false;
        }
    }

}