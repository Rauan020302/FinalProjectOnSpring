package it.academy.project.projectonspring.entity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login",nullable = false,unique = true)
    private String username;

    @Column(name = "fullName",nullable = false)
    private String fullName;

    @Column(name = "user_password",nullable = false,unique = true)
    private String password;

    @Column(name = "status")
    private Long status;

    @Column(name = "contact")
    private Integer contact;

    @Column(name = "profession")
    private String profession;

    @Column(name = "address")
    private String address;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

}

