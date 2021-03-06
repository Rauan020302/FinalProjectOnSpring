package it.academy.project.projectonspring.model;

import it.academy.project.projectonspring.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChildModel {
    private String fullName;

    private LocalDate birthDay;

    private Gender gender;

    private Long groupId;

    private String parent;

    private Integer contact;

    private Long imageId;
}
