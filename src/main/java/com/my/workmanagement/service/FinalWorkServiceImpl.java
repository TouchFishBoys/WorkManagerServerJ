package com.my.workmanagement.service;

import com.my.workmanagement.entity.FinalWorkDO;
import com.my.workmanagement.entity.TeamDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.FinalWorkBO;
import com.my.workmanagement.payload.response.finalwork.FinalWorkInfoResponse;
import com.my.workmanagement.repository.FinalWorkRepository;
import com.my.workmanagement.repository.TeamRepository;
import com.my.workmanagement.service.interfaces.FinalWorkService;
import com.my.workmanagement.service.interfaces.TeamService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FinalWorkServiceImpl implements FinalWorkService {
    private FinalWorkRepository finalWorkRepository;
    private TeamRepository teamRepository;
    private final TeamService teamService;

    FinalWorkServiceImpl(
            FinalWorkRepository finalWorkRepository,
            TeamRepository teamRepository,
            TeamService teamService
    ) {
        this.finalWorkRepository = finalWorkRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    @Override
    public FinalWorkBO getFinalWorkInfo(Integer teamId) throws IdNotFoundException {
        TeamDO team = teamRepository.findByTeamId(teamId);
        if (team == null) {
            // 没找到队伍
            throw new IdNotFoundException("team");
        }
        FinalWorkDO finalWork = finalWorkRepository.findFinalWorkDOByTeamId(team);
        if (finalWork == null) {
            throw new IdNotFoundException("finalWork");
        }
        return FinalWorkBO.FinalWorkBOBuilder.aFinalWorkBO()
                .withFinalWorkId(finalWork.getFworkId())
                .withFinalWorkName(finalWork.getFworkName())
                .withTeamName(team.getTeamName())
                .withSubmitTime(finalWork.getTimeUpload())
                .withDescription(finalWork.getFworkDescription())
                .withAuthors(teamService.getTeamMembers(teamId))
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
