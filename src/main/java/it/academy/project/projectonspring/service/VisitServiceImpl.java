package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.entity.Visit;
import it.academy.project.projectonspring.entity.Child;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.MonthModel;
import it.academy.project.projectonspring.model.VisitModel;
import it.academy.project.projectonspring.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ChildService childService;

    @Override
    public Visit getVisitById(Long id) {
//        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        Visit visit = visitRepository.findById(id).orElse(null);
//        Long groupId = visit.getGroup().getId();
//        if (!visit.getGroup().getKinderGarden().getUser().getUsername().equals(user.getUsername()) || !groupId.equals(id)){
//            throw new ObjectsNotFoundException("not found");
//        }
        return visitRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("No visits by Id: " + id));
    }

    @Override
    public Visit deleteVisit(Long id) {
        Visit visit = getVisitById(id);
        if (visit != null){
            visitRepository.delete(visit);
            return visit;
        }
        throw new ObjectsNotFoundException("No visits by Id: " + id);
    }

    @Override
    public Visit updateVisit(VisitModel visitModel, Long id)  {
        Child child = childService.getChildById(visitModel.getChildId());
        Group group = groupService.getGroupById(visitModel.getGroupId());
        try {
            if (child == null || group == null) throw new ObjectsNotFoundException();

            Visit visit = getVisitById(id);
            visit.setChild(child);
            visit.setGroup(group);
            visit.setDate(visitModel.getDate());
            visit.setVisit(visitModel.getVisit());

            return visitRepository.save(visit);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found visits by id - " + id);
        }

    }

    @Override
    public List<Visit> findAllByGroup_IdAndMonth(MonthModel monthModel) {
        List<Visit> visits = visitRepository.findAllByGroup_Id(monthModel.getGroupId());
        List<Visit> newVisits = new ArrayList<>();
        try {
            if (visits.size() == 0) throw new ObjectsNotFoundException();

            for (Visit visit : visits) {
                int monthValue = visit.getDate().getMonthValue();
                if (monthValue == monthModel.getMonth()) {

                    newVisits.add(visit);
                }
            }
            return newVisits;
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found Visits by GroupId - " + monthModel.getGroupId());
        }
    }

    @Override
    public List<VisitModel> saveVisit(List<VisitModel> visitModels){
        for (VisitModel v :visitModels) {
            Child child = childService.getChildById(v.getChildId());
            Group group = groupService.getGroupById(v.getGroupId());
            try {
                if (child == null || group == null) throw new ObjectsNotFoundException();
                Visit visit = Visit.builder()
                        .visit(v.getVisit())
                        .date(v.getDate())
                        .group(group)
                        .child(child).build();
                visitRepository.save(visit);
            }catch (ObjectsNotFoundException e){
                throw new ObjectsNotFoundException("child or group not found");
            }
        }
        return visitModels;
    }

    @Override
    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public List<Visit> getAllVisits(){
        return visitRepository.findAll();
    }

    @Override
    public List<Visit> findAllByGroup_Id(Long id) {
        return visitRepository.findAllByGroup_Id(id);
    }

    @Override
    public List<Visit> findAllByChild_Id(Long id) {
        return visitRepository.findAllByChild_Id(id);
    }
}

