/*
 * Dictionary word list implementation as a LinkedList.
 * LinkedList made up of nodes created by WordMeaningNodeBuilder.
 * Each node contains a word and meaning.
 * Nodes are stored on lexicographical order.
 */
/**
 * @author Lester
 */

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class WordList {

    private LinkedList<String> currentList;
    private LinkedList<String> removedWordsList;

    public WordList(String word, String meaning) {
        currentList = new LinkedList<String>();
        removedWordsList = new LinkedList<String>();
        String firstNode = WordMeaningNodeBuilder.buildNode(word, meaning);
        currentList.addFirst(firstNode);
    }

    public void addNode(String word, String meaning) {
        int existsIndex = wordExists(word);

        if (existsIndex == -1) {
            //Word is not in the list - Add in alphabetical order
            //Build a new node
            String newNode = WordMeaningNodeBuilder.buildNode(word, meaning);
            //Find index in words list to keep alphabetical order
            int lexicoIndex = lexicoIndexFinder(word, currentList);
            //Add to list in specified index
            currentList.add(lexicoIndex, newNode);

        } else {
            //Word is in the list - Add meaning to node
            updateNode(existsIndex, meaning);
        }

    }

    public boolean removeNode(String word) {
        int existsIndex = wordExists(word);

        if (existsIndex == -1) {
            //Word is not in the list - Removal failed
            return false;
        } else {
            //Word is in the list - Removal successful
            currentList.remove(existsIndex);

            //Find index in removed words list to keep in alphabetical order
            int lexicoIndex = lexicoIndexFinder(word, removedWordsList);

            //Add to removed words list at specified index.
            removedWordsList.add(lexicoIndex, word);

            return true;
        }

    }

    public String getFormattedCurrentList() {
        ListIterator<String> iter = currentList.listIterator();
        String list = "";
        while (iter.hasNext()) {
            list += iter.next() + "\n\n";
        }
        return list;
    }

    public String getFormattedRemovedList() {
        ListIterator<String> iter = removedWordsList.listIterator();
        String list = "";
        while (iter.hasNext()) {
            list += iter.next() + "\n";
        }
        return list;
    }

    private void updateNode(int nodeIndex, String extraMeaning) {
        String node = currentList.get(nodeIndex);
        node = WordMeaningNodeBuilder.updateNode(extraMeaning, node);
        currentList.set(nodeIndex, node);
    }

    private int wordExists(String word) {
        //Returns index number if word is found in list
        //Returns -1 if word is not found

        ListIterator<String> iter = currentList.listIterator();
        String currentNode;
        int indexCounter = 0;

        while (iter.hasNext()) {
            currentNode = iter.next();
            Scanner readCurrentNode = new Scanner(currentNode);
            if (readCurrentNode.next().equalsIgnoreCase(word)) {
                return indexCounter;
            }
            indexCounter++;
        }
        return -1;

    }

    /*This method not only finds the index at which a node is to be added to the
     dictionary list based on alphabetical order; but it is also generalized to
     find the alphabetical index position in which a word should be placed in any list.
     This is particularly useful in this case, because we can use the method to also 
     organize and order the deleted words list.*/
    private int lexicoIndexFinder(String word, LinkedList list) {
        ListIterator<String> iter = list.listIterator();
        String currentNode;

        int indexCounter = 0;

        while (iter.hasNext()) {
            currentNode = iter.next();
            Scanner readCurrentNode = new Scanner(currentNode);
            if (word.compareToIgnoreCase(readCurrentNode.next()) < 0) {
                return indexCounter;
            }
            indexCounter++;
        }
        return indexCounter;

    }

}
