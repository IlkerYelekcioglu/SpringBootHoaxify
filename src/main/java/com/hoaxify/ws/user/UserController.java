package com.hoaxify.ws.user;

import com.hoaxify.ws.shared.GenericMessage;
import com.hoaxify.ws.shared.Messages;
import com.hoaxify.ws.user.dto.UserCreate;
import com.hoaxify.ws.user.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
   UserService userService;

  @PostMapping("/api/v1/users")
   GenericMessage createUser(@Valid @RequestBody UserCreate user) {
    userService.save(user.toUser());
    String message = Messages.getMessageForLocale("hoaxify.create.user.success.message", LocaleContextHolder.getLocale());
    return new GenericMessage(message);
  }
  @PatchMapping("api/v1/users/{token}/active")
  GenericMessage activateUser(@PathVariable String token) {
    userService.activateUser(token);
    String message = Messages.getMessageForLocale("hoaxify.activate.user.success.message", LocaleContextHolder.getLocale());
    return new GenericMessage(message);
  }

  @GetMapping("/api/v1/users")
  Page<UserDTO> getUsers(Pageable pageable){
    return  userService.getUsers(pageable).map(UserDTO::new);
  }
@GetMapping("/api/v1/users/{id}")
UserDTO getUserById(@PathVariable long id) {
    return new UserDTO(userService.getUser(id));
}


}
