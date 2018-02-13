package com.cayuela.ghost.controller;

import com.cayuela.ghost.dto.Form;
import com.cayuela.ghost.dto.GameResponse;
import com.cayuela.ghost.exceptions.IllegalPlayException;
import com.cayuela.ghost.game.GhostGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * Technical name: {@code ghost}
 * <p>
 * Sub-Function: {@literal Ghost > GET/POST request}
 * <p>
 * <ul>
 * <li>Handle the Spring based requests</li>
 * <li>Evaluate {@code letter}</li>
 * <li>Modify {@link GameResponse gameResponse} accordingly</li>
 * <li>Handle {@link IllegalPlayException}'s occurring during processing</li>
 * <li>Determine the ghost game</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
@Controller
@RequestMapping(value = "/ghost")
public class HomeController {
    
    private static final String GAME_RESPONE = "gameResponse";

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    /**
     * Create new form object for empty from
     *
     * @return the empty form
     */
    @ModelAttribute("form")
    public Form setUpFormForm() {
        return new Form();
    }

    /**
     * The Get request to start the game
     *
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String get(HttpSession httpSession, Model model) {

        // In this case we have to set the response through Model...
        model.addAttribute(GAME_RESPONE,
                new GameResponse(getGhostGame(httpSession).getCurrentNode().getNodeValue(), ""));

        return "home";
    }

    /**
     * The post request with the letter played.
     *
     * @param form
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String post(@ModelAttribute("form") Form form,
            HttpSession httpSession, Model model) {

        Character play = Character.toLowerCase(form.getLetter());

        GhostGame ghostGame = getGhostGame(httpSession);
        try {
            ghostGame.playAgainstComputer(play);

            if (ghostGame.gameOver()) {
                httpSession.invalidate();
                String string = ghostGame.getCurrentNode().getNodeValue();
                model.addAttribute(GAME_RESPONE,
                        new GameResponse(string,
                                "Game Over: Player " + ghostGame.getCurrentNode().whoPlaysThisNode() + " loses. ["
                                        + string + "] is a word in our dictionary ..."));
                return "home";
            }

        } catch (IllegalPlayException invalidPlay) {
            httpSession.invalidate();
            model.addAttribute(GAME_RESPONE,
                    new GameResponse(ghostGame.getCurrentNode().getNodeValue(),
                            "Game Over: " + invalidPlay.getMessage()));
            return "home";
        }

        model.addAttribute(GAME_RESPONE,
                new GameResponse(ghostGame.getCurrentNode().getNodeValue(), ""));
        return "home";
    }

    /**
     * Manage all the exceptions provide by Spring Framework
     * Just show in the Modal Dialog
     *
     * @param ex the exeption
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        logger.error("ErrorException: " + ex.getMessage());
        ModelAndView model = new ModelAndView("home", "form", new Form());
        model.addObject(GAME_RESPONE,
                new GameResponse("", ex.getMessage()));
        return model;

    }


    private GhostGame getGhostGame(HttpSession httpSession) {
        GhostGame ghostGame = (GhostGame) httpSession.getAttribute("game");

        if (ghostGame == null) {
            ghostGame = new GhostGame();
            httpSession.setAttribute("game", ghostGame);
        }

        return ghostGame;
    }
}
