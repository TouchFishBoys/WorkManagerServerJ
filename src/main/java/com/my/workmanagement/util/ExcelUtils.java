package com.my.workmanagement.util;

import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.exception.UnsupportedFileTypeException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ExcelUtils {
    private final static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    public static List<StudentDO> readFromExcel(File file) throws UnsupportedFileTypeException, FileNotFoundException {
        InputStream is = new FileInputStream(file);
        String filename = file.getName();
        return readFromExcel(is, filename);
    }

    public static List<StudentDO> readFromExcel(InputStream is, String filename) throws UnsupportedFileTypeException {
        List<StudentDO> result = new LinkedList<>();
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
                    StudentDO rowData = new StudentDO();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        if (row.getCell(0).getCellType() == CellType.STRING) {
                            rowData.setStudentNum(row.getCell(0).getStringCellValue());
                        } else if (row.getCell(0).getCellType() == CellType.NUMERIC) {
                            rowData.setStudentNum(String.valueOf(row.getCell(0).getNumericCellValue()));
                        }
                        rowData.setStudentName(row.getCell(1).getStringCellValue());
                        rowData.setStudentClass(row.getCell(2).getStringCellValue());
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

    public static List<StudentDO> readFromExcel(MultipartFile mf) throws UnsupportedFileTypeException, IOException {
        InputStream is = mf.getInputStream();
        String filename = mf.getOriginalFilename();
        return readFromExcel(is, filename);
    }

    public static <T> List<T> readFromExcel(InputStream is, ExcelParser<T> parser, int sheetNum)
            throws IOException {
        List<T> result = new LinkedList<>();
        try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(sheetNum);
            if (sheet == null) {
                logger.error("Sheet is empty");
                return result;
            }
            logger.info("Sheet has {} rows", sheet.getLastRowNum());

            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                T obj = parser.parse(row);
                if (obj != null) {
                    result.add(obj);
                }
            }
        } catch (IOException ioe) {
            logger.error(ioe.getLocalizedMessage());
            throw ioe;
        }
        return result;
    }
}
