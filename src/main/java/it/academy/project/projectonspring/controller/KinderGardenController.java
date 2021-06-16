package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.KinderGarden;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.KinderGardenModel;
import it.academy.project.projectonspring.service.KinderGardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garden")
public class KinderGardenController {
    @Autowired
    private KinderGardenService kinderGardenService;

    @PostMapping
    private KinderGarden saveKG(@RequestBody KinderGardenModel kinderGardenModel) throws ObjectsNotFoundException {
        return kinderGardenService.saveKG(kinderGardenModel);
    }
    @GetMapping
    public List<KinderGarden> getALlKG(){
        return kinderGardenService.getAllKG();
    }
    @GetMapping("/{id}")
    public KinderGarden getKGById(@PathVariable Long id) throws ObjectsNotFoundException {
        return kinderGardenService.getKGById(id);
    }
    @DeleteMapping("/{id}")
    public KinderGarden deleteKGById(@PathVariable Long id) throws ObjectsNotFoundException {
        return kinderGardenService.deleteKGById(id);
    }
    @PutMapping("/{id}")
    public KinderGarden updateKGById(@RequestBody KinderGardenModel kinderGardenModel,
                                     @PathVariable Long id) throws ObjectsNotFoundException {
        return kinderGardenService.updateKGById(kinderGardenModel,id);
    }
}
