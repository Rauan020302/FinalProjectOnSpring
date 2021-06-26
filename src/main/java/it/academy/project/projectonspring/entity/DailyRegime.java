package it.academy.project.projectonspring.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "daily_regime")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class DailyRegime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id",nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "regime_id",nullable = false)
    private Regime regime;
}
