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
     * Test case to check if read the whole txt
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
     * Test case to check if read the whole txt
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

    @Test(expected = IllegalPlayException.class)
    public void playFallibleHumanOptionTest() {
        GhostGame ghostGameNWords = new GhostGame();
        ghostGameNWords.playAgainstComputer('2');
    }
}
