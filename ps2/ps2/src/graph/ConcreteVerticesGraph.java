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
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices) = a graph that contains vertices and directed weighted edges
    // Representation invariant:
    //   An edge has positive integer weight
    //   The set of edges' vertices is strictly a subset of graph's vertices
    // Safety from rep exposure:
    //   immutable data type and private fields
    
    // constructor
    public ConcreteVerticesGraph() {}
    
    // checkRep
    private void checkRep() {
        for (Vertex<L> v: vertices) {
            Map<L, Integer> nearby = v.connectedTo();
                for (L s: nearby.keySet()) {
                    assert nearby.get(s) > 0;
            }
        }
    }
    
    @Override public boolean add(L Vertex) {
        boolean existed = false;
        for (Vertex<L> v: vertices) {
            if (v.name().equals(Vertex)) {
                existed = true;
            }
        }
        if (existed) {
            return false;
        } else {
            vertices.add(new Vertex<L>(Vertex));
            checkRep();
            return true;
        }
    }
    
    @Override public int set(L source, L target, int weight) {
        for (Vertex<L> v: vertices) {
            if (v.name().equals(source)) {
                for (L s: v.connectedTo().keySet()) {
                    if (s.equals(target)) {
                        return v.connect(target, weight); // update or delete Vertex<L> if exists
                    }
                }
            }
        }
        add(source);
        add(target);
        for (Vertex<L> v: vertices) {
            if (v.name().equals(source)) {
                return v.connect(target, weight); // add new relationship if not exists
            }
        }
        return 0;
    }
    
    @Override public boolean remove(L Vertex) {
        Set<Vertex<L>> toBeRemoved = new HashSet<Vertex<L>>();
        for (Vertex<L> v: vertices) {
            if (v.name().equals(Vertex)) {
                toBeRemoved.add(v);
            }
        }
        if (toBeRemoved.size() == 0) {
            return false;
        } else {
            vertices.removeAll(toBeRemoved);
            return true;
        }
    }
    
    @Override public Set<L> vertices() {
        Set<L> result = new HashSet<L>();
        for (Vertex<L> v: vertices) {
            result.add(v.name());
        }
        return result;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> result = new HashMap<L, Integer>();
        for (Vertex<L> v: vertices) {
            Map<L, Integer> connected = v.connectedTo();
            for (L s: connected.keySet()) {
                if (s.equals(target)) {
                    result.put(v.name(), connected.get(s));
                }
            }
        }
        return result;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        for (Vertex<L> v: vertices) {
            if (v.name().equals(source)) {
                return new HashMap<L, Integer>(v.connectedTo());
            }
        }
        return new HashMap<L, Integer>();
    }
    
    // toString()
    @Override public String toString() {
        String result = "";
        for (Vertex<L> v: vertices) {
            for (L s: v.connectedTo().keySet()) {
                result += v.name() + " -> " + s + " (" + v.connectedTo().get(s) + "); ";
            }
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
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph. Each Vertex<L> corresponds to a Vertex<L> in graph
 * and has a Map storing vertices connected to it and their positive weight.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {

    
    
    // fields
    private final L name;
    private final Map<L, Integer> connectedTo;
    
    // Abstraction function:
    //   AF(L) = a Vertex<L> has its name and stores all vertices connected to it
    // Representation invariant:
    //   all weight connected to it is > 0
    //   each Vertex<L> must have a name
    // Safety from rep exposure:
    //   private immutable name
    
    // constructor
    Vertex(L name) {
        this.name = name;
        this.connectedTo = new HashMap<L, Integer>();
    }
    
    //checkRep
    void checkRep() {
        assert name != null;
        for (L s: connectedTo.keySet()) {
            assert connectedTo.get(s) > 0;
        }
    }
    
    //methods
    /**
     * return the connected target vertices
     * @return a Map stores connected target vertices and their weight
     */
    Map<L, Integer> connectedTo() {
        return new HashMap<L, Integer>(connectedTo);
    }
    
    /**
     * connect this Vertex<L> to a new Vertex<L>. If the weight is zero, disconnect it
     * @param v new Vertex<L>
     * @param weight edge weight
     * @return return previous weight if existed, else zero
     */
    int connect(L v, int weight) {
        L toBeRemoved = null;
        boolean remove = false;
        for (L s: connectedTo.keySet()) {
            if (s.equals(v) ) {
                if (weight == 0) {
                    toBeRemoved = s;
                    remove = true;
                } else {
                    int prev = connectedTo.get(s);
                    connectedTo.put(v, weight); // if exists and need to update
                    checkRep();
                    return prev;
                }
            }
        }
        if (remove) {
            int prev = connectedTo.get(v);
            connectedTo.remove(toBeRemoved); // if exists and need to delete
            checkRep();
            return prev;
        } else {
            connectedTo.put(v, weight);
            checkRep();
            return 0;
        }
        
    }
    
    /**
     * return Vertex<L>'s name
     * @return Vertex<L>'s name
     */
    L name() {
        return name;
    }
    // toString()
    @Override public String toString() {
        String result = "";
        for (L s: connectedTo().keySet()) {
            result += name() + " -> " + s + " (" + connectedTo().get(s) + "); ";
        }
        return result;
    }
    
}
