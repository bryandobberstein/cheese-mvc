package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by max on 5/16/17.
 */
@Controller
@RequestMapping(value = "cheese")
public class CheeseController {


    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Get Cheesy");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAdd(Model model, @ModelAttribute @Valid Cheese cheese, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }
        CheeseData.add(cheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(Model model){
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemove(@RequestParam int[] cheeseIds) {

        for (int id : cheeseIds){
            CheeseData.remove(id);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable int cheeseId){
        Cheese eCheese = CheeseData.getById(cheeseId);
        model.addAttribute("cheese", eCheese);
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("title", "Edit cheese " + eCheese.getName() + " (id = " + eCheese.getCheeseId() + ")");
        return "cheese/edit";
    }

    @RequestMapping(value="edit/{cheeseId}", method=RequestMethod.POST)
    //public String processEditForm(@RequestParam int cheeseId, @RequestParam String name, @RequestParam String description){
    public String processEdit(Model model, @RequestParam int cheeseId, @RequestParam String name, @RequestParam String description, @RequestParam CheeseType type){

        CheeseData.getById(cheeseId).setName(name);
        CheeseData.getById(cheeseId).setDescription(description);
        CheeseData.getById(cheeseId).setType(type);

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }
}
