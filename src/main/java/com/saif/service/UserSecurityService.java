package com.saif.service;

import com.saif.model.User;
import com.saif.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.getUserById(Long.parseLong(username));
    if(user == null)
      throw new UsernameNotFoundException("User not found");
//        .orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exist!"));

    return org.springframework.security.core.userdetails.User
        .withUsername(user.getName())
        .password(user.getPassword())
        .accountLocked(false)
        .disabled(false)
        .roles("USER")
        .build();
  }
}
