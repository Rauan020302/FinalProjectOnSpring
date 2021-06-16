package it.academy.project.projectonspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class KinderGardenModel {

    private String name;

    private String description;

    private Integer number;

    private String address;

    private Integer contact;

    private Long userId;

    private Long imageId;
}
