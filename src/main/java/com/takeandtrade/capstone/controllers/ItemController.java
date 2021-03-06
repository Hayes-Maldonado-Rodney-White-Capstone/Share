package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.*;
import com.takeandtrade.capstone.models.Category;
import com.takeandtrade.capstone.models.Item;
import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.repositories.CategoryRepository;
import com.takeandtrade.capstone.repositories.ItemRepository;
import com.takeandtrade.capstone.repositories.RequestRepository;
import com.takeandtrade.capstone.repositories.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class ItemController {
    //dependency injection
    private final ItemRepository itemDao;
    private final CategoryRepository categoryDao;
    private final UserRepository userDao;
    private final RequestRepository requestDao;

    public ItemController(ItemRepository itemDao, CategoryRepository categoryDao, UserRepository userDao, RequestRepository requestDao){
        this.itemDao = itemDao;
        this.categoryDao = categoryDao;
        this.userDao = userDao;
        this.requestDao = requestDao;
    }

    //get the form that allows the logged in user to create a new post
    @GetMapping("/items/create")
    public String viewCreateItemForm(Model model){
        model.addAttribute("item", new Item());
        List<Category> categoryList = categoryDao.findAll();
        model.addAttribute("categories", categoryList);
        return "items/create";
    }

    //when the user submits the form, postmapping saves the information into the db
    @PostMapping("/items/create")
    public String addNewItem(@Valid @ModelAttribute Item item, Errors validation , Model model, @RequestParam("images") MultipartFile multipartFile) throws IOException {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("item", item);
            List<Category> categoryList = categoryDao.findAll();
            model.addAttribute("categories", categoryList);
            return "items/create";
        }

        User itemLender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  //this grabs the logged in user
        item.setUser(itemLender);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("filename " + fileName);
        item.setImage(fileName);
        itemDao.save(item);

        String uploadDir = "./src/main/webapp/images/capstoneimages/";   ///this creates a directory
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
        return "redirect:/items";
    }

    //get item index
    @GetMapping("/items")
    public String viewItemsIndex(Model model) {
        model.addAttribute("items", itemDao.findAll());
        List <Category> categoriesList = categoryDao.findAll();
        model.addAttribute("categories", categoriesList);
        return "items/itemsindex";
    }

    //postmapping for Item search bar
    @PostMapping("/items/search")
    public String searchForItems(@RequestParam("search") String search, Model model) {
        model.addAttribute("search", itemDao.search(search));
        return "items/search";
    }

    //view one item
    @GetMapping("/items/{itemId}")
    public String viewOneItem(Model model, @PathVariable Long itemId){
        Item viewItem = itemDao.getById(itemId);
        model.addAttribute("item", viewItem);
        model.addAttribute("user", viewItem.getUser()); //getting the user that created the item
        return "items/viewone";
    }

    //edit functionality
    @GetMapping("/edit/{itemId}")
    public String editItemForm(Model model, @PathVariable Long itemId) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userId", loggedInUser);
        System.out.println("logged in user" + loggedInUser.getId());
        Item item = itemDao.getById(itemId);
        User creator = item.getUser();
        System.out.println("creator get id" + creator.getId());
        List<Category> categoryList = categoryDao.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("items", item); //this pre-populates the info in the form in the edit.html
        return "items/edit";
    }

    @PostMapping("/items/edit/{itemId}")
    public String editItem(@PathVariable("itemId") Long itemId, @ModelAttribute Item item, @RequestParam("images") MultipartFile multipartFile) throws IOException {
        item.setDatePosted(LocalDateTime.now()); //set the date again after editing the item
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        item.setImage(fileName);

        itemDao.save(item);
        String uploadDir = "./src/main/webapp/images/capstoneimages/";
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
        return "redirect:/items";
    }

    //delete functionality
    @PostMapping("/items/deleteitem")
    public String deleteItem(Long itemId) {
        itemDao.deleteById(itemId);
        return "redirect:/userprofile";
    }

}
