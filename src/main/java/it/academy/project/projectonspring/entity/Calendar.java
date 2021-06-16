package it.academy.project.projectonspring.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "calendars")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "visit")
    private Boolean visit;

    @OneToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

}
