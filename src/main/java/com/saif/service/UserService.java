package com.saif.service;

import com.saif.helper.exception.ServiceException;
import com.saif.model.User;
import com.saif.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User create(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User findById(Long id) throws ServiceException {
    return userRepository.findById(id)
        .orElseThrow(() -> new ServiceException(
            "User not found with ID: " + id,
            HttpStatus.NOT_FOUND
        ));
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User update(User user) {
    return userRepository.save(user);
  }

  public void delete(Long id) {
    userRepository.deleteById(id);
  }
}
