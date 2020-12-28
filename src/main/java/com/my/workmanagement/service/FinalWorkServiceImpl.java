package com.my.workmanagement.service;

import com.my.workmanagement.entity.FinalWorkDO;
import com.my.workmanagement.entity.TeamDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.payload.response.finalwork.FinalWorkInfoResponse;
import com.my.workmanagement.repository.FinalWorkRepository;
import com.my.workmanagement.repository.TeamRepository;
import com.my.workmanagement.service.interfaces.FinalWorkService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FinalWorkServiceImpl implements FinalWorkService {
    private FinalWorkRepository finalWorkRepository;
    private TeamRepository teamRepository;

    FinalWorkServiceImpl(FinalWorkRepository finalWorkRepository, TeamRepository teamRepository) {
        this.finalWorkRepository = finalWorkRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public FinalWorkInfoResponse getFinalWorkInfo(Integer teamId) throws IdNotFoundException {
        TeamDO teamDO=teamRepository.findByTeamId(teamId);
        if(teamDO==null) {
            throw new IdNotFoundException("team");
        }
        FinalWorkDO finalWorkDO = finalWorkRepository.findFinalWorkDOByTeamId(teamDO);
        if(finalWorkDO==null) {
            throw new IdNotFoundException("finalWork");
        }
        return FinalWorkInfoResponse.FinalWorkInfoResponseBuilder.aFinalWorkInfoResponse()
                .withFworkId(finalWorkDO.getFworkId())
                .withFworkName(finalWorkDO.getFworkName())
                .withFworkDescreption(finalWorkDO.getFworkDescription())
                .withFworkScore(finalWorkDO.getFworkScore())
                .withTeamId(teamDO.getTeamId())
                .withTeamName(teamDO.getTeamName())
                .withTimeUpload(finalWorkDO.getTimeUpload())
                .build();
    }

    @Override
    @Transactional
    public boolean setFinalWorkScore(Integer finalWork, Integer score) throws IdNotFoundException {
        if (!finalWorkRepository.existsById(finalWork)) {
            throw new IdNotFoundException("finalWorkId");
        }
        return finalWorkRepository.setScoreByFinalWorkId(finalWork, score);
    }
}
