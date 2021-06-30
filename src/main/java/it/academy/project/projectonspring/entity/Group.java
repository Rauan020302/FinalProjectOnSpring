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

    @Column(name = "full_name_teacher")
    private String teacherFullName;

    @Column(name = "info",length = 800)
    private String info;

    @ManyToOne
    @JoinColumn(name = "kinder_id",nullable = false)
    private KinderGarden kinderGarden;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

}
