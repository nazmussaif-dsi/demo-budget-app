package com.saif.apis;

import com.saif.helper.exception.ServiceException;
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
  public List<User> findAll(){
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public User findById(@PathVariable("id") Long id) throws ServiceException {
    return userService.findById(id);
  }

  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public User create(@RequestBody User user) {
    return userService.create(user);
  }

  @PostMapping("/update")
  public User update(@RequestBody User user) {
    return userService.update(user);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id) {
    userService.delete(id);
  }
}
