package it.academy.project.projectonspring.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "child")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name",nullable = false,unique = true)
    private String fullName;

    @Column(name = "age",nullable = false)
    private LocalDate age;

    @Column(name = "gender",nullable = false)
    private String gender;

    @ManyToOne
    @JoinColumn(name = "group_id",nullable = false)
    private Group group;

    @Column(name = "parent_full_name",nullable = false)
    private String parent;

    @Column(name = "contacts",nullable = false)
    private Integer contact;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

}
