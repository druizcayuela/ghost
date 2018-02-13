package com.cayuela.ghost.visitorpattern;


import com.cayuela.ghost.game.Player;
import com.cayuela.ghost.tree.Node;


/**
 * {@code ghost} GHOST GAME.
 * <p>
 * Technical names: {@code Ghost}
 * <ul>
 * <li>This class will try to decide the players for whom certain node is a goal...</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
public class AimTreeVisitor implements TreeVisitor {

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit(Node node) {

        // If this node is a leaf, the player who plays it loses and
        // then this node becomes a goal for the another one
        if (node.isLeaf()) {

            node.setAimToThePlayer(Player.getNextPlayer(node.whoPlaysThisNode()));
            return;
        }

        // If node is not a leaf we have to set the players it is a goal for from
        // their children...
        for (Player player : Player.values()) {
            if (canBeAObjetive(node, player)) {
                node.setAimToThePlayer(player);
            }
        }
    }

    private boolean canBeAObjetive(Node node, Player player) {

        boolean allNodesInChildrenAreGoals = true;

        for (Node child : node.getChildren()) {
            if (child.getAimToThePlayer() == player) {
                if (player == child.whoPlaysThisNode()) {
                    // if any of the child nodes is a goal for the Player
                    // and the player can choose it because it's going to be
                    // played in his turn the current node is also a goal for
                    // the player
                    return true;
                }
            } else {
                allNodesInChildrenAreGoals = false;
            }
        }

        // If all nodes beneath this one are goals for the Player
        // the current node is a goal for that player...
        return allNodesInChildrenAreGoals;
    }

}