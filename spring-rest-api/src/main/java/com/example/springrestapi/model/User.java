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
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Firstname is mandatory")
    @Size(min = 2, max = 50)
    @Column(name = "first_name", nullable = false, length = 50)
    private  String firstName;

    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 2, max = 50)
    @Column(name = "last_name", nullable = false, length = 50)
    private  String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email
    @Column(nullable = false, unique = true)
    private  String email;

    @NotNull(message = "Age is mandatory")
    @Min(18)
    @Max(99)
    @Column(nullable = false)
    private  Integer age;

    @Pattern(regexp = "[A-Z]{4}\\d{4}")
    @Column(name = "employee_id")
    private String employeeId;
}