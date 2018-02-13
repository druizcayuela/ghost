package com.cayuela.ghost.tree;

import com.cayuela.ghost.game.Player;
import com.cayuela.ghost.visitorpattern.AlphaBetaPruningVisitor;
import com.cayuela.ghost.visitorpattern.ShortestPathToWinFasterTreeVisitor;
import com.cayuela.ghost.visitorpattern.TreeVisitor;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ghost} GHOST GAME.
 * <p>
 * Technical names: {@code Ghost}
 * <ul>
 * <li>Node for the tree structure</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
public class Node implements Serializable {

    private static final long serialVersionUID = 1126590095558370837L;

    private final String nodeValue;
    private final boolean completeWord;
    //good practice know who will win when you choose this node
    private Player aimToThePlayer;
    private List<Node> children;

    private Node parentNode; //to be what is the parent node of a node

    //default constructor, only used in the root of the tree
    public Node() {
        this("", false, null);
    }

    public Node(String nodeValue, boolean completeWord, Node parent) {
        this.nodeValue = nodeValue;
        this.completeWord = completeWord;
        if (parent != null) {
            parent.addChild(this);
        }
        this.parentNode = parent;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public boolean isCompleteWord() {
        return completeWord;
    }

    public List<Node> getChildren() {
        if (children == null) {
            children = new ArrayList<Node>();
        }

        return children;
    }

    public List<Node> getWinnerChildrenForComputer() {
        List<Node> winnerChildren = new ArrayList<Node>();

        for (Node child : getChildren()) {
            if (child.getAimToThePlayer() == Player.COMPUTER) {
                winnerChildren.add(child);
            }
        }

        return winnerChildren;
    }

    /**
     * Get the player who play this node.
     *
     * @return the Player
     */
    public Player whoPlaysThisNode() {
        if (getNodeValue() == null || getNodeValue().length() == 0) {
            return null;
        }
        //If it is even, is the human's turn.
        return (getNodeValue().length() - 1) % 2 == 0 ? Player.HUMAN : Player.COMPUTER;
    }

    /**
     * Get the Next node with the played letter
     *
     * @param play the played letter
     * @return the node
     */
    public Node getTheNextNode(Character play) {
        for (Node childNode : getChildren()) {
            if (play == childNode.getLetter()) {
                return childNode;
            }
        }

        return null;
    }

    public Character getLetter() {
        return getNodeValue().charAt(getNodeValue().length() - 1);
    }

    public Player getAimToThePlayer() {
        return aimToThePlayer;
    }

    public boolean isLeaf() {
        return CollectionUtils.isEmpty(getChildren());
    }

    public void postOrder(TreeVisitor treeVisitor) {
        postOrder(this, treeVisitor);
    }

    public void preOrder(TreeVisitor treeVisitor) {
        preOrder(this, treeVisitor);
    }

    public Node getShortestPathToWinFaster() {
        ShortestPathToWinFasterTreeVisitor visitor = new ShortestPathToWinFasterTreeVisitor();
        postOrder(visitor);
        return visitor.getDefeatFinalNode();
    }

    public Node getResultFromAlphaBetaPruning() {
        AlphaBetaPruningVisitor visitor = new AlphaBetaPruningVisitor();
        visitor.visit(this);
        return visitor.getDefeatFinalNode();
    }

    public void setAimToThePlayer(Player aimToThePlayer) {
        this.aimToThePlayer = aimToThePlayer;
    }

    private void postOrder(Node node, TreeVisitor treeVisitor) {

        for (Node child : node.getChildren()) {
            postOrder(child, treeVisitor);
        }

        treeVisitor.visit(node);
    }

    private void preOrder(Node node, TreeVisitor treeVisitor) {

        treeVisitor.visit(node);

        for (Node child : node.getChildren()) {
            preOrder(child, treeVisitor);
        }
    }

    private void addChild(Node childNode) {
        getChildren().add(childNode);
    }

    @Override
    public String toString() {
        return "Node [nodeValue=" + nodeValue + ", completeWord=" + completeWord
                + ", aimToThePlayer=" + aimToThePlayer + "]";
    }
}
