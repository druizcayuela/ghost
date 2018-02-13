package com.cayuela.ghost.tree;

import com.cayuela.ghost.config.AppConfig;
import com.cayuela.ghost.game.GhostGame;
import com.cayuela.ghost.game.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class NodeTest {


    /**
     * Test if who play the node it is correct computer case
     */
    @Test
    public void whoPlaysThisNodeComputerTestCase() {
        GhostGame ghostGame = new GhostGame();
        ghostGame.playAgainstComputer('c');
        Node rootNode = ghostGame.getCurrentNode();
        Assert.assertEquals(Player.COMPUTER, rootNode.whoPlaysThisNode());
    }

    /**
     * Test if who play the node it is correct human case
     */
    @Test
    public void whoPlaysThisNodeHumanTestCase() {
        GhostGame ghostGame = new GhostGame();
        ghostGame.playHumanPlayer('c');
        Node rootNode = ghostGame.getCurrentNode();
        Assert.assertEquals(Player.HUMAN, rootNode.whoPlaysThisNode());
    }

    /**
     * Test case to test is leaf a leaf
     */
    @Test
    public void isLeafTest() {
        GhostGame ghostGame = new GhostGame();
        ghostGame.playAgainstComputer('e');
        ghostGame.playAgainstComputer('z');
        ghostGame.playAgainstComputer('d');
        ghostGame.playAgainstComputer('a');
        ghostGame.playAgainstComputer('a');
        Node lastNode = ghostGame.getCurrentNode();
        Assert.assertTrue(lastNode.isLeaf());
    }
}
