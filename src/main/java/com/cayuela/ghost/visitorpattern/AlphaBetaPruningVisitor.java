package com.cayuela.ghost.visitorpattern;


import com.cayuela.ghost.game.Player;
import com.cayuela.ghost.tree.Node;

import java.util.List;

/**

 */
/**
 * {@code ghost} GHOST GAME.
 * <p>
 * Technical names: {@code Ghost}
 * <ul>
 * <li>
 *  This class will try to decide which is the best path when the machine is thinking that it can be loose
 *  I'm using the mini max alogorith with the alpha-beta pruning</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
public class AlphaBetaPruningVisitor implements TreeVisitor {

    private Node defeatFinalNode;
    private static final int ALLOWED_DEPTH = 2; // Examining the tree with a depth of 3
    private static final int P_INFINITY = Integer.MAX_VALUE;
    private static final int N_INFINITY = Integer.MIN_VALUE;

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit(Node node) {

        setDefeatNodeWithAlphaBetaPruning(node);
    }

    /**
     * Recursive minimax at level of depth for either maximizing or minimizing player.
     */
    private void setDefeatNodeWithAlphaBetaPruning(Node node) {

        maxValue(N_INFINITY, P_INFINITY, ALLOWED_DEPTH, node);
    }

    public int maxValue(int alpha, int beta, int depth, Node node) {
        if (node.isLeaf()){
            return heuristic(node);
        }

        int value = N_INFINITY;
        int prevValue = value;
        List<Node> nodeChildren = node.getChildren();
        for (Node n : nodeChildren) {
            int v = minValue(alpha, beta, depth - 1, n);

            if (v > value)
                value = v;


            if (value >= beta)
                return value;

            if (alpha < value)
                alpha = value;

            if (depth == ALLOWED_DEPTH && v == value) {
                if (prevValue < value) {
                    prevValue = value;
                }
                defeatFinalNode = n;
            }
        }
        return value;
    }

    public int minValue(int alpha, int beta, int depth, Node node) {
        if (node.isLeaf())
            return heuristic(node);

        int value = P_INFINITY;

        List<Node> nodeChildren = node.getChildren();

        for (Node n : nodeChildren) {
            int v = maxValue(alpha, beta, depth - 1, n);

            if (v < value)
                value = v;

            if (value <= alpha)
                return value;

            if (beta > value)
                beta = value;
        }

        return value;
    }


    public Node getDefeatFinalNode() {
        return defeatFinalNode;
    }

    public int heuristic(Node node) {
        int value = 0;

		/* Since the user plays first, the computer will win when the number of letters in the word
         * are odd for the user to finish last.
		*/
        if (node.getAimToThePlayer() == Player.COMPUTER){
            value += 20;
        } else{
            value -= 10;
        }


        // The chances of extending the game and choosing more words increases when words are lengthy.
        value += (node.getNodeValue().length());

        // The more winning nodes there are, the more likely the human will be to fail
        if(node.getParentNode() != null){
            for (Node parentNode : node.getParentNode().getChildren()){
                if (parentNode.getAimToThePlayer() == Player.COMPUTER){
                    value += 1;
                }else{
                    value -= 1;
                }
            }
        }

        return value;
    }
}