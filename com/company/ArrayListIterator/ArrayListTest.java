package com.company.ArrayListIterator;

import com.company.ArrayListIterator.ArrayList;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ArrayListTest {
    private ArrayList<Integer> testInstance;
    private ListIterator<Integer> listIterator;

    @Before
    public void setUp() throws Exception {
        testInstance = new ArrayList<>();
        listIterator = testInstance.listIterator();
    }


    @Test
    public void testSizeWhenSizeIs0() throws Exception {

        assertEquals(0, testInstance.size());
    }

    @Test
    public void testIsEmptyWhenEmpty() throws Exception {

        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testToArrayWhenInputArrayHaveSizeOne() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[1];

        final Integer[] result = testInstance.toArray(input);
        assertNotEquals(input, result);
        assertEquals((Integer)1, result[0]);
        assertEquals((Integer)2, result[1]);
        assertEquals((Integer)3, result[2]);
        assertEquals(3, result.length);
    }

    @Test
    public void testToArrayWhenInputArrayHaveCorrectSize() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[3];

        final Integer[] result = testInstance.toArray(input);
        assertArrayEquals(input, result);
    }

    @Test
    public void testContains() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        assertTrue(testInstance.contains(3));
        assertFalse(testInstance.contains(0));
    }

    @Test
    public void testAdd() throws Exception {

        testInstance.add(1);
        testInstance.add(1);

        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveObjectFirstElement() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        Object a = 1;
        testInstance.remove(a);

        assertEquals(1, testInstance.size());
        assertEquals("Method remove(final Object o) is wrong", (Integer)2, (Integer)testInstance.get(0));
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveObjectLastElement() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        Object a = 2;
        testInstance.remove(a);

        assertEquals(1, testInstance.size());
        assertEquals("Method remove(final Object o) is wrong", (Integer)1, (Integer)testInstance.get(0));
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContainsAll() throws Exception {

        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(1);

        assertTrue(testInstance.containsAll(testInstance2));
    }

    @Test
    public void testAddAll() throws Exception {

        testInstance.add(1);
        testInstance.add(2);

        testInstance.add(3);
        testInstance.add(4);

        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
    }

    @Test
    public void testRemoveAll() throws Exception {

        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.removeAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(1));
    }

    @Test
    public void testRetainAll() throws Exception {

        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.retainAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(2));
    }

    @Test
    public void testClear() throws Exception {

        testInstance.add(1);
        testInstance.add(1);

        testInstance.clear();

        assertTrue(testInstance.isEmpty());
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testRemoveIndexFirst() throws Exception {
        testInstance.add(1);
        testInstance.add(2);

        testInstance.remove(0);

        assertEquals(1, testInstance.size());
        assertEquals("Method remove(final int index) is wrong", (Integer)2, (Integer)testInstance.get(0));
        assertFalse(testInstance.isEmpty());

    }

    @Test
    public void testRemoveIndexLast() throws Exception {
        testInstance.add(1);
        testInstance.add(2);

        testInstance.remove(1);

        assertEquals(1, testInstance.size());
        assertEquals("Method remove(final int index) is wrong", (Integer)1, (Integer)testInstance.get(0));
        assertFalse(testInstance.isEmpty());
    }

    //ElementsIterator:
    @Test
    public void testRemoveBeforeNext() throws Exception {

        testInstance.add(2);
        try {
            listIterator.remove();
            fail("remove do not throw the Exception when called before next");
        } catch (final IllegalStateException e) {}
    }

    @Test
    public void testNextOnEmptyCollection() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        listIterator.next();
        listIterator.remove();
        listIterator.next();
        listIterator.remove();
        try {
            listIterator.next();
            fail("next do not throw the Exception when no more elements");
        } catch (final NoSuchElementException e) {}
    }

    @Test
    public void testHasPreviousWhenIteratorAtTheEndOfTheCollection() {

        testInstance.add(1);
        testInstance.add(2);
        assertFalse("The ArrayList  has no previous items! Your previousIndex() is wrong.", listIterator.hasPrevious());
        listIterator.next();
        assertTrue("The ArrayList has the previous elements! Your previousIndex() is wrong.", listIterator.hasPrevious());
    }


    @Test
    public void testAddInIteratorWhenEmptyList() {

        listIterator.add(1);
        listIterator.add(2);
        assertSame("previousIndex ",1, listIterator.previousIndex());
        assertSame("previous element ", 2, listIterator.previous());
        assertSame("First element ", 1, testInstance.get(0));
        assertEquals("size",2, testInstance.size());
    }

    @Test
    public void testAddInIteratorWhenIsNotEmptyListToTheBeginning() {


        testInstance.add(0);
        testInstance.add(0);
        testInstance.add(0);
        listIterator.add(1);
        assertSame("previousIndex ",0, listIterator.previousIndex());
        assertSame("nextIndex ",1, listIterator.nextIndex());
        assertSame("previous element ", 1, listIterator.previous());
        assertSame("Get first element ",1, testInstance.get(0));
        assertEquals("size",4, testInstance.size());
    }

    @Test
    public void testAddInIteratorLastIsNotSet() {

        listIterator.add(1);
        listIterator.add(2);
        listIterator.add(3);
        try {
            listIterator.set(222);
            fail("set method can not be called after add (E). Wrong last element.");
        } catch (final IllegalStateException e){}

        listIterator.add(4);
    }

    @Test
    public void testSetWhenNeitherNextNorPreviousHaveBeenCalled() throws Exception {
        testInstance.add(1);
        try {
            listIterator.set(null);
            fail("set method do not throw IllegalStateException the if neither next nor previous have been called");
        } catch (final IllegalStateException e){}
        listIterator.add(2);
        try {
            listIterator.set(null);
            fail("set method do not throw IllegalStateException the if neither next nor previous have been called");
        } catch (final IllegalStateException e){}
    }

    @Test
    public void testSetAfterNext() {
        testInstance.add(1);
        testInstance.add(3);
        listIterator.next();
        listIterator.next();
        listIterator.set(2);
        assertEquals("set() should replaces the last element returned by next() or previous()", (Integer)2, testInstance.get(1));
    }

    @Test
    public void testSetAfterPrevious() {
        testInstance.add(1);
        testInstance.add(3);
        testInstance.add(4);
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.set(3);
        listIterator.previous();
        listIterator.set(2);

        assertEquals("set() should replaces the last element returned by next() or previous()", (Integer)1, testInstance.get(0));
        assertEquals("set() should replaces the last element returned by next() or previous()", (Integer)2, testInstance.get(1));
        assertEquals("set() should replaces the last element returned by next() or previous()", (Integer)3, testInstance.get(2));
    }

    @Test
    public void testPreviousIndex() {
        testInstance.add(1);
        listIterator.next();
        assertEquals("Your previousIndex() is wrong.", 0, listIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexWhenItEqualsTo1() {

        testInstance.add(1);
        testInstance.add(1);
        listIterator.next();
        listIterator.next();

        assertEquals("Your previousIndex() is wrong.", 1, listIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexWhenEmptyCollection() {
        assertEquals("In an empty collection, previousIndex() must return -1!", -1, listIterator.previousIndex());
    }

    @Test
    public void testPreviousWhenEmptyCollection() throws Exception {

        try {
            listIterator.previous();
            fail("list iterator do not throw the Exception when called previous method on empty collection");
        } catch (final java.util.NoSuchElementException e) {}
    }

    @Test
    public void testHasPreviousWhenEmptyCollection() {
        assertFalse("Your hasPrevious() is wrong.", listIterator.hasPrevious());
    }

    @Test
    public void testRemoveTwoTimeInTheRow() throws Exception {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);

        listIterator.next();

        listIterator.remove();
        assertEquals("Expected collection size is 4, however actual is not", 4, testInstance.size());
        try {
            listIterator.remove();
            fail("remove do not throw the Exception when called twice");
        } catch (final IllegalStateException e) {}
    }

    @Test
    public void testRemoveAfterThePrevious() throws Exception {
        listIterator.add(0);
        listIterator.add(1);
        listIterator.add(2);
        listIterator.add(3);
        listIterator.add(4);

        listIterator.previous();
        listIterator.previous();
        try {
            listIterator.remove();
        } catch (final IllegalStateException e) {
            throw new RuntimeException("remove() call can only be made once per call to next() or previous(). " +
                    "It can be made only if add(E) has not been called after the last call to next() or previous().");
        }
        //System.out.println(Arrays.deepToString(testInstance.toArray()));
        assertEquals("Expected collection size is 4, however actual is not", 4, testInstance.size());
        assertEquals("Expected element [3] == 4, however actual is not", (Integer)4, testInstance.get(3));
    }

    @Test
    public void testPreviousAfterNextWithOneElement() {
        testInstance.add(1);
        final Integer next = listIterator.next();
        final Integer previous = listIterator.previous();
        assertEquals("From the documentation: \n" +
                "Note that alternating calls to next() and previous()" +
                " will return the same element repeatedly ", next, previous);
    }

    @Test
    public void testPreviousAfterNextMoreElements() {
        testInstance.add(0);
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        //System.out.println(Arrays.deepToString(testInstance.toArray()));
        listIterator.next();
        listIterator.next();
        assertEquals("From the documentation: \n" +
                "Note that alternating calls to next() and previous()" +
                " will return the same element repeatedly ", (Integer) 2, listIterator.next());
        assertEquals("After next() index should be greater", 3, listIterator.nextIndex());

        assertEquals("From the documentation: \n" +
                "Note that alternating calls to next() and previous()" +
                " will return the same element repeatedly ", (Integer) 2, listIterator.previous());
        assertEquals("After previous() index should be less", 2, listIterator.nextIndex());

        assertEquals("From the documentation: \n" +
                "Note that alternating calls to next() and previous()" +
                " will return the same element repeatedly ", (Integer) 2, listIterator.next());
        assertEquals("After next() index should be greater", 3, listIterator.nextIndex());

        assertEquals("From the documentation: \n" +
                "Note that alternating calls to next() and previous()" +
                " will return the same element repeatedly ", (Integer) 2, listIterator.previous());
        assertEquals("After previous() index should be less", 2, listIterator.nextIndex());
    }
}