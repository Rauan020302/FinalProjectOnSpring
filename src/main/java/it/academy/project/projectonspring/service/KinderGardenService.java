package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.KinderGarden;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.KinderGardenModel;

import java.util.List;

public interface KinderGardenService {
    List<KinderGarden> getAllKG();
    KinderGarden getKGById(Long id) throws ObjectsNotFoundException;
    KinderGarden saveKG(KinderGarden kinderGarden);
    KinderGarden saveKG(KinderGardenModel kinderGardenModel) throws ObjectsNotFoundException;
    KinderGarden deleteKGById(Long id) throws ObjectsNotFoundException;
    KinderGarden updateKGById(KinderGardenModel kinderGardenModel,Long id) throws ObjectsNotFoundException;
}
