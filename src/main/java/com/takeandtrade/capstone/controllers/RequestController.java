package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.models.Request;
import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.repositories.ItemRepository;
import com.takeandtrade.capstone.repositories.RequestRepository;
import com.takeandtrade.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RequestController {
    //dependency injection
    private final RequestRepository requestDao;
    private final UserRepository userDao;
    private final ItemRepository itemDao;

    public RequestController(RequestRepository requestDao, UserRepository userDao, ItemRepository itemDao) {
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.itemDao = itemDao;
    }

    @GetMapping("/items/request-form")
    public String viewRequestForm(Model model){
        model.addAttribute("request", new Request());
        return "items/request-form";
    }

    //add postMapping to handle what happens when the user submits the form

}
