package it.academy.project.projectonspring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class UserModel {

    private String username;

    private String fullName;

    private String password;

    private Long status;

    private Integer contact;

    private String profession;

    private String address;

    private LocalDateTime createdDate = LocalDateTime.now();

    private Long imageId;
}
