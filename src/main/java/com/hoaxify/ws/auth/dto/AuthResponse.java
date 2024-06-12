package com.hoaxify.ws.auth.dto;

import com.hoaxify.ws.auth.dto.token.Token;
import com.hoaxify.ws.user.dto.UserDTO;

public class AuthResponse {
UserDTO user;

Token token;

  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }

  public Token getToken() {
    return token;
  }

  public void setToken(Token token) {
    this.token = token;
  }
}
