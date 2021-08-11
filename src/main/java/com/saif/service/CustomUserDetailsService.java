package com.saif.service;

import com.saif.model.CustomUserDetail;
import com.saif.model.User;
import com.saif.model.UserRole;
import com.saif.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exist!"));
    List<UserRole> roles = user.getRoles();

    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
    for (UserRole role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }

    CustomUserDetail customUserDetail = new CustomUserDetail();
    customUserDetail.setUser(user);
    customUserDetail.setAuthorities(authorities);

    return customUserDetail;
  }
}
