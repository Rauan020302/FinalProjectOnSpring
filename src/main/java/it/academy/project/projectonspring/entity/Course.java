package it.academy.project.projectonspring.entity;

import it.academy.project.projectonspring.enums.Week;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "week")
    private Week week;

    @Column(name = "time_start")
    private Time timeStart;

    @Column(name = "time_end")
    private Time timeEnd;


}
