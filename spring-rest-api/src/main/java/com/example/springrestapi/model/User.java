package com.example.springrestapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    @Min(18)
    @Max(99)
    @Column(nullable = false)
    private int age;

    @NotBlank(message = "email is mandatory")
    @Email
    @Column(unique = true, nullable = true)
    private String email;

    @NotBlank(message = "employeeId is mandatory")
    @Pattern(regexp = "[A-X]{4}//d{4}")
    @Column(nullable = false, unique = true)
    private String employeeId;

    @Size(min = 3, max = 50)
    @NotBlank(message = "firstname is mandatory")
    @Column(nullable = false)
    private String firstName;

    @Size(min = 3, max = 50)
    @Column(nullable = false)
    @NotBlank(message = "lastname is mandatory")
    private String lastName;
}
