// i collaborated with Lina Ulger. for this coding homework
package org.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.example.NumberUtils.add;
import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {
    /**
     * Step 1: understand the requirement, input type and output type
     * Requirement: Add two list of integer, index by index, and returns another list
     * <p>
     * Step 2 (raw):  Perform partition and boundary analysis on input and output
     * Each input: left | right
     * Combination of input:
     * Output:
     * Step 3: Derive potential test cases
     */
    @Test
    @Tag("SpecificationBased")
    public void testBasic() { //checking for acceptable input lists
        LinkedList<Integer> Left = new LinkedList<>(); //creating the first LL that represents an integer
        LinkedList<Integer> Right = new LinkedList<>(); // creating the second LL that represets an integer
        Left.add(1);
        Left.add(3);
        Left.add(5);
        Left.add(2);
        // the lines LtOne.add(), are for making a list with some integers (LL #1)
        Right.add(4);
        Right.add(6);
        Right.add(3);
        Right.add(2);
        // the lines LtTwo.add(), are for making a list with some integers (LL #2)
        LinkedList<Integer> sumLst = (LinkedList<Integer>) add(Left, Right); //calling the add method to add the integers summing them together
        // and creating a LL as a new list.
        assertEquals(sumLst.get(0), 5); // asserting the first index integer sum
        assertEquals(sumLst.get(1), 9); // asserting the second index integer sum
        assertEquals(sumLst.get(2), 8); // asserting the third index integer sum
        assertEquals(sumLst.get(3), 4); // asserting the fourth index integer sum
    }
    @Test
    @Tag("CoverageBased")
    public void testListWithZeros() {
        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();
        // added Zeros to the leftt
        left.add(0);
        left.add(0);
        left.add(0);
        // adds numbers to right side
        right.add(5);
        right.add(6);
        right.add(7);

        LinkedList<Integer> result = (LinkedList<Integer>) NumberUtils.add(left, right);

        assertEquals(5, result.get(0));
        assertEquals(6, result.get(1));
        assertEquals(7, result.get(2));
    }


    @Test
    @Tag("SpecificationBased")
    public void testErrorNegative() { // test adding negative integer throws an excpetion
        LinkedList<Integer> left = new LinkedList<>(); //creating first LL
        LinkedList<Integer> Right = new LinkedList<>(); // creating second LL
        left.add(-5); // adding -5 to our first list.
        Right.add(1); // adding 1 to our second list
        assertThrows(IllegalArgumentException.class, () -> {
            add(left, Right);
        }); //asserting the add method to
        //throw an illegal arguement exception.

    }
    @Test
    @Tag("CoverageBased")
    public void testNullElementInList() {
        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();

        left.add(null); // checks what happens if null element is in list
        right.add(3);

        assertThrows(NullPointerException.class, () -> {
            NumberUtils.add(left, right);
        });
    }


    @Test
    @Tag("SpecificationBased")
    public void testdoubleDigits() { // testing whether integers that are greater than 9 are throwing excpetions
        LinkedList<Integer> Left = new LinkedList<>(); //creating LL #1
        LinkedList<Integer> Right = new LinkedList<>(); //creating ll #2
        Left.add(10);// a digit that is bigger than 10
        Right.add(3); // a digit that is not a problem because its between 0-9.
        assertThrows(IllegalArgumentException.class, () -> {
            add(Left, Right);
        });// asserts the add method
        //to throw an illegal arguemenent exception
    }

    @Test
    @Tag("SpecificationBased")
    public void testEmptyDigits() { // test adding an empty list to a non-empty list.
        LinkedList<Integer> Left = new LinkedList<>(); //creating LL #1
        LinkedList<Integer> Right = new LinkedList<>(); //creating LL #2
        Right.add(1); // adding one digit to LL #2
        LinkedList<Integer> sumLst = (LinkedList<Integer>) add(Left, Right); //calling the add method
        //with one empty and non-empty list.
        assertEquals(1, sumLst.getFirst()); // assert the sum that have single digit from the non-empty list.

    }

    @Test
    @Tag("SpecificationBased")
    public void testCarry() { // tests handling carry during summation
        LinkedList<Integer> Left = new LinkedList<>(); // creating LL #1
        LinkedList<Integer> Right = new LinkedList<>(); //creating LL #2
        Left.add(9); // adding integer 9 to the first LL
        Right.add(9); // adding integer 9 to the second LL
        LinkedList<Integer> sumLst = (LinkedList<Integer>) add(Left, Right); //calling a method with inputs
        //that will make a carry
        assertEquals(1, sumLst.getFirst()); //asserts the most significant integer of the final result in the carry
        assertEquals(8, sumLst.get(1)); // asserts that the next digit of the result is 8

    }

    @Test
    @Tag("SpecificationBased")
    public void testNull() { // test the reaction
        LinkedList<Integer> initial = new LinkedList<>();
        LinkedList<Integer> result = (LinkedList<Integer>) add(initial, null);
        assertNull(result);
    }
    @Test
    @Tag("MutationEdgeCases")
    public void testEdgeCasesForMutation() {
        List<Integer> left = new ArrayList<>(List.of(9, 9));
        List<Integer> right = new ArrayList<>(List.of(1));
        List<Integer> actual = NumberUtils.add(left, right);
        assertEquals(List.of(1, 0, 0), actual);

        left = new ArrayList<>(List.of(1));
        right = new ArrayList<>(List.of(8));
        // 1 + 8 = 9 => final => [9], no leftover carry
        actual = NumberUtils.add(left, right);
        assertEquals(List.of(9), actual);

        left = new ArrayList<>(List.of(5, 8));
        right = new ArrayList<>(List.of(7, 8));
        actual = NumberUtils.add(left, right);
        assertEquals(List.of(1, 3, 6), actual);


        left = new ArrayList<>(List.of(0, 0, 0));
        right= new ArrayList<>(List.of(0, 0));

        actual= NumberUtils.add(left, right);
        assertEquals(List.of(0), actual);
    }




}