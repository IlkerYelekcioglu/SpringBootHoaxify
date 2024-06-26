package com.hoaxify.ws.user;

import com.hoaxify.ws.email.EmailService;
import com.hoaxify.ws.user.exception.ActivationNotificationException;
import com.hoaxify.ws.user.exception.NotFoundException;
import com.hoaxify.ws.user.exception.NotUniqueEmailException;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    EmailService emailService;

    @Transactional(rollbackOn = {MailException.class})
    public void save(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.saveAndFlush(user);
            emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());
        }catch (DataIntegrityViolationException exception){
            throw new NotUniqueEmailException();
        }catch (MailException exception){
            throw new ActivationNotificationException();
        }

    }


  public void activateUser(String token) {
        User inDB = userRepository.findByActivationToken(token);
        if(inDB == null){
            throw new ActivationNotificationException();
        }else {
            inDB.setActive(true);
            inDB.setActivationToken(null);
        }
  }

  public Page<User> getUsers(Pageable page) {
      return userRepository.findAll(page);
  }

  public User getUser(long id) {
      return userRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
  }

  public User findByEmail(String email) {
     return userRepository.findByEmail(email);
    }
}
