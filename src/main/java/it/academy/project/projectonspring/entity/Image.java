package it.academy.project.projectonspring.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "format",nullable = false)
    private String format;

    @Column(name = "url",nullable = false)
    private String url;

    @Column(name = "name",nullable = false)
    private String name;

}
