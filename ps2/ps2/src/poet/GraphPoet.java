/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   AF(corpus) = a corpus represented by a graph
    // Representation invariant:
    //   graph is not null
    // Safety from rep exposure:
    //   private field with immutable data type
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        try {
            Scanner sc = new Scanner(corpus);
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\s+");
                for (int i = 1; i < line.length; i++) {
                    graph.set(normalize(line[i - 1]), 
                            normalize(line[i]), 1);
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // checkRep
    public void checkRep() {
        assert graph != null;
    }
    
    /**
     * remove all symbols except latter in a string and turn it into lower case
     * @param word input word
     * @return a word contains only lower case letters 
     */
    private static String normalize(String word) {
        return word.replaceAll("[^A-Za-z]+", "").toLowerCase();
    }
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String result = "";
        String[] inputs = input.split("\\s+");
        for (int i = 0; i < inputs.length - 1; i++) {
            boolean foundSecond = false;
            String currentWord = normalize(inputs[i]);
            if (graph.vertices().contains(currentWord)) { //contains the first word
                Map<String, Integer> bridgeWords = graph.targets(currentWord);
                for (String bridge: bridgeWords.keySet()) {
                    String nextWord = normalize(inputs[i + 1]);
                    if (graph.targets(bridge).keySet().contains(nextWord)) { //contains the second word
                        result += currentWord + " " + bridge + " " ;
                        foundSecond = true;
                    }
                }
                if (!foundSecond) {
                    result += inputs[i] + " "; // contains the first but does not contain the second word
                }
            } else {
                result += inputs[i] + " "; // does not contain the first word
            }
        }
        result += inputs[inputs.length - 1] + " "; // add the last word
        return result.replaceAll("\\s+$", "");
    }
    
    //toString()
    @Override
    public String toString() {
        return graph.toString();
    }
    
}
