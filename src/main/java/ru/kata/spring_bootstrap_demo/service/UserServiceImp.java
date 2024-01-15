package ru.kata.spring_bootstrap_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring_bootstrap_demo.model.User;
import ru.kata.spring_bootstrap_demo.repositories.UserRepository;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

   @Autowired
   public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
   }

   @Override
   @Transactional(readOnly = true)
   public User findByUsername(String username) {
      return userRepository.findByUsername(username);
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByUsername(username);
      if(user == null) {
         throw new UsernameNotFoundException(String.format("user '%s' not found", username));
      }

      return new org.springframework.security.core.userdetails.User(
              user.getUsername(),
              user.getPassword(),
              user.isAccountNonExpired(),
              user.isCredentialsNonExpired(),
              user.isEnabled(),
              user.isAccountNonLocked(),
              user.getRoles());
   }

   @Override
   @Transactional(readOnly = true)
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   @Override
   @Transactional(readOnly = true)
   public User findById(Long id) {
      return userRepository.findById(id)
              .orElse(null);
   }

   @Override
   @Transactional
   public void createOrUpdateUser(User user) {
      if ((user.getId() == null) ||
              (!user.getPassword().equals(userRepository.findById(user.getId()).get().getPassword()))) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
      }
      userRepository.save(user);
   }

   @Override
   @Transactional
   public void deleteUser(Long id) {
      userRepository.deleteById(id);
   }
}
