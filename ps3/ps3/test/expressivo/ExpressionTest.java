/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   Partition on Expression:
    //   empty Expression
    //   at least one expression
    //
    //   Partition on Number:
    //   zero
    //   positive integer
    //   positive integer with five decimal places
    //
    //   Partition on Variable:
    //   single letter
    //   letter sequence
    //   same letter with different cases
    //   
    //   Partition on Plus:
    //   single plus sign
    //   multiple plus sign
    //   
    //   Partition on Multiply:
    //   single multiply sign
    //   multiple multiply sign
    //
    //   Partition on toString():
    //   empty Expression
    //   valid Expression with all variants (addition and multiplication sequence)
    //
    //   Partition on equals():
    //   must obey structural equality
    //   must have an equivalence relationship (reflexive, symmetric, transitive)
    //   throw exception for null
    //   integer and double with decimal should be equal when decimal followed by a zero
    //   two double type should be tested to at least five decimal places
    //   
    //   Partition on hashCode():
    //   return same value for same equals() expression
    //   test to five decimal places
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    static final Expression empty = Expression.emptyExpression();
    
    // tests for Expression.parse()
    //covers:
    //zero
    @Test
    public void testZero() {
        Expression test = Expression.parse("0");
        assertNotNull("expected not null", test);
        assertEquals("expected string format", "0.0", test.toString());
    }
    
    //covers:
    //positive integer
    @Test
    public void testPositiveInteger() {
        Expression test = Expression.parse("42");
        assertNotNull("expected not null", test);
        assertEquals("expected string format", "42.0", test.toString());
    }
    
    //covers
    //positive integer with five decimal places
    @Test
    public void testDecimalPositiveInteger() {
        Expression test = Expression.parse("42.00000");
        assertNotNull("expected not null", test);
        assertEquals("expected string format", "42.00000", test.toString());
    }
    
    //covers
    //single letter
    @Test
    public void testSingleLetter() {
        Expression test = Expression.parse("a");
        assertNotNull("expected not null", test);
        assertEquals("expected string format", "a", test.toString());
    }
    
    //covers:
    //letter sequence
    @Test
    public void testLetterSequence() {
        Expression test = Expression.parse("foo");
        assertNotNull("expected not null", test);
        assertEquals("expected string format", "foo", test.toString());
    }
    
    //covers:
    //same letter with different cases
    @Test
    public void testLetterDifferentCase() {
        Expression test = Expression.parse("Foo");
        assertNotNull("expected not null", test);
        assertEquals("expected string format", "Foo", test.toString());
        assertFalse("expected not equals ", test.equals(new Variable("foo")));
    }
    
    //Test for toString():
    //covers:
    //single multiply sign
    @Test
    public void testSinglePlus() {
        Expression test = Expression.parse("0 + 0");
        assertNotNull("expected not null", test);
        assertEquals("expected string format", "0.0 + 0.0", test.toString());
        
    }
    
    //covers:
    //multiple multiply sign
    @Test
    public void testMultiplePlus() {
        Expression test = Expression.parse("(0.0 + 0 + 1)");
        Expression test1 = Expression.parse("(3+4) + 5");
        Expression test2 = Expression.parse("(3+4 + 5)");
        assertNotNull("expected not null", test);
        assertEquals("expected string format", "0.0 + 0.0 + 1.0", test.toString());
        assertTrue("expected equals", test1.equals(test2));
        
    }
    
    //covers:
    //single multiply sign
    @Test
    public void testSingleMultiply() {
        Expression test = Expression.parse("5 * 6");
        assertNotNull("expected not null", test);
        assertEquals("expected string format", "(5) * (6)", test.toString());
    }
    
    //covers:
    //multiple multiply sign
    @Test
    public void testMultipleMultiply() {
        Expression test = Expression.parse("5 * 6*7");
        assertNotNull("expected not null", test);
        assertEquals("expected string format", "((5) * (6)) * (7)", test.toString());
    }
    
    //covers:
    // empty Expression
    @Test
    public void testEmptyExpression() {
        assertNotNull("expected not null", empty);
        assertEquals("expected string format", "", empty.toString());
    }
    
    //covers:
    // valid Expression with all variants
    @Test 
    public void testAllVariants() {
        Expression test = Expression.parse("3+5*6");
        assertNotNull("expected not null", test);
        assertEquals("expected string format", "3 + ((5) * (6))", test.toString());
        Expression test1 = Expression.parse("(3 + 5)*6");
        assertNotNull("expected not null", test1);
        assertEquals("expected string format", "(3 + 5) * (6)", test1.toString());
    }
    
    //covers:
    // structural equality
    // must have an equivalence relationship (reflexive, symmetric, transitive)
    @Test
    public void testStuctural() {
        Expression test1 = Expression.parse("1+x");
        Expression test2 = Expression.parse("(1 + x)");
        Expression test3 = Expression.parse("1 + x");
        Expression test4 = Expression.parse("(1) + (x)");
        Expression test5 = Expression.parse("x + 1");
        //structural
        assertTrue("expected equals", test1.equals(test2));
        assertTrue("expected equals", test1.equals(test3));
        assertTrue("expected equals", test1.equals(test4));
        assertFalse("expected equals", test1.equals(test5));
        // reflexive
        assertTrue("expected equals", test1.equals(test1));
        //symmetric
        assertTrue("expected equals", test2.equals(test1));
        assertTrue("expected equals", test3.equals(test1));
        assertTrue("expected equals", test4.equals(test1));
        //transitive
        assertTrue("expected equals", test2.equals(test3));
        assertTrue("expected equals", test2.equals(test4));
        assertTrue("expected equals", test3.equals(test4));
    }
    
    //covers:
    //throw exception for null
    @Test(expected=AssertionError.class)
    public void testNull() {
        Expression.parse(null);
    }
    
    //covers:
    // integer and double with decimal should be equal when decimal followed by a zero
    @Test
    public void testZeroDecimal() {
        Expression test = Expression.parse("1");
        Expression test1 = Expression.parse("1.0");
        assertEquals("expected equals", test, test1);
    }
    
    //covers:
    // two double type should be tested to at least five decimal places
    @Test
    public void testFiveDecimals() {
        Expression test = Expression.parse("1.01");
        Expression test1 = Expression.parse("1.01000");
        assertEquals("expected equals", test, test1);
        Expression test2 = Expression.parse("x + 1.01");
        Expression test3 = Expression.parse("x + 1.01000");
        assertEquals("expected equals", test2, test3);
    }
    
    //Test for hashCode():
    //covers:
    // return same value for same equals() expression
    // test to five decimal places
    @Test
    public void testSameHashCode() {
        Expression test = Expression.parse("1.01");
        Expression test1 = Expression.parse("1.01000");
        assertEquals("expected equals", test.hashCode(), test1.hashCode());
    }
}
