package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.DailyRegime;
import it.academy.project.projectonspring.model.DailyRegimeModel;
import it.academy.project.projectonspring.service.DailyRegimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daily_regime")
public class DailyRegimeController {
    @Autowired
    private DailyRegimeService dailyRegimeService;

    @PostMapping
    public DailyRegime saveDailyRegime(@RequestBody DailyRegimeModel dailyRegimeModel){
        return dailyRegimeService.saveDailyRegime(dailyRegimeModel);
    }

    @GetMapping
    public List<DailyRegime> getAllDailyRegime(){
        return dailyRegimeService.getAllDailyRegime();
    }
    @GetMapping("/{id}")
    public DailyRegime getDailyRegimeById(@PathVariable Long id){
        return dailyRegimeService.getDailyRegimeById(id);
    }
    @GetMapping("/group/{id}")
    public List<DailyRegime> getDailyRegimeByGroupId(@PathVariable Long id){
        return dailyRegimeService.findAllByGroup_Id(id);
    }
    @GetMapping("/regime/{id}")
    public List<DailyRegime> getDailyRegimeByRegimeId(@PathVariable Long id){
        return dailyRegimeService.findAllByRegime_Id(id);
    }
    @GetMapping("/sort/{id}")
    public List<DailyRegime> getDailyRegimeByGroupIdOrderByRegime(@PathVariable Long id){
        return dailyRegimeService.findAllByGroup_IdOrderByRegimeAsc(id);
    }
    @DeleteMapping("/{id}")
    public DailyRegime deleteDailyRegime(@PathVariable Long id){
        return dailyRegimeService.deleteDailyRegime(id);
    }
    @PutMapping("/{id}")
    public DailyRegime updateDailyRegime(@RequestBody DailyRegimeModel dailyRegimeModel,@PathVariable Long id){
        return dailyRegimeService.updateDailyRegime(dailyRegimeModel,id);
    }



}
