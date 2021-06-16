package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Calendar;
import it.academy.project.projectonspring.entity.Child;
import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.entity.User;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.CalendarModel;
import it.academy.project.projectonspring.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ChildService childService;
    @Autowired
    private UserService userService;

    @Override
    public List<Calendar> getAllCalendar(){
        return calendarRepository.findAll();
    }

    @Override
    public it.academy.project.projectonspring.entity.Calendar getCalendarById(Long id) throws ObjectsNotFoundException {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Calendar calendar = calendarRepository.findById(id).orElse(null);
        Long groupId = calendar.getGroup().getId();
        if (!calendar.getGroup().getKinderGarden().getUser().getUsername().equals(user.getUsername()) || groupId != id){
            throw new ObjectsNotFoundException("not found");
        }
        return calendarRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("No calendar by Id: " + id));
    }

    @Override
    public Calendar deleteCalendar(Long id) throws ObjectsNotFoundException {
        Calendar calendar = getCalendarById(id);
        if (calendar != null){
            calendarRepository.delete(calendar);
            return calendar;
        }
        throw new ObjectsNotFoundException("No calendar by Id: " + id);
    }

    @Override
    public Calendar updateCalendar(CalendarModel calendarModel, Long id) throws ObjectsNotFoundException {
        Calendar calendar = getCalendarById(id);
        Child child = childService.getChildById(calendarModel.getChildId());
        Group group = groupService.getGroupById(calendarModel.getGroupId());
        if (calendar == null || child == null || group == null)throw new ObjectsNotFoundException("calendar,child or group not found");

        calendar.setChild(child);
        calendar.setGroup(group);
        calendar.setDate(calendarModel.getDate());
        calendar.setVisit(calendarModel.getVisit());
        return calendarRepository.save(calendar);
    }

    @Override
    public Calendar saveCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    @Override
    public Calendar saveCalendar(CalendarModel calendarModel) throws ObjectsNotFoundException {
        Child child = childService.getChildById(calendarModel.getChildId());
        Group group = groupService.getGroupById(calendarModel.getGroupId());
        if (child == null || group == null)throw new ObjectsNotFoundException("child or group not found");

        Calendar calendar = Calendar.builder()
                .child(child)
                .group(group)
                .visit(calendarModel.getVisit())
                .date(calendarModel.getDate()).build();
        return calendarRepository.save(calendar);
    }

    @Override
    public List<Calendar> findAllByGroup_Id(Long id) {
        return calendarRepository.findAllByGroup_Id(id);
    }

    @Override
    public List<Calendar> findAllByChild_Id(Long id) {
        return calendarRepository.findAllByChild_Id(id);
    }
}

