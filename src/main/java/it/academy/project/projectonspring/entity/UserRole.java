package it.academy.project.projectonspring.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name",nullable = false)
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
