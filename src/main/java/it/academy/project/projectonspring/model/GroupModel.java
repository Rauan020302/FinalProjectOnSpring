package it.academy.project.projectonspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class GroupModel {

    private String name;

    private Long kinderGardenId;

    private Long imageId;

    private String teacherFullName;

    private String info;
}
