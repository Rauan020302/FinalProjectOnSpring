package it.academy.project.projectonspring.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "regime")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Regime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_time")
    private Time timeStart;

    @Column(name = "end_time")
    private Time timeEnd;
}
