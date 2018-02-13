package com.cayuela.ghost.visitorpattern;


import com.cayuela.ghost.tree.Node;

/**
 * {@code ghost} GHOST GAME.
 * <p>
 * Technical names: {@code Ghost}
 * <ul>
 * <li>A part of the {@code PruneAfterTheCreationTreeVisitor}</li>
 * <li>It is used to determinate what is the shortest past to win</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
public class ShortestPathToWinFasterTreeVisitor implements TreeVisitor {

    private Node defeatFinalNode;

    public Node getDefeatFinalNode() {
        return defeatFinalNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit(Node node) {
        if (node.isLeaf()) {

            if (defeatFinalNode == null) {
                defeatFinalNode = node;
            }
            if (node.getNodeValue().length() < defeatFinalNode.getNodeValue().length()) {
                defeatFinalNode = node;
            }
        }
    }
}