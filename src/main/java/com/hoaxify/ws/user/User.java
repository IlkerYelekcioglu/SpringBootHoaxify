package com.hoaxify.ws.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hoaxify.ws.auth.token.Token;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.List;


@Entity
@Table(name = "USERS",uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {

    @Id
    @GeneratedValue
    long id;

    String username;

    String email;

    @JsonIgnore
    String password;

    @JsonIgnore
    boolean active = false;

    @JsonIgnore
    String activationToken;

    @Lob
    String image;

    String passwordResetToken;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    List<Token> tokens;


    /**
     * Görünmesini istemediğimiz fieldların tepesine JsonIgnore annotation ı kullanırız.
     *
     */

    /*
    Blob lobları araştır
     */


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
}
