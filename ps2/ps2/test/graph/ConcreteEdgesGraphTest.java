/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   Partition on graph edge number:
    //   zero edge
    //   at least one edge
    
    //  tests for ConcreteEdgesGraph.toString()
    // covers
    // graph edge number:
    // at least one edge
    @Test
    public void testToStringAtLeastOneEdge() {
        Graph<String> test = emptyInstance();
        test.set("first", "second", 1);
        test.set("second", "third", 2);
        test.add("fourth");
        test.add("fifth");
        assertEquals("expected string literal", "first -> second (1); second -> third (2); ", test.toString());
    }
    
    // covers
    // graph edge number:
    // zero edge
    @Test
    public void testToStringNoEdge() {
        Graph<String> test = emptyInstance();
        assertEquals("expected string literal", "EMPTY GRAPH", test.toString());
    }
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   toString()
    //   from()
    //   to()
    //   weight()
    
    //  tests for operations of Edge
    // covers:
    // toString()
    @Test 
    public void testEdgeToString() {
        Edge<String> test = new Edge<String>("first", "second", 1);
        assertEquals("expected string literal", "first -> second (1); ", test.toString());
    }
    
    // covers:
    // from()
    @Test
    public void testEdgeFrom() {
        Edge<String> test = new Edge<String>("first", "second", 1);
        assertEquals("expected from", "first", test.from());
    }
    
    // covers:
    // to()
    @Test
    public void testEdgeTo() {
        Edge<String> test = new Edge<String>("first", "second", 1);
        assertEquals("expected from", "second", test.to());
    }
    
    // covers:
    // weight()
    @Test
    public void testEdgeweight() {
        Edge<String> test = new Edge<String>("first", "second", 1);
        assertEquals("expected from", 1, test.weight());
    }
    
    
}
