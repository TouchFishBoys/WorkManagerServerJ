package com.my.workmanagement.repository;

import com.my.workmanagement.entity.FinalWorkDO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FinalWorkRepository extends CrudRepository<FinalWorkDO, Integer> {
    boolean existsByFworkId(Integer fworkId);

    @Modifying
    @Query("UPDATE FinalWorkDO fw SET fw.fworkScore = :score WHERE fw.fworkId = :finalWorkId AND fw.fworkScore IS NULL")
    int setScoreByFinalWorkId(Integer finalWorkId, Integer score);

    @Modifying
    @Query("UPDATE FinalWorkDO fw SET fw.documentScore = :score WHERE fw.fworkId = :finalWorkId AND fw.documentScore IS NULL")
    int setDocumentScoreByFinalWorkId(Integer finalWorkId, Integer score);
}
