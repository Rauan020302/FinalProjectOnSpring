package it.academy.project.projectonspring.service;

import it.academy.project.projectonspring.entity.Regime;
import it.academy.project.projectonspring.exception.ObjectsNotFoundException;
import it.academy.project.projectonspring.repository.RegimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegimeServiceImpl implements RegimeService {
    @Autowired
    private RegimeRepository regimeRepository;

    @Override
    public Regime updateRegime(Regime regime, Long id) {
        Regime newRegime = getRegimeById(id);
        try {
            if (newRegime == null) throw new ObjectsNotFoundException();

            newRegime.setName(regime.getName());
            newRegime.setTimeEnd(regime.getTimeEnd());
            newRegime.setTimeStart(regime.getTimeStart());

            return saveRegime(newRegime);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found regime by id - " + id);
        }
    }

    @Override
    public Regime deleteRegime(Long id) {
        Regime regime = getRegimeById(id);
        if (regime != null){
            regimeRepository.delete(regime);
            return regime;
        }
        return null;
    }

    @Override
    public List<Regime> getAllRegime() {
        return regimeRepository.findAll();
    }

    @Override
    public Regime saveRegime(Regime regime) {
        return regimeRepository.save(regime);
    }

    @Override
    public Regime getRegimeById(Long id) {
        return regimeRepository.findById(id).orElseThrow(() -> new ObjectsNotFoundException("not found regime by id " + id));
    }
}
