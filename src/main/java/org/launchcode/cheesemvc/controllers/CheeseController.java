package org.launchcode.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by max on 5/16/17.
 */
@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "Get Cheesy");
        return "cheese/index";
    }
}
