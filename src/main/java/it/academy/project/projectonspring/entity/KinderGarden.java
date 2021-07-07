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

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description",length = 1024,nullable = false)
    private String description;

    @Column(name = "address",nullable = false,unique = true)
    private String address;

    @Column(name = "contact",nullable = false)
    private Integer contact;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;





}
