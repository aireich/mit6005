/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //
    // empty():
    // no input, output is an empty graph
    //
    // add():
    // Partition on added vertex:
    // vertex has been existed, graph is not modified
    // vertex is new
    //
    // set():
    // Partition on set() method behavior:
    // insert a new edge
    // update existing edge
    //
    // Partition on edge weight in set() method:
    // non-zero weight edge
    // zero weight edge, edge removed
    //
    // remove():
    // Partition on a vertex about to be removed:
    // vertex has been existed
    // vertex does not exist
    //
    // sources():
    // given valid input, verify the size and elements of returned result are correct
    // remove an vertex, then corresponding sources() should change if needed
    //
    // targets()
    // given valid input, verify the size and elements of returned result are correct
    // remove an vertex, then corresponding targets() should change if needed
    // 
    // vertices():
    // empty graph
    // at least one vertex

    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // covers
    // add():
    // vertex has been existed, graph is not modified
    // vertex is new
    // vertices():
    // at least one graph
    @Test
    public void testAdd() {
        Graph<String> test = emptyInstance();
        test.add("first");
        assertEquals("expected graph size after the first add", 1, test.vertices().size());
        test.add("second");
        assertEquals("expected graph size after the second add", 2, test.vertices().size());
        test.add("first");
        assertEquals("expected graph size after adding existing first ele", 2, test.vertices().size());
        assertTrue("graph will not be changed after adding existing ele", test.vertices().contains("first"));
        assertTrue("graph will not be changed after adding existing ele", test.vertices().contains("second"));
        assertFalse("expected return after adding existing first ele", test.add("first"));
        assertTrue("expected return after adding new ele", test.add("third"));
    }
    
    // covers:
    // set() method behavior:
    // insert a new edge
    // update existing edge
    //
    //  edge weight in set() method:
    // non-zero weight edge
    // zero weight edge, edge removed
    @Test
    public void testSet() {
        Graph<String> test = emptyInstance();
        test.add("first");
        test.add("second");
        test.set("first", "second", 1);
        assertEquals("graph size should not be changed", 2, test.vertices().size());
        test.set("second", "third", 2);
        assertEquals("expected graph size after adding an edge with non-existing vertex", 3, test.vertices().size());
        assertEquals("expected return after updating existing edge", 1, test.set("first", "second", 3));
        assertEquals("expected return after adding an edge with non-existing vertex", 0, test.set("zero", "ten", 1));
        assertEquals("expected graph size after adding an edge with non-existing vertex", 5, test.vertices().size());
        assertEquals("expected graph size after deleting an edge", 1, test.set("zero", "ten", 0));
        assertTrue("graph should still contain vertices after deleting the adge", test.vertices().contains("zero"));
        assertTrue("graph should still contain vertices after deleting the adge", test.vertices().contains("ten"));
    }
    
    //covers:
    // remove():
    // Partition on a vertex about to be removed:
    // vertex has been existed
    // vertex does not exist
    @Test
    public void testRemove() {
        Graph<String> test = emptyInstance();
        test.add("first");
        test.add("second");
        assertTrue("expetced return after remove an existing vertex", test.remove("first"));
        assertEquals("expetced graph size after remove an existing vertex", 1, test.vertices().size());
        assertTrue("expetced return after remove an existing vertex", test.remove("second"));
        assertEquals("expetced graph size after remove an existing vertex", 0, test.vertices().size());
        test.add("third");
        assertFalse("expetced return after remove a non-existed vertex", test.remove("first"));
    }
    
    // covers:
    // sources():
    // given valid input, verify the size and elements of returned result are correct
    @Test
    public void testSources() {
        Graph<String> test = emptyInstance();
        test.set("first", "second", 1);
        test.set("third", "second", 3);
        test.set("second", "fourth", 2);
        test.add("fifth");
        test.add("sixth");
        assertEquals("expected graph size", 6, test.vertices().size());
        assertEquals("expected source set size of first", 2, test.sources("second").size());
        Map<String, Integer> firstSource = test.sources("second");
        assertTrue("expected ele contanied by first source set", firstSource.get("first").equals(1));
        assertTrue("expected ele contanied by first source set", firstSource.get("third").equals(3)); 
        test.remove("first");
        assertFalse("graph should not conatin removed vertex", test.sources("second").keySet().contains("first"));
    }
    
    // covers:
    // targets():
    // given valid input, verify the size and elements of returned result are correct
    @Test
    public void testTargets() {
        Graph<String> test = emptyInstance();
        test.set("first", "second", 1);
        test.set("first", "third", 3);
        test.set("second", "fourth", 2);
        test.add("fifth");
        test.add("sixth");
        assertEquals("expected graph size", 6, test.vertices().size());
        assertEquals("expected target set size of first", 2, test.targets("first").size());
        Map<String, Integer> firstTarget = test.targets("first");
        assertTrue("expected ele contanied by first target set", firstTarget.get("second").equals(1));
        assertTrue("expected ele contanied by first target set", firstTarget.get("third").equals(3)); 
        test.remove("first");
        assertFalse("graph should not conatin removed vertex", test.targets("second").keySet().contains("first"));
    }
    
}
