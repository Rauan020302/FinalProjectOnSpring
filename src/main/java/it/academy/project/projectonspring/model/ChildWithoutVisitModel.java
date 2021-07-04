package it.academy.project.projectonspring.model;

import it.academy.project.projectonspring.entity.Child;
import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.entity.Image;
import it.academy.project.projectonspring.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChildWithoutVisitModel extends Child {
    private Long id;

    private String fullName;

    private LocalDate birthDay;

    private Gender gender;

    private Group group;

    private String parent;

    private Integer contact;

    private Image image;
}
