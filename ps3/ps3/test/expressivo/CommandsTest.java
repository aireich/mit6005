/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    // Testing strategy
   //  Partition on differentiate():
   //   differentiate on constant
   //   differentiate on self
   //   differentiate on other variable
   //   differentiate on plus
   //   differentiate on multiply
   //
   //  Partition one simplify():
   //   single number
   //   two number multiply and add
   //   single variable with no specification
   //   single variable with specification
   //   two variables, one has value
   //   two variables, no one has value
   //   two variables, both have value
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    //covers:
    //   differentiate on constant
    @Test
    public void testOnConstant() {
        assertEquals("expected result", "0.0", Commands.differentiate("3", "x"));
    }
    
    //covers:
    // differentiate on self
    @Test
    public void testOnSelf() {
        assertEquals("expected result", "1.0", Commands.differentiate("x", "x"));
    }
    
    //covers:
    //   differentiate on other variable
    @Test
    public void testDiffOther() {
        assertEquals("expected result", "0.0", Commands.differentiate("x", "y"));
    }
    
    //covers:
    //  differentiate on plus
    @Test
    public void testDiffPlus() {
        assertEquals("expected result", "(2.0 * 1.0) + (x * 0.0) + (3.0 * 1.0) + (x * 0.0)", Commands.differentiate("2 * x + 3*x", "x"));
    }
    
    //covers:
    //   differentiate on multiply
    @Test
    public void testDiffMul() {
        assertEquals("expected result", "(x * 1.0) + (x * 1.0)", Commands.differentiate("x * x", "x"));
    }
    
    //covers:
   //   single number
    @Test
    public void testNumber() {
        Map<String, Double> env = new HashMap<String, Double>();
        assertEquals("expected result", "1.0", Commands.simplify("1", env));
    }
    
    //covers
   //   two number multiply and add
    @Test
    public void testTwoNumber() {
        Map<String, Double> env = new HashMap<String, Double>();
        assertEquals("expected result", "9.0", Commands.simplify("2 * 2 + 5", env));
    }
    
    //covers
   //   single variable with no specification
    @Test
    public void testSingle() {
        Map<String, Double> env = new HashMap<String, Double>();
        assertEquals("expected result", "x", Commands.simplify("x", env));
    }
    
    //covers
   //   single variable with specification
    @Test
    public void testSingleSpec() {
        Map<String, Double> env = new HashMap<String, Double>();
        env.put("x", 5.0);
        assertEquals("expected result", "5.0", Commands.simplify("x", env));
    }
    
    //covers
   //   two variables, one has value
    @Test
    public void testDoubleSpecOne() {
        Map<String, Double> env = new HashMap<String, Double>();
        env.put("x", 5.0);
        assertEquals("expected result", "y + 5.0", Commands.simplify("x + y", env));
    }
    
    //covers
   //   two variables, no one has value
    @Test
    public void testDoubleSpec() {
        Map<String, Double> env = new HashMap<String, Double>();
        assertEquals("expected result", "x + y", Commands.simplify("x + y", env));
    }
    
    //covers
   //   two variables, both have value
    @Test
    public void testDoubleSpecTwo() {
        Map<String, Double> env = new HashMap<String, Double>();
        env.put("x", 5.0);
        env.put("y", 6.0);
        assertEquals("expected result", "11.0", Commands.simplify("x + y", env));
    }
    // TODO tests for Commands.differentiate() and Commands.simplify()
    
}
