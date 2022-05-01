/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //  Partition on graph edge number:
    //   zero edge
    //   at least one edge
    
    // tests for ConcreteVerticesGraph.toString()
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
     * Testing Vertex<L>...
     */
    
    // Testing strategy for Vertex<L>
    //  connected():
    //   Partition on number of vertices connected to this Vertex<L>:
    //   zero Vertex<L>
    //   one Vertex<L>
    //   multiple vertices
    //  connect():
    //   connect with a Vertex<L>
    
    // covers
    //  connect():
    @Test
    public void testConnect() {
        Vertex<String> test = new Vertex<String>("first");
        assertEquals("expected return after adding", 0, test.connect("second", 1));
        assertEquals("expected return after adding", 1, test.connect("second", 1));
    }

    // covers
    // connected():
    // zero Vertex<L>
    @Test
    public void testConnectedZeroVertex() {
        Vertex<String> test = new Vertex<String>("first");
        assertEquals("expected Vertex<L> set size", 0, test.connectedTo().size());
    }
    
    // covers
    // connected():
    // one Vertex<L>
    @Test
    public void testConnectedOneVertex() {
        Vertex<String> test = new Vertex<String>("first");
        test.connect("second", 1);
        assertEquals("expected vertices set size", 1, test.connectedTo().size());
    }
    
 // covers
    // connected():
    // multiple vertices
    @Test
    public void testConnectedMultipleVertices() {
        Vertex<String> test = new Vertex<String>("first");
        test.connect("second", 1);
        test.connect("third", 1);
        assertEquals("expected vertices set size", 2, test.connectedTo().size());
        test.connect("third", 0);
        assertEquals("expected vertices set size", 1, test.connectedTo().size());
    }
    // tests for operations of Vertex<L>
    
}
