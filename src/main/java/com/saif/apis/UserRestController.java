package com.saif.apis;

import com.saif.model.User;
import com.saif.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/users")
public class UserRestController {
  private final UserService userService;

  @GetMapping
  public List<User> getAllUsers(){
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable("id") Long id){
    return userService.getUserById(id);
  }

  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public String addUser(@RequestBody User user) {
    userService.saveUser(user);
    return "added";
  }

  @PostMapping("/update")
  public String updateUser(@RequestBody User user){
    userService.updateUser(user);
    return "updated";
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String deleteUser(@PathVariable("id") Long id) {
    userService.deleteUser(id);
    return "deleted";
  }
}
