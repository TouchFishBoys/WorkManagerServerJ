package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.exception.StorageFileNotFoundException;
import com.my.workmanagement.exception.WordTemplateNotFoundException;
import com.my.workmanagement.model.bo.FinalWorkBO;
import com.my.workmanagement.model.bo.QaTableBO;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public interface FinalWorkService {
    FinalWorkBO getFinalWorkInfo(Integer teamId) throws IdNotFoundException;

    /**
     * 设置大作业成绩
     *
     * @param finalWork 大作业Id
     * @param score     大作业成绩
     * @return 是否成功
     * @throws IdNotFoundException 没找到大作业
     */
    boolean setFinalWorkScore(Integer finalWork, Integer score) throws IdNotFoundException;

    void setQAScore(Integer courseId, Integer studentId, Integer qaScore);

    boolean setDocumentScore(Integer finalWork, Integer score) throws IdNotFoundException;

    Resource loadDocumentByFworkId(Integer finalWorkId) throws FileNotFoundException, StorageFileNotFoundException;

    Resource loadFworkFileByFworkId(Integer finalWorkId) throws StorageFileNotFoundException;

    boolean generateQaTable(List<QaTableBO.QaTableItemBO> items, Integer courseId, Integer studentId, String location, Integer score) throws WordTemplateNotFoundException, IOException, IdNotFoundException;
}
