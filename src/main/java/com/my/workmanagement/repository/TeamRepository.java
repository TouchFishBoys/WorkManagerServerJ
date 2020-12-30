package com.my.workmanagement.repository;

import com.my.workmanagement.entity.FinalWorkDO;
import com.my.workmanagement.entity.TeamDO;
import com.my.workmanagement.entity.TopicDO;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<TeamDO,Integer> {
    TeamDO findByTeamId(Integer teamId);


}
