package it.academy.project.projectonspring.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "kinder_garden")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class KinderGarden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "number")
    private Integer number;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private Integer contact;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;


}
