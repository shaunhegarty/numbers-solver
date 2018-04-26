package cd.numbers;

import java.util.ArrayList;

public class Permutator<T> {

    private ArrayList<T> list;
    private int[] positions;
    private int index = 0;

    private long maxPermutations;
    private long permutationStep = 1;

    public Permutator(ArrayList<T> list){
        this.list = (ArrayList<T>) list.clone();
        this.positions = new int[list.size()];
        this.maxPermutations = factorial(list.size());
    }

    public ArrayList<T> next() {
        if (!hasNext()) {
            throw new PermutationException("No more Permutations Remaining");
        }
        if(permutationStep == 1){
            permutationStep++;
            return clonedArray();
        }


        while(index < list.size()) {
            if(positions[index] < index) {
                if(index % 2 == 0) {
                    swap(list, 0, index);
                } else {
                    swap(list, positions[index], index);
                }
                positions[index]++;
                index = 0;
                permutationStep++;
                return clonedArray();
            } else {
                positions[index] = 0;
                index++;
            }
        }
        permutationStep++;

        return clonedArray();
    }

    private ArrayList<T> clonedArray(){
        return (ArrayList<T>) list.clone();
    }

    public boolean hasNext() {
        return permutationStep <= maxPermutations;
    }

    private static <T> void swap(ArrayList<T> list, int first, int second) {
        T temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    private long factorial(int number){
        return factorial((long) number);
    }

    private long factorial(long number) {
        long result = number;
        for(long i = number - 1; i > 0; i--) {
            result *=  i;
        }
        return result;
    }


    public long getMaxPermutations() {
        return maxPermutations;
    }

    public long getPermutationStep() {
        return permutationStep;
    }


}

class PermutationException extends RuntimeException {
    public PermutationException(String message) {
        super(message);
    }
}
