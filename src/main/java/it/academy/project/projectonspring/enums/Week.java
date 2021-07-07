package it.academy.project.projectonspring.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.ToString;

@ToString
public enum Week {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    String transfer;
    Week(String transfer) {
        this.transfer = transfer;
    }

    public String getTransfer() {
        return transfer;
    }
    @JsonCreator
    public static Week transferWeek(String transfer){
        for (Week week : Week.values()){
            if (week.getTransfer().equals(transfer)){
                return week;
            }
        }
        throw new IllegalArgumentException();
    }
}
