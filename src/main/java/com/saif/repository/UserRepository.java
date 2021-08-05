package com.saif.repository;

import com.saif.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepository {

  private final HibernateTemplate hibernateTemplate;

  public List<User> getAllUsers() {
    return hibernateTemplate.loadAll(User.class);
  }

  public User getUserById(Long id) {
    return hibernateTemplate.get(User.class, id);
  }

  @Transactional
  public void saveUser(User user) {
    hibernateTemplate.save(user);
  }

  @Transactional
  public void updateUser(User user) {
    hibernateTemplate.update(user);
  }

  @Transactional
  public void deleteUser(Long id) {
    hibernateTemplate.delete(hibernateTemplate.load(User.class, id));
  }
}
