package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.models.Message;
import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.repositories.MessageRepository;
import com.takeandtrade.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/messages/messagesindex")
    public String viewMessageIndex(Model model) {

        model.addAttribute("users", userDao.findAll());

        //        model.addAttribute("items", itemDao.findAll());
//        model.addAttribute("messages", messageDao.findAll());

        return "messages/messagesindex";
    }

    @GetMapping("/messages/{receiverId}")
    public String writeOneMessage(Model model, @PathVariable long receiverId) {
        //define the logged in user
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User sendingUser = userDao.getById(loggedInUser.getId());
        model.addAttribute("loggedInUser", sendingUser);

        //when you click on the message button, it grabs the receipient's id.
        User receivingUser = userDao.getById(receiverId);
        model.addAttribute("receivingUser", receivingUser);

        model.addAttribute("user", receivingUser);
        model.addAttribute("userMessage", new Message());

        model.addAttribute("messages", messageDao.findAll());

        return "messages/writemessage";
    }

    @PostMapping("/messages/{receiverId}")
    public String sendMessage(@PathVariable long receiverId, @ModelAttribute Message userMessage) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User sendingUser = userDao.getById(loggedInUser.getId());
        User receivingUser = userDao.getById(receiverId);

//        Message userMessage = new Message();

        userMessage.setSender(sendingUser);
        userMessage.setReceiver(receivingUser);
        System.out.println("post mapping sendingUser " + sendingUser.getUsername());
        System.out.println("post mapping receivingUser " + receivingUser.getUsername());
//        userMessage.setContent(userMessage.getContent());
        userMessage.setTimeSent(LocalDateTime.now());

        messageDao.save(userMessage);

        return "redirect:/messages/" + receiverId;
//        return "redirect:/messages/messagesindex";


    }






}
