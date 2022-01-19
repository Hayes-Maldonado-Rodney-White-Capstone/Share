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

import java.security.Timestamp;
import java.time.LocalDateTime;

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
        model.addAttribute("userMessage", new Message());

        return "/messages/viewmessage";

    }

    @PostMapping("/messages/{recieverId}")
    public String sendMessage(@PathVariable long recieverId, @ModelAttribute Message userMessage) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User sendingUser = userDao.getById(loggedInUser.getId());
        User receivingUser = userDao.getById(recieverId);

//        Message userMessage = new Message();

        userMessage.setSender(sendingUser);
        userMessage.setReceiver(receivingUser);
        userMessage.setMessage(userMessage.getMessage());
        userMessage.setTimeSent(LocalDateTime.now());
        messageDao.save(userMessage);


        return "redirect:/messages/" + recieverId;


    }






}
