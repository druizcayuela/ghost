package com.cayuela.ghost.controller;

import com.cayuela.ghost.dto.Form;
import com.cayuela.ghost.dto.GameResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * <p>
 * Technical name: {@code ghost}
 * <p>
 * Sub-Function: {@literal HANDLE ERROR from  > GET/POST request}
 * <p>
 * <ul>
 * <li>Handle the Spring based on error requests</li>
 * <li>Evaluate {@code error code}</li>
 * <li>Modify the error with a new response</li>
 * <li>Handle HTTP errors during processing</li>
 * <li>I should have a new GUI to show and handle our custom server error</li>
 * <li>at the moment, just return no the home page</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
@Controller
public class HTTPErrorHandlerController {

    private static final String PATH = "home";

    private static final Logger logger = LoggerFactory.getLogger(HTTPErrorHandlerController.class);

    @Autowired
    private MessageSource messageSource;

    /*
    * Create new form object for empty from
    */
    @ModelAttribute("form")
    public Form setUpFormForm() {
        return new Form();
    }

    /**
     * I should redirect to a custom error page ...
     *
     * @param httpSession
     * @return
     */
    @RequestMapping(value = {"/400", "/404", "/500"})
    public String httpError(Locale locale, HttpSession httpSession, Model model, HttpServletRequest httpRequest) {
        // add parametrized message from controller
        StringBuilder error = new StringBuilder();

        error.append(messageSource.getMessage("home.error", null, locale));
        error.append("\n");
        error.append(getErrorMessage(httpRequest, locale));

        logger.error(error.toString());
        model.addAttribute("gameResponse", new GameResponse("", error.toString()));
        httpSession.invalidate();
        return PATH;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }

    private String getErrorMessage(HttpServletRequest httpRequest, Locale locale) {

        int httpErrorCode = getErrorCode(httpRequest);

        return messageSource.getMessage("home." + httpErrorCode, null, locale);
    }
}
