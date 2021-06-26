package it.academy.project.projectonspring.enums;


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
}
