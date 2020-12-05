package com.my.workmanagement.util;

import com.my.workmanagement.exception.UnsupportedFileTypeException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ExcelUtils {
    private final static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    public static List<HashMap<String, Object>> readFromExcel(File file) throws UnsupportedFileTypeException, FileNotFoundException {
        InputStream is = new FileInputStream(file);
        String filename = file.getName();
        return readFromExcel(is, filename);
    }

    public static List<HashMap<String, Object>> readFromExcel(InputStream is, String filename) throws UnsupportedFileTypeException {
        List<HashMap<String, Object>> result = new LinkedList<>();
        List<String> headerList = new LinkedList<>();
        try {
            Workbook workbook;

            if (filename != null && filename.endsWith(".xls")) {
                workbook = new HSSFWorkbook(is);
            } else if (filename != null && filename.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(is);
            } else {
                logger.error("File name: {}", filename);
                throw new UnsupportedFileTypeException();
            }

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                logger.info("Sheet is empty {}", filename);
                return result;
            }
            logger.info("Sheet length is :" + sheet.getLastRowNum());

            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (i == sheet.getFirstRowNum()) {
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        // 获取表头
                        headerList.add(row.getCell(j).getStringCellValue());
                    }
                } else { // 不是表头
                    HashMap<String, Object> rowData = new HashMap<>();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        switch (row.getCell(j).getCellType()) {
                            case STRING:
                                rowData.put(headerList.get(j), row.getCell(j).getStringCellValue());
                                break;
                            case NUMERIC:
                                rowData.put(headerList.get(j), row.getCell(j).getNumericCellValue());
                                break;
                            case BOOLEAN:
                                rowData.put(headerList.get(j), row.getCell(j).getBooleanCellValue());
                                break;
                            case ERROR:
                                rowData.put(headerList.get(j), row.getCell(j).getErrorCellValue());
                                break;
                            default:
                                rowData.put(headerList.get(j), "");
                                break;
                        }
                    }
                    result.add(rowData);
                }
            }
            workbook.close();
            is.close();
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
        return result;
    }

    public static List<HashMap<String, Object>> readFromExcel(MultipartFile mf) throws UnsupportedFileTypeException, IOException {
        InputStream is = mf.getInputStream();
        String filename = mf.getOriginalFilename();
        return readFromExcel(is, filename);
    }
}
