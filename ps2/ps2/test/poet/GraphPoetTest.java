/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //  Partition on input:
    //  no two-edge-long path
    //  one two-edge-long path
    //  no target in the corpus
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    //tests
    //covers:
    // no two-edge-long path
    //  one two-edge-long path
    //  no target in the corpus
    @Test 
    public void testPoem() {
        try {
            File corpus = new File("test/poet/corpus.txt");
            GraphPoet g = new GraphPoet(corpus);
            String poem = g.poem("Seek to explore new and exciting synergies!");
            assertEquals("expected poem: ", "Seek to explore strange new life and exciting synergies!", poem);
        } catch (IOException e) {
            System.out.println("file not found");
        } 
    }
    
}
