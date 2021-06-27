package it.academy.project.projectonspring.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visits")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date",nullable = false)
    private LocalDate date;

    @Column(name = "visit")
    private Boolean visit;

    @OneToOne
    @JoinColumn(name = "child_id",nullable = false)
    private Child child;

//    @OneToOne
//    @JoinColumn(name = "group_id")
//    private Group group;

}
