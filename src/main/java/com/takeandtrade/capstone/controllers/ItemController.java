package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.models.Item;
import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.repositories.ItemRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
    //dependency injection
    private final ItemRepository itemDao;

    public ItemController(ItemRepository itemDao){
        this.itemDao = itemDao;
    }

//    @GetMapping("/items/create")
//    public String viewCreateItemForm(Model model){
//        model.addAttribute("item", new Item());
//        return "/items/create";
//    }
//
//    @PostMapping("/items/create")
//    public String addNewItem(@ModelAttribute Item item) {
//        User itemLender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        item.setUser(itemLender);
//        itemDao.save(item);
//        return "redirect:/templates/items";
//    }
//
//    @GetMapping("/items")
//    public String itemsIndex(Model model) {
//        model.addAttribute("items", itemDao.findAll());
//        return "templates/items";
//    }



}
