package com.cayuela.ghost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * Technical name: {@code ghost}
 * <p>
 * Sub-Function: {@literal redirect get request}
 * <p>
 * <ul>
 * <li>Handle the Spring based requests</li>
 * <li>Determine the ghost game</li>
 * </ul>
 *
 * @author Daniel Ruiz
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    /**
     * In case of GET empty URL, redirect to the correct URL
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView method() {
        return new ModelAndView("redirect:" + "/ghost");
    }
}
