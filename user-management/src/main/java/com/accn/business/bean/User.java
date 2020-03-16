package com.accn.business.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    private Long userId;

    @NotEmpty (message = "First Name is mandatory")
    @Size(min=5, max=20)
    private String firstName;

    @NotEmpty (message = "Last Name is mandatory")
    @Size(min=5, max=30)
    private String lastName;

    @Pattern(regexp = "[0-9]{10}")
    private String telephone;

    @NotEmpty(message = "Email is mandatory")
    @Email
    private String email;

    public User() {
    }

    public User(Long userId, String firstName, String lastName, String telephone, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
