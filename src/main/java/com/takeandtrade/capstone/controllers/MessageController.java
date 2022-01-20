package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.models.Item;
import com.takeandtrade.capstone.models.Message;
import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.repositories.CategoryRepository;
import com.takeandtrade.capstone.repositories.ItemRepository;
import com.takeandtrade.capstone.repositories.MessageRepository;
import com.takeandtrade.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {

    private final MessageRepository messageDao;
    private final UserRepository userDao;

    //dependency injection
    public MessageController(MessageRepository messageDao, UserRepository userDao) {
        this.messageDao = messageDao;
        this.userDao = userDao;
    }

    @GetMapping("/messages")
    public String viewMessageIndex(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        model.addAttribute("users", userDao.findAll());
        List<Message> messageList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        return "messages/index";
    }

    @GetMapping("/messages/{recieverId}")
    public String viewOneMessage(Model model, @PathVariable long recieverId) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User viewUser = userDao.getById(recieverId);

        model.addAttribute("user", viewUser);

        return "/messages/view";

    }

    @GetMapping("/messages/create")
    public String viewCreateMessage(@ModelAttribute Message message) {

        return "messages/create";
    }

    @PostMapping("/messages/create")
    public String createMessage(@ModelAttribute Message message) {
        messageDao.save(message);
        return "redirect:/messages";
    }



}
