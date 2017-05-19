package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by max on 5/16/17.
 */
@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Get Cheesy");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAdd(Model model, @ModelAttribute @Valid Cheese cheese, @RequestParam int categoryId, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }

        Category category = categoryDao.findOne(categoryId);
        cheese.setCategory(category);
        cheeseDao.save(cheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(Model model){
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemove(@RequestParam int[] cheeseIds) {

        for (int id : cheeseIds){
            cheeseDao.delete(id);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable int cheeseId){
        Cheese eCheese = cheeseDao.findOne(cheeseId);
        model.addAttribute("cheese", eCheese);
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Edit cheese " + eCheese.getName() + " (id = " + eCheese.getId() + ")");
        return "cheese/edit";
    }

    @RequestMapping(value="edit/{cheeseId}", method=RequestMethod.POST)
    public String processEdit(int cheeseId, Model model, @ModelAttribute @Valid Cheese cheese, Errors errors, @RequestParam int categoryId){

        if (errors.hasErrors()){
            model.addAttribute("title", "Add Cheese");
            return "cheese/edit";
        }

        cheeseDao.delete(cheeseId);
        Category category = categoryDao.findOne(categoryId);
        cheese.setCategory(category);
        cheeseDao.save(cheese);

        return "redirect:/cheese";
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String categoryDisplay(Model model, @RequestParam int id) {

        Category category = categoryDao.findOne(id);
        List<Cheese> cheeses = category.getCheeses();
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Cheeses in Category: " + category.getName());
        return "cheese/index";
    }
}
