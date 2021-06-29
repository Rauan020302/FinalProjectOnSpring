package it.academy.project.projectonspring.controller;

import it.academy.project.projectonspring.entity.Regime;
import it.academy.project.projectonspring.service.RegimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regime")
public class RegimeController {
    @Autowired
    private RegimeService regimeService;

    @PostMapping
    public Regime saveRegime(@RequestBody Regime regime){
        return regimeService.saveRegime(regime);
    }
    @GetMapping
    public List<Regime> getAllRegime(){
        return regimeService.getAllRegime();
    }
    @GetMapping("/{id}")
    public Regime getRegimeById(@PathVariable Long id){
        return regimeService.getRegimeById(id);
    }
    @DeleteMapping("/{id}")
    public Regime deleteRegime(@PathVariable Long id){
        return regimeService.deleteRegime(id);
    }
    @PutMapping("/{id}")
    public Regime updateRegime(@RequestBody Regime regime, @PathVariable Long id){
        return regimeService.updateRegime(regime,id);
    }
}
