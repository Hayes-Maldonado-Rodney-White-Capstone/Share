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
        request.setItemReq(requestedItem); //this is what is setting the FK in the request table.

        model.addAttribute("request", request);

        //find all requests for that approver so we can show them on the page?
        //        model.addAttribute("messages", messageDao.findAll());
        model.addAttribute("allmyrequests", requestDao.findAll());

        //what should happen when someone submits the request form.
        //we should set approver1 to be the string person who posted the item
        request.setApprover1(requestedItem.getUser().getUsername());
        System.out.println("set approver to " + requestedItem.getUser().getUsername());

        //save the info to the db
        requestDao.save(request);

        return "items/request-submitted";
    }

    //postmapping for approve button
    @PostMapping("/myRequests/approved")
    public String submitApproval(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User producerUser = userDao.getById(loggedInUser.getId()); //logged in user is the one who posted the item, so they will have requests, and they are approver1
        model.addAttribute("loggedInUser", producerUser);

        model.addAttribute("requests", requestDao.findAll());
        List<Request> requestList = requestDao.findAll();
        model.addAttribute("requests", requestList);

        //when the user clicks approve, we need to change the item availability to false
        //and use the setter to change the request status to APPROVED
        


        return "users/myRequests";

    }

}
