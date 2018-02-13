package com.cayuela.ghost.tree;


import com.cayuela.ghost.utils.FileUtils;
import com.cayuela.ghost.visitorpattern.AimTreeVisitor;
import com.cayuela.ghost.visitorpattern.PruneAfterTheCreationTreeVisitor;

import java.io.Serializable;
import java.util.List;

/**
 * {@code ghost} GHOST GAME.
 * <p>
 * Technical names: {@code Ghost}
 * <ul>
 * <li>The tree structure</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
public class Tree implements Serializable {

    private static final long serialVersionUID = 1126590095558370837L;

    //Static holder singleton pattern ...
    private static class SingletonHolder {
        static final Tree uniqueInstance = new Tree();

        private SingletonHolder() {
            throw new IllegalStateException("This class should not be instantiated");
        }
    }

    public static Tree getInstance() {
        return SingletonHolder.uniqueInstance;
    }

    // We could read this from a properties file for instance...
    private static final int MINIMUM_NUMBER_OF_LETTERS_IN_VALID_WORD = 4;

    private Node root;

    private Tree() {

        List<String> wordList = FileUtils.getInstance().getWordList();

        root = new Node();

        // Constructing the tree from words list...
        for (String word : wordList) {
            addWord(word);
        }

        // We can set the nodes with the aim of each player. We'll use post order traversal to "iterate"
        root.postOrder(new AimTreeVisitor());

        // We can reduce or pruning the tree with unnecessary data. In this case I'm going to use the pre order traversal
        root.preOrder(new PruneAfterTheCreationTreeVisitor());

        // Clean up the memory, we do not need it more.
        FileUtils.getInstance().deleteWordLists();
    }


    public Node getRoot() {
        return root;
    }

    private void addWord(String word) {
        if (word == null || word.length() < MINIMUM_NUMBER_OF_LETTERS_IN_VALID_WORD) {
            return;
        }

        Node node = getRoot();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            String fragment = stringBuilder.append(word.charAt(i)).toString();
            boolean completeWord = (i == (word.length() - 1));
            node = getChildNode(node, fragment, completeWord);
            if (node == null) {
                return;
            }
        }
    }

    private Node getChildNode(Node parentNode, String fragment,
                              boolean completeWord) {

        if (parentNode.isCompleteWord()	&& parentNode.getNodeValue().length() >= MINIMUM_NUMBER_OF_LETTERS_IN_VALID_WORD) {
            // We don't need to add more nodes because this already
            // completed word is preventing us from going any further
            return null;
        }

        Node childNode = parentNode.getTheNextNode(fragment.charAt(fragment.length() - 1));
        if (childNode != null) {
            return childNode;
        }

        //In the constructor add the child node+
        return new Node(fragment, completeWord, parentNode);
    }
}