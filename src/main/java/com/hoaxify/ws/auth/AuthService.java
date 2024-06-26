package com.hoaxify.ws.auth;

import com.hoaxify.ws.auth.dto.AuthResponse;
import com.hoaxify.ws.auth.dto.Credentials;
import com.hoaxify.ws.auth.dto.token.Token;
import com.hoaxify.ws.auth.dto.token.TokenService;
import com.hoaxify.ws.auth.exception.AuthenticationException;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserService;
import com.hoaxify.ws.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  @Autowired
  UserService userService;

  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  TokenService tokenService;

  public AuthResponse authenticate(Credentials creds) {
    User inDB = userService.findByEmail(creds.email());
    if(inDB == null)
      throw new AuthenticationException();
    if(!passwordEncoder.matches(creds.password(), inDB.getPassword())) throw new AuthenticationException();

    Token token = tokenService.createToken(inDB,creds);
    AuthResponse authResponse = new AuthResponse();
    authResponse.setToken(token);
    authResponse.setUser(new UserDTO(inDB));
    return authResponse;
  }
}
