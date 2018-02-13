package com.cayuela.ghost.visitorpattern;


import com.cayuela.ghost.game.Player;
import com.cayuela.ghost.tree.Node;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;


/**
 * {@code ghost} GHOST GAME.
 * <p>
 * Technical names: {@code Ghost}
 * <ul>
 * <li>A prune done after the creation of Tree Structure</li>
 * <li>I just need one path to the machine AI</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
public class PruneAfterTheCreationTreeVisitor implements TreeVisitor {

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit(Node node) {

        if (Player.COMPUTER == Player.getNextPlayer(node.whoPlaysThisNode())) {
            // The next one to play is the COMPUTER: we can expect from him only
            // logical moves, we can prune the tree in order to get
            // gain efficiency in execution time and memory
            List<Node> winnerChildren = node.getWinnerChildrenForComputer();
            if (CollectionUtils.isNotEmpty(winnerChildren)) {
                // We will consider only winner children for COMPUTER, and take just one case
                //for example, the shortest path to computer's win
                node.getChildren().clear();
                node.getChildren().add(getShortestPathToWinFaster(winnerChildren));
            }
        }
    }


    private Node getShortestPathToWinFaster(List<Node> winnerChildren) {

        //In the case that there is just one case, obviously, it is not necessary get the short path ...
        if(winnerChildren.size() == 1){
            return winnerChildren.get(0);
        }

        Node nodeWithMinDepth = null;
        int minDepth = Integer.MAX_VALUE;

        //Getting the node which has the minimum depth value
        for (Node winnerChild : winnerChildren) {
            Node defeatFinalNode = winnerChild.getShortestPathToWinFaster();

            if (defeatFinalNode != null) {
                int defeatFinalNodeDepth = defeatFinalNode.getNodeValue().length();

                if (defeatFinalNodeDepth < minDepth) {
                    minDepth = defeatFinalNodeDepth;
                    nodeWithMinDepth = winnerChild;
                }
            }
        }

        return nodeWithMinDepth;
    }
}
