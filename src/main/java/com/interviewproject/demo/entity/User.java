package com.interviewproject.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Entity(name = "app_user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "seq_user", allocationSize=10)
    @Column(nullable = false)
    private Long id;

    @NotBlank(message = "Cannot be null")
    private String name;

    @NotBlank(message = "Cannot be null")
    private String firstName;

    private String lastName;

    @NotBlank(message = "Cannot be null")
    @Email(message = "Must be a valid email")
    private String email;

    @NotBlank(message = "Cannot be null")
    private String password;

    @Size(max= 255, message = "Size must not be greater then 255")
    private String address;

}