package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.models.*;
import com.takeandtrade.capstone.repositories.RatingRepository;
import com.takeandtrade.capstone.repositories.ReviewRepository;
import com.takeandtrade.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewController {

    private final ReviewRepository reviewDao;
    private final UserRepository userDao;
    private final RatingRepository ratingDao;

    public ReviewController(ReviewRepository reviewDao, UserRepository userDao, RatingRepository ratingDao) {
        this.reviewDao = reviewDao;
        this.userDao = userDao;
        this.ratingDao = ratingDao;
    }


    @GetMapping("/reviews/{producer_id}")//we are writing the review on the user that posted the item
    public String writeReview(Model model, @PathVariable long producer_id){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User consumerUser = userDao.getById(loggedInUser.getId());//user that is writing the review on the item they borrowed
        model.addAttribute("loggedInUser", consumerUser);

        User producerUser = userDao.getById(producer_id);//
        model.addAttribute("producerUser", producerUser );

        model.addAttribute("user", producerUser);
        model.addAttribute("userReview", new Review());

        model.addAttribute("reviews", reviewDao.findAll());

        List<Rating> ratingList = ratingDao.findAll();
        model.addAttribute("ratings", ratingList);

        return "reviews/writereview";
    }

    @PostMapping("/reviews/{producer_id}")
    public String submitReview(@PathVariable long producer_id, @ModelAttribute Review review){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User consumerUser = userDao.getById(loggedInUser.getId());

        User producerUser = userDao.getById(producer_id);
        review.setConsumer(consumerUser);
        review.setProducer(producerUser);

        System.out.println(consumerUser);
        System.out.println(producerUser);

        reviewDao.save(review);

        return "redirect:/reviews/"+ producer_id;
    }



}
