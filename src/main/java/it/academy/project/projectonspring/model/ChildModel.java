package it.academy.project.projectonspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChildModel {
    private String fullName;

    private Integer age;

    private String gender;

    private Long groupId;

    private String parent;

    private Integer contact;

    private Long imageId;
}
