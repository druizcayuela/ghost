package com.cayuela.ghost.game;


import com.cayuela.ghost.exceptions.IllegalPlayException;
import com.cayuela.ghost.tree.Node;
import com.cayuela.ghost.tree.Tree;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

/**
 * {@code ghost} GHOST GAME.
 * <p>
 * Technical names: {@code Ghost}
 * <ul>
 * <li>Model to handle logic in the Ghost Game</li>
 * <li>It will have the tree structure</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
public class GhostGame implements Serializable{

    private static final long serialVersionUID = 1126590095558370837L;

    private final Tree tree;
    private Node currentNode;

    public GhostGame() {
        tree = Tree.getInstance();
        currentNode = tree.getRoot();
    }

    public boolean gameOver() {
        return currentNode.isLeaf();
    }

    public void playAgainstComputer(Character playedLetter) {
        if (playedLetter != null) {
            //First of all, play the HUMAN with te letter given.
            //Just we need to change the currentNode
            playHumanPlayer(playedLetter);
            // ... now processing response play
            playBestForTheComputer();
        }
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    private void playHumanPlayer(Character playedLetter) {
        if (gameOver()) {
            return;
        }

        updateCurrentNodeAfterPlay(Player.HUMAN, playedLetter);
    }

    private void playBestForTheComputer() {
        if (gameOver()) {
            return;
        }

        updateCurrentNodeAfterPlay(Player.COMPUTER, chooseBestLetterToPlayForComputer());
    }

    private void updateCurrentNodeAfterPlay(Player player, Character playedLetter) {

        Node nextNode = currentNode.getTheNextNode(playedLetter);
        if (nextNode == null) {
            throw new IllegalPlayException(
                    MessageFormat
                            .format(
                                    "Invalid play [{0}] for player [{1}]. The resulting word [{2}] "+
                                            "does not exist in the dictionary and cannot be extended into a word ...",
                                    playedLetter, player, currentNode.getNodeValue() + playedLetter));
        }

        currentNode = nextNode;
    }

    private Character chooseBestLetterToPlayForComputer() {

        List<Node> winnerChildren = currentNode.getWinnerChildrenForComputer();
        if (CollectionUtils.isNotEmpty(winnerChildren)) {
            // If the computer thinks it can win, the computer only will choose the path which
            //was not pruned the tree in the PruneAfterTheCreationTreeVisitor
            return winnerChildren.get(0).getLetter();
        }

        return choosePathWhenMachineCanLoose().getLetter();
    }

    // "if the computer thinks it will lose,
    // use the next heuristics.
    private Node choosePathWhenMachineCanLoose() {

        return currentNode.getResultFromAlphaBetaPruning();
    }
}