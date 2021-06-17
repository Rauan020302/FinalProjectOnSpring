package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.KinderGarden;
import it.academy.project.projectonspring.model.KinderGardenModel;

import java.util.List;

public interface KinderGardenService {
    List<KinderGarden> getAllKG();
    KinderGarden getKGById(Long id);
    KinderGarden saveKG(KinderGarden kinderGarden);
    KinderGarden saveKG(KinderGardenModel kinderGardenModel);
    KinderGarden deleteKGById(Long id);
    KinderGarden updateKGById(KinderGardenModel kinderGardenModel,Long id);
}
