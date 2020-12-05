package com.my.workmanagement;

import com.deepoove.poi.XWPFTemplate;
import com.my.workmanagement.util.ExcelUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PoiTemplateTest {
    @Test
    void testPoi() throws IOException {
        try {
            XWPFTemplate.compile("C:\\Users\\Kerit\\Desktop\\test.docx").render(new HashMap<String, Object>() {
                {
                    put("title", "this is title");
                    put("Normal", "Hello world");
                }
            }).writeToFile("C:\\Users\\Kerit\\Desktop\\test_out.docx");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void testReadExcel() {
        try {
            var list = ExcelUtils.readFromExcel(new File("C:\\Users\\Kerit\\Desktop\\test.xlsx"));
            for (HashMap<String, Object> map : list) {
                System.out.println(map.toString());
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void testExportExcel() {
        File outputFile = new File("C:\\Users\\Kerit\\Desktop\\test_out.xlsx");


    }
}
