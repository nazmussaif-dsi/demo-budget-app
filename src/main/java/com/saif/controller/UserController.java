package com.saif.controller;

import com.saif.helper.exception.ServiceException;
import com.saif.model.User;
import com.saif.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
  private final UserService userService;

  @GetMapping
  public String showAllUsers(Model model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "users/user_list";
  }

  @GetMapping("/{id}")
  public String showUser(@PathVariable("id") Long id, Model model) throws ServiceException{
    User user = userService.findById(id);
    model.addAttribute("user", user);
    return "users/user_details";
  }

  @GetMapping("/add")
  public String showUserForm(Model model) {
    model.addAttribute("user", new User());
    return "users/user_form";
  }

  @PostMapping("/add")
  public String addUser(@ModelAttribute("user") User user) {
    userService.create(user);
    return "redirect:/users";
  }

  @PatchMapping("/update")
  public String updateUser(@ModelAttribute("user") User user){
    userService.update(user);
    return "redirect:/users";
  }

  @DeleteMapping("/delete/{id}")
  public String deleteUser(@PathVariable("id") Long id) {
    userService.delete(id);
    return "redirect:/users";
  }
}
