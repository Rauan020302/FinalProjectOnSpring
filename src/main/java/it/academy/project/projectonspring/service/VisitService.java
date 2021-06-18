package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Visit;
import it.academy.project.projectonspring.model.VisitModel;

import java.util.List;

public interface VisitService {
    List<Visit> getAllVisits();
    Visit getVisitById(Long id);
    Visit deleteVisit(Long id);
    Visit updateVisit(VisitModel visitModel, Long id);
    Visit saveVisit(Visit visit);
    Visit saveVisit(VisitModel visitModel) ;
    List<Visit> findAllByGroup_Id(Long id);
    List<Visit> findAllByChild_Id(Long id);
}
