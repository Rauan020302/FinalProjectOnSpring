package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Visit;
import it.academy.project.projectonspring.entity.Child;
import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.entity.User;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.VisitModel;
import it.academy.project.projectonspring.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ChildService childService;
    @Autowired
    private UserService userService;

    @Override
    public List<Visit> getAllVisits(){
        return visitRepository.findAll();
    }

    @Override
    public Visit getVisitById(Long id) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Visit visit = visitRepository.findById(id).orElse(null);
        Long groupId = visit.getGroup().getId();
        if (!visit.getGroup().getKinderGarden().getUser().getUsername().equals(user.getUsername()) || groupId != id){
            throw new ObjectsNotFoundException("not found");
        }
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
    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit saveVisit(VisitModel visitModel){
        Child child = childService.getChildById(visitModel.getChildId());
        Group group = groupService.getGroupById(visitModel.getGroupId());
        try {
            if (child == null || group == null) throw new ObjectsNotFoundException();

            Visit visit = Visit.builder()
                    .child(child)
                    .group(group)
                    .visit(visitModel.getVisit())
                    .date(visitModel.getDate()).build();
            return visitRepository.save(visit);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("child or group not found");
        }
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

