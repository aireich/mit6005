/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * Testing Strategy
     * 
     * Partition on timestamp:
     * All tweets have the same timestamp
     * Not all tweets have the same timestamp
     * 
     * Partition on tweets:
     * Tweets have only one element
     * Tweets have at least two elements
     * 
     * Partition on username:
     * Valid username mention
     * Invalid username mention
     * 
     * Partition on usermention number:
     * No user-mention
     * At least one user-mention
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(1, "charlie", "not for now @alyssa", d1);
    private static final Tweet tweet4 = new Tweet(1, "doge", "check my email abc@mit.edu @charlie", d2);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    /**
     *covers:
     *Tweets have at least two elements
     *Not all tweets have the same timestamp
     */
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    
    /**
     * covers:
     * Tweets have only one element
     */
    @Test
    public void testGetTimeSpanOneTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1));
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d1, timespan.getEnd());
    }
    
    /**
     * covers:
     * Tweets have at least two elements
     * All tweets have the same timestamp
     */
    @Test
    public void testGetTimespanTwoEqualTimeTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet3));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d1, timespan.getEnd());
    }
    
    /**
     * covers:
     * Valid username mention
     * At least one user-mention
     */
    @Test
    public void testGetMentionUsersValidMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
        assertEquals("expected number of user-mention", 1, mentionedUsers.size());
        assertTrue("expected user-mention", mentionedUsers.remove("alyssa"));
    }
    
    /**
     * covers:
     * Invalid username mention
     * At least one user-mention
     */
    @Test
    public void testGetMentionUsersInvalidMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet4));
        assertEquals("expected number of user-mention", 1, mentionedUsers.size());
    }
    
    
    /**
     * covers:
     * No user-mention
     */
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
