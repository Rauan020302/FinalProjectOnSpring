package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.DailyRegime;
import it.academy.project.projectonspring.entity.Group;
import it.academy.project.projectonspring.entity.Regime;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.model.DailyRegimeModel;
import it.academy.project.projectonspring.repository.DailyRegimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DailyRegimeServiceImpl implements DailyRegimeService {
    @Autowired
    private DailyRegimeRepository dailyRegimeRepo;
    @Autowired
    private RegimeService regimeService;
    @Autowired
    private GroupService groupService;

    @Override
    public DailyRegime saveDailyRegime(DailyRegimeModel dailyRegimeModel) {
        Regime regime = regimeService.getRegimeById(dailyRegimeModel.getRegimeId());
        Group group = groupService.getGroupById(dailyRegimeModel.getGroupId());

        try {
            if (regime == null || group == null) throw new ObjectsNotFoundException();

            DailyRegime dailyRegime = DailyRegime.builder()
                    .regime(regime)
                    .group(group).build();

            return saveDailyRegime(dailyRegime);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("regime or group not found ");
        }
    }

    @Override
    public DailyRegime updateDailyRegime(DailyRegimeModel dailyRegimeModel, Long id) {
        Regime regime = regimeService.getRegimeById(dailyRegimeModel.getRegimeId());
        Group group = groupService.getGroupById(dailyRegimeModel.getGroupId());
        try {
            if (regime == null || group == null) throw new ObjectsNotFoundException();

            DailyRegime dailyRegime = getDailyRegimeById(id);
            dailyRegime.setGroup(group);
            dailyRegime.setRegime(regime);

            return saveDailyRegime(dailyRegime);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found daily regime by id - " + id);
        }
    }

    @Override
    public DailyRegime deleteDailyRegime(Long id) {
        DailyRegime dailyRegime = getDailyRegimeById(id);

        if (dailyRegime != null){
            dailyRegimeRepo.delete(dailyRegime);
            return dailyRegime;
        }
        return null;
    }

    @Override
    public List<DailyRegime> findAllByGroup_Id(Long id) {
        return dailyRegimeRepo.findAllByGroup_Id(id);
    }

    @Override
    public List<DailyRegime> findAllByRegime_Id(Long id) {
        return dailyRegimeRepo.findAllByRegime_Id(id);
    }

    @Override
    public List<DailyRegime> findAllByGroup_IdOrderByRegimeAsc(Long group_id) {
        return dailyRegimeRepo.findAllByGroup_IdOrderByRegimeAsc(group_id);
    }

    @Override
    public List<DailyRegime> getAllDailyRegime() {
        return dailyRegimeRepo.findAll();
    }

    @Override
    public DailyRegime getDailyRegimeById(Long id) {
        return dailyRegimeRepo.findById(id).orElseThrow(() -> new ObjectsNotFoundException("not found daily regime by id - " + id ));
    }

    @Override
    public DailyRegime saveDailyRegime(DailyRegime dailyRegime) {
        return dailyRegimeRepo.save(dailyRegime);
    }
}
