/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilterTest {

    /**
     * Test strategy:
     * 
     * Partition on username case sensitivity:
     * same case
     * different case
     * 
     * Partition on timespan length:
     * same timestamp (0 timespan length)
     * different timestamp (non-zero timespan length)
     * 
     * Partition on text case sensitivity:
     * same text with different case
     * same text with same case
     * 
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "dragon-ring", "no way #hype", d2);
    private static final Tweet tweet4 = new Tweet(4, "dragon-Ring", "NO WAY #hype", d1);
    private static final Tweet tweet5 = new Tweet(5, "dragon-ring", "I am going crazy about that #hype", d1);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        
        assertEquals("expected singleton list", 1, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
    }
    
    /**
     * covers:
     * same case
     * different case
     */
    @Test
    public void testWrittenByDifferntUserNameCase() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet3, tweet4, tweet5), "dragon-ring");
        
        assertEquals("expected singleton list", 3, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet3));
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet4));
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet5));
    }
    
    @Test
    public void testInTimespanMultipleTweetsMultipleResults() {
        Instant testStart = Instant.parse("2016-02-17T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet1));
    }
    
    /**
     * covers:
     * same timestamp (0 timespan length)
     */
    @Test
    public void testInTimespanSameTimestamp() {
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet2, tweet3, tweet1), new Timespan(d2, d2));
        
        assertEquals("expected list length", 2, inTimespan.size());
        assertTrue("expected list should contain", inTimespan.containsAll(Arrays.asList(tweet2, tweet3)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet2));
    }
    
    @Test
    public void testContaining() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("talk"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, containing.indexOf(tweet1));
    }
    
    @Test
    public void testContainingDifferentCase() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet3, tweet4), Arrays.asList("way"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet3, tweet4)));
        assertEquals("expected same order", 0, containing.indexOf(tweet3));
    }

    /*
     * Warning: all the tests you write here must be runnable against any Filter
     * class that follows the spec. It will be run against several staff
     * implementations of Filter, which will be done by overwriting
     * (temporarily) your version of Filter with the staff's version.
     * DO NOT strengthen the spec of Filter or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Filter, because that means you're testing a stronger
     * spec than Filter says. If you need such helper methods, define them in a
     * different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}
