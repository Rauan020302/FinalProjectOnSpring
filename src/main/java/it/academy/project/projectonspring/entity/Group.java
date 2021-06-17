package it.academy.project.projectonspring.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "kinder_id",nullable = false)
    private KinderGarden kinderGarden;

    @Column(name = "full_name_teacher")
    private String teacherFullName;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "info")
    private String info;
}
