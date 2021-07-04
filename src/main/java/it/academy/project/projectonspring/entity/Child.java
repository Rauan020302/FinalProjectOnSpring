package it.academy.project.projectonspring.entity;

import it.academy.project.projectonspring.enums.Gender;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "childs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Column(name = "birth_day",nullable = false)
    private LocalDate birthDay;

    @Column(name = "gender",nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "parent_full_name",nullable = false)
    private String parent;

    @Column(name = "contacts",nullable = false)
    private Integer contact;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

}
