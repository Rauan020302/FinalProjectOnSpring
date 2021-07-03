package it.academy.project.projectonspring.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.ToString;

@ToString
public enum Gender {
    MALE("Мальчик"),
    FEMALE("Девочка");

    String transfer;
    Gender(String transfer) {
        this.transfer = transfer;
    }

    public String getTransfer() {
        return transfer;
    }

    @JsonCreator
    public static Gender transferGender(String transfer){
        for (Gender gender : Gender.values()){
            if (gender.getTransfer().equals(transfer)){
                return gender;
            }
        }
        throw new IllegalArgumentException();
    }
}
