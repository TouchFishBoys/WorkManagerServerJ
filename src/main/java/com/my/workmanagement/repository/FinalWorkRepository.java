package com.my.workmanagement.repository;

import com.my.workmanagement.entity.FinalWorkDO;
import com.my.workmanagement.entity.TeamDO;
import org.springframework.data.repository.CrudRepository;

public interface FinalWorkRepository extends CrudRepository<FinalWorkDO,Integer> {
    FinalWorkDO findFinalWorkDOByTeamId(TeamDO teamDO);

}
