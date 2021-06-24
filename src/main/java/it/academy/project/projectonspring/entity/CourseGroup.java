package it.academy.project.projectonspring.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "course_group")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CourseGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne()
    @JoinColumn(name = "group_id")
    private Group group;
}
