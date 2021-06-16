package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Calendar;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.CalendarModel;

import java.util.List;

public interface CalendarService {
    List<Calendar> getAllCalendar();
    Calendar getCalendarById(Long id) throws ObjectsNotFoundException;
    Calendar deleteCalendar(Long id) throws ObjectsNotFoundException;
    Calendar updateCalendar(CalendarModel calendarModel, Long id) throws ObjectsNotFoundException;
    Calendar saveCalendar(Calendar calendar);
    Calendar saveCalendar(CalendarModel calendarModel) throws ObjectsNotFoundException;
    List<Calendar> findAllByGroup_Id(Long id);
    List<Calendar> findAllByChild_Id(Long id);
}
