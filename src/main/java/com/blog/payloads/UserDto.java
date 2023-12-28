package com.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4, message = "User name length must be of 4 char")
    private String name;
    @Email(message = "email address must be valid")
    private String email;
    @NotEmpty
    @Size(min = 5, max = 15, message = "pass length must be between 5 to 15")
    private String password;
    @NotEmpty
    private String about;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getAbout() {
//        return about;
//    }
//
//    public void setAbout(String about) {
//        this.about = about;
//    }
//
//    public UserDto() {
//    }
}
