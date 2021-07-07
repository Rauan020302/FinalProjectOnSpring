package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.DailyRegime;
import it.academy.project.projectonspring.model.DailyRegimeModel;

import java.util.List;

public interface DailyRegimeService {
    List<DailyRegime> getAllDailyRegime();
    DailyRegime saveDailyRegime(DailyRegimeModel dailyRegimeModel);
    DailyRegime getDailyRegimeById(Long id);
    DailyRegime saveDailyRegime(DailyRegime dailyRegime);
    DailyRegime updateDailyRegime(DailyRegimeModel dailyRegimeModel, Long id);
    DailyRegime deleteDailyRegime(Long id);
    List<DailyRegime> findAllByGroup_Id(Long id);
    List<DailyRegime> findAllByRegime_Id(Long id);
    List<DailyRegime> findAllByGroup_IdOrderByRegimeAsc(Long group_id);

}
