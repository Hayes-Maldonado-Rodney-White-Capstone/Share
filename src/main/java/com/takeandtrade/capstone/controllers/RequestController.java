package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.models.Item;
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
import java.util.List;

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

    //get the request form
    @GetMapping("/request/{itemId}")
    public String viewRequestForm(Model model, @PathVariable Long itemId){
        model.addAttribute("request", new Request());
        Item requestItem = itemDao.getById(itemId);  //get the item id
        System.out.println("id of item " + requestItem.getId());
        System.out.println("name of item " + requestItem.getItemName());
        model.addAttribute("item", requestItem);  //this allows us to show information about the item
        System.out.println("producer/lender " + requestItem.getUser().getUsername());

        return "items/request-form";
    }

    //add postMapping to handle what happens when the user submits the form
    @PostMapping("/request/{itemId}")
    public String submitRequestForm(@ModelAttribute Request request, @PathVariable Long itemId, Model model){
        //the logged in user is making the request
        User itemBorrower = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  //this grabs the logged in user
        request.setUserReq(itemBorrower);
        System.out.println("who's making the request " + itemBorrower.getUsername());
        Item requestedItem = itemDao.getById(itemId);
        model.addAttribute(itemId);

        model.addAttribute("request", request);

        //what should happen when someone submits the request form.
        //we should set approver1 to be the string person who posted the item
        request.setApprover1(requestedItem.getUser().getUsername());
        System.out.println("set approver to " + requestedItem.getUser().getUsername());
        //set the status to Pending?
//        request.setStatus("PENDING");
//        System.out.println("status " + request.getStatus());

        //save the info to the db
        requestDao.save(request);

        return "items/request-submitted";
    }

}
