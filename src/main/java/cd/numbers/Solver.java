package cd.numbers;

import java.util.*;

public class Solver {
    private final static int MAX_TARGET_DISTANCE = 10;

    private int target;
    private int[] numbers;

    private Map<Integer, List<Solution>> solutionMap = new HashMap<>();

    public Solver(int target, int[] numbers) {
        this.target = target;
        this.numbers = numbers;
    }

    public Map<Integer, List<Solution>> solve() {
        ArrayList<Integer> factors = new ArrayList<>(this.numbers.length);
        for(int number : this.numbers) {
            factors.add(number);
        }
        // To get through all possible solutions, first get each permutation of the usable numbers then apply each
        // possible operation in order
        Permutator permutator = new Permutator(factors);
        while(permutator.hasNext()){
            ArrayList<Integer> next = permutator.next();
            trySolutions(next);
        }
        return solutionMap;
    }


    private void trySolutions(ArrayList<Integer> factors) {
        //Start a new solution.
        Solution solution = new Solution();
        //First operation will have zero on the left, so add first number
        solution.operate(Operator.ADD, factors.get(0));
        //Begin Recursion
        trySolutions(factors, solution.clone());
    }

    //Brute Force every operation
    private void trySolutions(ArrayList<Integer> factors, Solution solution) {
        //Stop when all numbers have been used once
        if(solution.steps() >= factors.size()) {
            return;
        }
        for(Operator operator : Operator.values()) {
            //Create a copy of the solution so far
            Solution clone = solution.clone();
            //Apply an operation
            clone.operate(operator, factors.get(solution.steps()));
            //If the solution is sufficiently close to the target add it to the solution map
            checkBestSolution(clone);
            //Recurse through and apply the next operation
            trySolutions(factors, clone);
        }
    }

    // Add any solution that is close enough to the target
    private void checkBestSolution(Solution solution) {
        int difference = Math.abs(solution.getValue() - this.target);
        if(difference < MAX_TARGET_DISTANCE) {
            if (!solutionMap.containsKey(difference)) {
                solutionMap.put(difference, new ArrayList<>());
            }
            solutionMap.get(difference).add(solution);
        }
    }

}


