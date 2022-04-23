package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MySocialNetworkTest {
    
    /**
     * Testing strategy:
     *  Partition on hashtag:
     *  no hashtag
     *  at least one hashtag

     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "charlie", "Im looking for a job @alyssa", d2);
    private static final Tweet tweet4 = new Tweet(4, "dw-news", "incoming #hype for hype", d2);
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    /**
     * covers:
     * no hashtag
     */
    @Test
    public void testguessFollowsGraphNoHashtag() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1));
        assertEquals("expected graph size", 0, followsGraph.size());
    }
    
    /**
     * covers:
     * at least one hashtag
     */
    @Test
    public void testguessFollowsAtLeatOneHashtag() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet2, tweet4, tweet1, tweet3));
        assertEquals("expected graph size", 3, followsGraph.size());
    }
    

}
