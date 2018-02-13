package com.cayuela.ghost.game;

import com.cayuela.ghost.config.AppConfig;
import com.cayuela.ghost.exceptions.IllegalPlayException;
import com.cayuela.ghost.tree.Node;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class GhostGameTest {


    /**
     * Test case to test if the game is over
     */
    @Test
    public void gameOverTest() {
        GhostGame ghostGame = new GhostGame();
        ghostGame.playAgainstComputer('e');
        ghostGame.playAgainstComputer('z');
        ghostGame.playAgainstComputer('d');
        ghostGame.playAgainstComputer('a');
        ghostGame.playAgainstComputer('a');
        Assert.assertTrue(ghostGame.gameOver());
    }

    /**
     * Test case to test if the game is not over
     */
    @Test
    public void gameOverFalseTest() {
        GhostGame ghostGame = new GhostGame();
        ghostGame.playAgainstComputer('p');
        Assert.assertFalse(ghostGame.gameOver());
    }

    /**
     * Test case to do a play will win the player human
     */
    @Test
    public void playInOrderToHumanWinTest() {
        GhostGame ghostGame = new GhostGame();
        ghostGame.playAgainstComputer('e');
        ghostGame.playAgainstComputer('z');
        ghostGame.playAgainstComputer('d');
        ghostGame.playAgainstComputer('a');
        ghostGame.playAgainstComputer('a');
        Node lastNode = ghostGame.getCurrentNode();
        Assert.assertEquals(Player.HUMAN, lastNode.getAimToThePlayer());
    }



    /**
     * Test case to do a play will win the player computer
     */
    @Test
    public void playInOrderToComputerWinTest() {
        GhostGame ghostGame = new GhostGame();
        ghostGame.playAgainstComputer('c');
        ghostGame.playAgainstComputer('u');
        ghostGame.playAgainstComputer('i');
        ghostGame.playAgainstComputer('e');
        Node lastNode = ghostGame.getCurrentNode();
        Assert.assertEquals(Player.COMPUTER, lastNode.getAimToThePlayer());
    }

    /**
     * Test a illegal argument like a number
     */
    @Test(expected = IllegalPlayException.class)
    public void playFallibleHumanOptionTest() {
        GhostGame ghostGameNWords = new GhostGame();
        ghostGameNWords.playAgainstComputer('2');
    }
}
