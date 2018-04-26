package cd.numbers;

import cd.numbers.Permutator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class PermutatorTest {


    static final ArrayList<Integer> listA = new ArrayList<>();
    static final ArrayList<Integer> listB = new ArrayList<>();
    static {
        //uniques
        listA.add(1);
        listA.add(2);
        listA.add(3);
        listA.add(4);

        //duplicates
        listB.add(2);
        listB.add(2);
        listB.add(1);
        listB.add(4);
        listB.add(5);
    }

    @Test
    public void testDuplicatesUniqueList() {
        Permutator permutator = new Permutator(listA);
        List<ArrayList<Integer>> permutationList = new ArrayList<>();
        while(permutator.hasNext()) {
            permutationList.add(permutator.next());
        }
        Set<ArrayList<Integer>> permutationSet = new HashSet<>();
        permutationSet.addAll(permutationList);
        assertEquals(permutationList.size(), permutationSet.size());

    }

    @Test
    public void testDuplicatesListWithDupes() {
        Permutator permutator = new Permutator(listB);
        List<ArrayList<Integer>> permutationList = new ArrayList<>();
        while(permutator.hasNext()) {
            permutationList.add(permutator.next());
        }
        Set<ArrayList<Integer>> permutationSet = new HashSet<>();
        permutationSet.addAll(permutationList);
        assertNotEquals(permutationList.size(), permutationSet.size());

    }

    @Test
    public void testAmountListWithDupes() {
        Permutator permutatorB = new Permutator(listB);
        assertTrue(permutatorB.hasNext());
        assertEquals(120, permutatorB.getMaxPermutations());
        for(int i = 0; i < permutatorB.getMaxPermutations(); i++) {
            permutatorB.next();
        }
        assertFalse(permutatorB.hasNext());
    }

    @Test
    public void testAmountUniqueList() {
        Permutator permutatorA = new Permutator(listA);
        assertTrue(permutatorA.hasNext());
        assertEquals(24, permutatorA.getMaxPermutations());
        for(int i = 0; i < permutatorA.getMaxPermutations(); i++) {
            permutatorA.next();
        }
        assertFalse(permutatorA.hasNext());
    }

}