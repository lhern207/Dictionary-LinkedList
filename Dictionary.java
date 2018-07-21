/*
 * Test class that displays the Dictionary
 * Exception handling missing when wrong menu option is entered
/**
 * @author Lester
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Dictionary {

    public static void main(String[] arg) {
        //Gui implementation
        WordList list = null;
        boolean done = false;
        String menu = "Enter option:\n1. Add word and/or meaning\n2. Remove word from dictionary\n"
                + "3. Display dictionary list\n4. Display removed words list\n5. Quit";
        while (!done) {
            String s = JOptionPane.showInputDialog(menu);

            int i = Integer.parseInt(s);
            switch (i) {
                case 1:
                    String addWordMenu = "Enter a word that you would like to add to the dictionary.\n"
                            + "If the words exists, the meaning/definition you later enter will be "
                            + "added to the ones already existing";
                    String word = JOptionPane.showInputDialog(addWordMenu);
                    String addMeaningMenu = "Enter a meaning/definition that you would like to add to the "
                            + "dictionary word entry that you just specified";
                    String meaning = JOptionPane.showInputDialog(addMeaningMenu);

                    //If the list doesn't exist, create it //Interface will sugget
                    //initializing the object/variable but in this case we don't want that.
                    if (list == null) {
                        list = new WordList(word, meaning);
                    } //If list already exist, just add word and/or meaning
                    else {
                        list.addNode(word, meaning);
                    }
                    display("The word/meaning entry was successfully added to"
                            + " the dictionary", "Entry Successfully Added");

                    break;

                case 2:
                    String removeMenu = "Please enter the word you would like to remove from "
                            + "the dictionary";
                    String wordToRemove = JOptionPane.showInputDialog(removeMenu);

                    if (list == null) {
                        //Display that dictionary list is empty
                        display("Removal failed. The dictionary list is empty", "Removal Unsuccessful");
                    } //If list already exist, search for word and attempt to remove it
                    else {
                        boolean successful = list.removeNode(wordToRemove);

                        if (successful) {
                            //Display that word was found and removed
                            display("The word was successfully removed from the"
                                    + " dictionary list", "Removal Successful");
                        } else {
                            //Display that either list is empty or word is not in the dictionary list.
                            display("Removal failed. Either the dictionary list is empty,\n"
                                    + "or the word entered is not in the listt", "Removal Unsuccessful");
                        }
                    }
                    break;

                case 3:
                    if (list == null) {
                        display("Dictionary List is currently empty", "Dictionary");
                    } else {
                        String currentList = list.getFormattedCurrentList();
                        if (currentList.isEmpty()) {
                            display("Dictionary List is currently empty", "Dictionary");
                        } else {
                            displayScrollPane(currentList, "Dictionary");
                        }
                    }
                    break;

                case 4:
                    if (list == null) {
                        display("Removed Words List is currently empty", "Removed Words List");
                    } else {
                        String removedWordsList = list.getFormattedRemovedList();
                        if (removedWordsList.isEmpty()) {
                            display("Removed Words List is currently empty", "Removed Words List");
                        } else {
                            displayScrollPane(removedWordsList, "Removed Words List");
                        }
                    }

                    break;

                case 5:
                    done = true;
                    break;
                default:
                    display("This option is undefined", "Error");
                    break;
            }

        }
    }

    static void display(String s, String title) {
        JOptionPane.showMessageDialog(null, s, title, JOptionPane.INFORMATION_MESSAGE);
    }

    static void displayScrollPane(String text, String title) {

        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1200, 720));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
