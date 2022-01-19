package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.models.Item;
import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.repositories.CategoryRepository;
import com.takeandtrade.capstone.repositories.ItemRepository;
import com.takeandtrade.capstone.repositories.MessageRepository;
import com.takeandtrade.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MessageController {

    private final MessageRepository messageDao;
    private final UserRepository userDao;

    //dependency injection
    public MessageController(MessageRepository messageDao, UserRepository userDao) {
        this.messageDao = messageDao;
        this.userDao = userDao;
    }

    @GetMapping("/messages/messagesindex")
    public String viewMessageIndex(Model model) {

        model.addAttribute("users", userDao.findAll());

        return "messages/messagesindex";
    }

    @GetMapping("/messages/{recieverId}")
    public String viewOneMessage(Model model, @PathVariable long recieverId) {

        User viewUser = userDao.getById(recieverId);

        model.addAttribute("user", viewUser);

        return "/messages/viewmessage";

    }





}
