package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Regime;

import java.util.List;

public interface RegimeService {
    List<Regime> getAllRegime();
    Regime saveRegime(Regime regime);
    Regime getRegimeById(Long id);
    Regime updateRegime(Regime regime, Long id);
    Regime deleteRegime(Long id);
}
