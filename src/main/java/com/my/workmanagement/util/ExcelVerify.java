package com.my.workmanagement.util;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;

public interface ExcelVerify {
    boolean verify(Row row);
}
