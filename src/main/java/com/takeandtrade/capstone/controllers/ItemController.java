package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.*;
import com.takeandtrade.capstone.models.Category;
import com.takeandtrade.capstone.models.Item;
import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.repositories.CategoryRepository;
import com.takeandtrade.capstone.repositories.ItemRepository;
import com.takeandtrade.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class ItemController {
    //dependency injection
    private final ItemRepository itemDao;
    private final CategoryRepository categoryDao;
    private final UserRepository userDao;

    public ItemController(ItemRepository itemDao, CategoryRepository categoryDao, UserRepository userDao){
        this.itemDao = itemDao;
        this.categoryDao = categoryDao;
        this.userDao = userDao;
    }

    //this gets the form that allows the logged in user to create a new post
    @GetMapping("/items/create")
    public String viewCreateItemForm(Model model){
        model.addAttribute("item", new Item());
        List<Category> categoryList = categoryDao.findAll();
        model.addAttribute("categories", categoryList);

        return "items/create";
    }

    //when the user submits the form, the postmapping saves the information into the db, and then redirects the browser to /items
    @PostMapping("/items/create")
    public String addNewItem(@ModelAttribute Item item, Model model) {
//        User itemLender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  //this grabs the logged in user
//        item.setUser(itemLender);

        //we'll use this code below for testing so we don't have to log in each time, when the create form is function use the code above for production
        User user = userDao.getById(1L);
        item.setUser(user);
        //
        item.setDatePosted(LocalDateTime.now());
        itemDao.save(item);
        return "redirect:/items";
    }

    //get item index
    @GetMapping("/items")
    public String viewItemsIndex(Model model) {
        model.addAttribute("items", itemDao.findAll());
        return "items/itemsindex";
    }

    //view one item
    @GetMapping("/items/{itemId}")
    public String viewOneItem(Model model, @PathVariable Long itemId){
        Item viewItem = itemDao.getById(itemId);
        model.addAttribute("item", viewItem);
        model.addAttribute("user", viewItem.getUser()); //getting the user that created the item
        return "/items/viewone";
    }



}
