package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.*;
import com.takeandtrade.capstone.models.Category;
import com.takeandtrade.capstone.models.Item;
import com.takeandtrade.capstone.repositories.CategoryRepository;
import com.takeandtrade.capstone.repositories.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ItemController {
    //dependency injection
    private final ItemRepository itemDao;
    private final CategoryRepository categoryDao;

    public ItemController(ItemRepository itemDao, CategoryRepository categoryDao){
        this.itemDao = itemDao;
        this.categoryDao = categoryDao;
    }

    @GetMapping("/items/create")
    public String viewCreateItemForm(Model model){
        model.addAttribute("item", new Item());
        List<Category> categoryList = categoryDao.getCategories();
        model.addAttribute("categories", categoryList);

        return "items/create";
    }
//
//    @PostMapping("/items/create")
//    public String addNewItem(@ModelAttribute Item item) {
//        User itemLender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        item.setUser(itemLender);
//        itemDao.save(item);
//        return "redirect:/templates/items";
//    }

    @GetMapping("/items")
    public String viewItemsIndex(Model model) {
        model.addAttribute("items", itemDao.findAll());
        return "items/itemsindex";
    }



}
