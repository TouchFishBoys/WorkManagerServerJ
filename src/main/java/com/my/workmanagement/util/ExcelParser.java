package com.my.workmanagement.util;

import org.apache.poi.ss.usermodel.Row;

public interface ExcelParser<T> {
    T parse(Row row);
}
