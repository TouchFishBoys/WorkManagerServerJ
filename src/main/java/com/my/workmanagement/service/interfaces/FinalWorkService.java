package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.payload.response.finalWork.FinalWorkInfoResponse;
import org.springframework.stereotype.Service;

@Service
public interface FinalWorkService {
    FinalWorkInfoResponse getFinalWorkInfo(Integer teamId) throws IdNotFoundException;
}
