/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> Edges = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices, Edge<L>s) = a graph that contains vertices and directed weighted Edge<L>s
    // Representation invariant:
    //   An Edge<L> has positive integer weight
    //   The set of Edge<L>s' vertices is strictly a subset of graph's vertices
    // Safety from rep exposure:
    //   graph is an immutable data type
    //   rep are all private fields
    
    //  constructor
    public ConcreteEdgesGraph() {
        
    }
    
    // checkRep
    private void checkRep() {
        Set<L> verticesSet = new HashSet<L>();
        for (Edge<L> e: Edges) {
            assert e.weight() > 0;
            verticesSet.add(e.from());
            verticesSet.add(e.to());
        }
        assert vertices.containsAll(verticesSet);
    }
    
    @Override public boolean add(L vertex) {
        if (vertices.contains(vertex)) {
            return false;
        } else {
            vertices.add(vertex);
            checkRep();
            return true;
        }
    }
    
    @Override public int set(L source, L target, int weight) {
        if (!vertices.contains(source)) {
            vertices.add(source);
        }
        if (!vertices.contains(target)) {
            vertices.add(target);
        }
        // Edge<L> exists and need to be deleted
        if (weight == 0) {
            for (Edge<L> e: Edges) {
                if (e.from().equals(source) && e.to().equals(target)) {
                    int prev = e.weight();
                    Edges.remove(Edges.indexOf(e));
                    checkRep();
                    return prev;
                }
            }
        } else { // Edge<L> exists and need to be updated
            for (Edge<L> e: Edges) {
                if (e.from().equals(source) && e.to().equals(target)) {
                    int prev = e.weight();
                    Edge<L> updatedEdge = new Edge<L>(source, target, weight);
                    Edges.add(updatedEdge);
                    checkRep();
                    return prev;
                }
            }   
        }
        // Edge<L> does not exist and need to be inserted
        Edge<L> newEdge = new Edge<L>(source, target, weight);
        Edges.add(newEdge);
        return 0;
    }
    
    @Override public boolean remove(L vertex) {
        List<Edge<L>> toBeRemoved = new ArrayList<Edge<L>>();
        if (vertices.contains(vertex)) {
            vertices.remove(vertex);
            for (Edge<L> e: Edges) {
                if (e.from().equals(vertex) || e.to().equals(vertex)) {
                    toBeRemoved.add(e);
                }
            }
            Edges.removeAll(toBeRemoved);
            checkRep();
            return true;
        } else {
            return false;
        }
        
    }
    
    @Override public Set<L> vertices() {
        return new HashSet<L>(vertices);
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> result = new HashMap<L, Integer>();
        for (Edge<L> e: Edges) {
            if (e.to().equals(target)) {
                result.put(e.from(), e.weight());
            }
        }
        checkRep();
        return result;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> result = new HashMap<L, Integer>();
        for (Edge<L> e: Edges) {
            if (e.from().equals(source)) {
                result.put(e.to(), e.weight());
            }
        }
        checkRep();
        return result;
    }
    
    //  toString()
    @Override public String toString() {
        String result = "";
        for (Edge<L> e: Edges) {
            result += e.from() + " -> " + e.to() + " (" + e.weight() + "); ";
        }
        if (result.equals("")) {
            return "EMPTY GRAPH";
        } else {
            return result;
        }  
    }
    
}

/**
 * specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdge<L>sGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // fields
    private final L source;
    private final L target;
    private int weight;
    
    // Abstraction function:
    //   AF(source, target, weight) = an directed Edge<L> with positive weight
    // Representation invariant:
    //   positive weight
    //   source and target should not be null
    // Safety from rep exposure:
    //   private field, immutable data type
    
    //  constructor
    /**
     * return a new Edge<L> instance
     * @param source
     * @param target
     * @param weight
     */
    Edge(L source, L target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    
    //  checkRep
    /**
     * check the rep invariants are held
     */
    void checkRep() {
        assert weight > 0 && source != null && target != null;
    }
    
    /**
     * return the source vertex
     * @return source vertex in L format
     */
    L from() {
        return source;
    }

    /**
     * return the target vertex
     * @return target vertex in L format
     */
    L to() {
        return target;
    }

    /**
     * return the weight of the Edge<L>
     * @return weight in int format
     */
    int weight() {
        return weight;
    }
    
    //  toString()
    @Override
    public String toString() {
        return source + " -> " + target + " (" + weight + "); ";
    }
    
}
