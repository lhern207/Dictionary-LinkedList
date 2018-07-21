/*
 * Builds a dictionary LinkedList node.
 * Each node contains a word and its meaning.
 */

/**
 *
 * @author Lester
 */

//This class is used to build nodes in the correct formatting to be added to the LinkedList
public final class WordMeaningNodeBuilder {

    private static String node;

    public WordMeaningNodeBuilder() {
    }

    //Build a brand new node
    public static String buildNode(String word, String meaning) {
        node = word + "\n" + "     " + "- " + meaning;
        return node;
    }

    //Update an already existing node with a definition
    public static String updateNode(String extraMeaning, String existingNode) {
        node = existingNode + "\n" + "     " + "- " + extraMeaning;
        return node;
    }

}
