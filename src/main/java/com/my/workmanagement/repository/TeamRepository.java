package com.my.workmanagement.repository;

import com.my.workmanagement.entity.TeamDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<TeamDO, Integer> {
    TeamDO findByTeamId(Integer teamId);

    @Query("SELECT COUNT(tdo) FROM TeamDO AS tdo, FinalWorkDO fwork WHERE fwork.timeUpload IS NOT NULL AND tdo IN :teams")
    Integer countFinishedTeam(List<TeamDO> teams);

    @Query("SELECT count(team) FROM TeamDO AS team LEFT OUTER JOIN team.finalWork where team.finalWork.timeUpload IS NOT NULL AND team IN :teams")
    Integer getFinishedTeams(List<TeamDO> teams);

    TeamDO getByFinalWork_FworkId(Integer fworkId);
}
