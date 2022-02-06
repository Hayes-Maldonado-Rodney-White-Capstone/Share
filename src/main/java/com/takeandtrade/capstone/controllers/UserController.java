package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.models.Rating;
import com.takeandtrade.capstone.models.Request;
import com.takeandtrade.capstone.models.Review;
import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;

@Controller
public class UserController {

    private final UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private final ItemRepository itemDao;
    private final ReviewRepository reviewDao;
    private final RatingRepository ratingDao;
    private final RoleRepository roleDao;
    private final RequestRepository requestDao;


    public UserController(UserRepository userDao,
                          PasswordEncoder passwordEncoder, ItemRepository itemDao, ReviewRepository reviewDao, RatingRepository ratingDao, RoleRepository roleDao, RequestRepository requestDao) {

        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.itemDao = itemDao;
        this.reviewDao = reviewDao;
        this.ratingDao = ratingDao;
        this.roleDao = roleDao;
        this.requestDao = requestDao;

    }

    @GetMapping("/registerform")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());  //I added this line and it made the red squigglies go away on the registerForm

        return "registerform";
    }

    @PostMapping("/registerform")
    public String saveUser(@Valid @ModelAttribute User user, Errors validation, Model model, @RequestParam("profilepic") MultipartFile multipartFile) throws IOException {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            return "registerform";
        }

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        //uses will be assigned the role of user upon registration, remember to use the seeder file after created the role table the first time, and after dropping your db
        user.setRoles(Collections.singletonList(roleDao.findByRoleType("user")));

        //for profile pictures
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("filename " + fileName);
        user.setProfileimage(fileName);

        userDao.save(user);

        //fileupload2
        String uploadDir = "./src/main/webapp/images/capstoneimages/";   ///this should save it in a directory named capstoneimages
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath.toFile().getAbsolutePath());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save image file: " + fileName, e);
        }

        return "redirect:/homepage";
    }

    @GetMapping("/viewPosterProfile/{posterId}")
    public String showPosterProfile(Model model, @PathVariable Long posterId){
                User poster = userDao.getById(posterId);

                model.addAttribute("user", poster);
                model.addAttribute("userItems",poster.getItems());
                model.addAttribute("reviews", poster.getWrittenReviews());
        return "users/viewPosterProfile";
    }

    @GetMapping("/userprofile")
    public String userHome(Model model) {
        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User itemOwner = userDao.getById(loggedinUser.getId());
        model.addAttribute("itemOwner", itemOwner);

        model.addAttribute("viewUser", loggedinUser);
        model.addAttribute("items", itemOwner.getItems());

        return "users/userprofile";
    }



    @GetMapping("/userEdit")
    public String updateUser(Model model) {
        User updateUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", updateUser);
        return "users/userEdit";

    }

    @PostMapping("/userEdit/{userId}")
    public String saveUserUpdate(@PathVariable Long userId,
        @RequestParam(name = "firstName") String firstName,
        @RequestParam(name = "lastName") String lastName,
        @RequestParam(name = "username") String username,
        @RequestParam(name = "email") String email,
        @RequestParam(name = "phoneNumber") Long phoneNumber,
        @RequestParam(name = "city") String city,
        @RequestParam(name = "state") String state,
        @RequestParam(name = "zipcode") String zipcode,
        @RequestParam(name = "password") String password

    ) {
        User updateTheUser = userDao.getById(userId);
        updateTheUser.setFirstName(firstName);
        updateTheUser.setLastName(lastName);
        updateTheUser.setUsername(username);
        updateTheUser.setEmail(email);
        updateTheUser.setPhoneNumber(phoneNumber);
        updateTheUser.setCity(city);
        updateTheUser.setState(state);
        updateTheUser.setZipcode(Integer.parseInt(zipcode));
        String hash = passwordEncoder.encode(password);
        updateTheUser.setPassword(hash);
//        updateTheUser.setPassword(password);

        userDao.save(updateTheUser);

        return "redirect:/userprofile";
    }

//    @PostMapping ("/user/delete/{userId}")
//    public String deleteUser(@PathVariable Long userId) {
//        System.out.println("delete id " + userId);
//        userDao.deleteById(userId);
//        return "redirect:/homepage";
//    }

    @PostMapping ("/user/delete")
    public String deleteUser() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findById(loggedInUser.getId());
        userDao.delete(user);
        return "redirect:../login";
    }


    @GetMapping("/myReviews")
    public String showMyReviews(Model model) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User consumerUser = userDao.getById(loggedInUser.getId());//user that is writing the review on the item they borrowed
        model.addAttribute("loggedInUser", consumerUser);

        User producerUser = userDao.getById(loggedInUser.getId());//
        model.addAttribute("producerUser", producerUser );

        model.addAttribute("user", producerUser);
        model.addAttribute("userReview", new Review());

        model.addAttribute("reviews", reviewDao.findAll());

        List<Rating> ratingList = ratingDao.findAll();
        model.addAttribute("ratings", ratingList);

        model.addAttribute("avgRating", reviewDao.findRatingAverage(producerUser.getId()));
    System.out.println(reviewDao.findRatingAverage(producerUser.getId()));
        System.out.println(producerUser.getId());
        //testing
        List<Review> reviewList = reviewDao.findAll();
        model.addAttribute("reviewList", reviewList);

        return "users/myReviews";
    }

    //mapped to myRequests.html
    @GetMapping("/myRequests")
    public String showMyRequests(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User producerUser = userDao.getById(loggedInUser.getId()); //logged in user is the one who posted the item, so they will have requests, and they are approver1
        model.addAttribute("loggedInUser", producerUser);

        model.addAttribute("requests", requestDao.findAll());
        List<Request> requestList = requestDao.findAll();
        model.addAttribute("requests", requestList);

        return "users/myRequests";
    }


}

