package com.spring.projectsem4.payload;

import com.spring.projectsem4.model.User;
import com.spring.projectsem4.util.annotation.EmailConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class SignUpDTO {

    @NotBlank
    @Size(min = 4, max = 40)
    private String fullName;

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(max = 40)
    @EmailConstraint
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank
    @Size(max = 11)
    private String phone;

    @NotBlank
    private Date birthday;

    @NotBlank
    @Size(max = 100)
    private String address;

    public SignUpDTO(User result) {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
