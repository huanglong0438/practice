package practice.excel;

import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class ExcelTest {

    @Test
    public void testExcelWrite() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        FileInputStream file = new FileInputStream("/Users/donglongcheng01/Downloads/migrate_app_template.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        for (int rowNo = 2; rowNo < 8000; rowNo++) {
            Row row = sheet.getRow(rowNo);
            if (row == null) {
                row = sheet.createRow(rowNo);
            }
            for (int col = 0; col <= 10; col++) {
                row.createCell(col).setCellValue(UUID.randomUUID().toString());
            }
        }
        FileOutputStream outFile = new FileOutputStream("/Users/donglongcheng01/Downloads/migrate_app_template2.xlsx");
        workbook.write(outFile);
        workbook.close();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    /**
     * http://poi.apache.org/components/spreadsheet/how-to.html#sxssf
     */
    @Test
    public void testStreamExcelWrite() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("read");
        FileInputStream file = new FileInputStream("/Users/donglongcheng01/Downloads/migrate_app_template.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
        SXSSFWorkbook workbook = new SXSSFWorkbook(xssfWorkbook);
        Sheet sheet = workbook.getSheetAt(0);
        for (int rowNo = 2; rowNo < 8000; rowNo++) {
            Row row = sheet.getRow(rowNo);
            if (row == null) {
                row = sheet.createRow(rowNo);
            }
            for (int col = 0; col <= 10; col++) {
                row.createCell(col).setCellValue(UUID.randomUUID().toString());
            }
        }
        stopWatch.stop();
        stopWatch.start("write");
        FileOutputStream outFile = new FileOutputStream("/Users/donglongcheng01/Downloads/migrate_app_template2.xlsx");
        workbook.write(outFile);
        workbook.close();
        workbook.dispose();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void encodingToBase64() {
        String path = "/Users/donglongcheng01/Downloads/migrate_app.csv"; // /Users/donglongcheng01/Downloads/migrate_app_template2.xlsx
        File fd = new File(path);
        try (FileInputStream file = new FileInputStream(fd)) {
            byte fileData[] = new byte[(int) fd.length()];
            file.read(fileData);
            String base64 = Base64.getEncoder().encodeToString(fileData);
            try (FileOutputStream outFile = new FileOutputStream("/Users/donglongcheng01/Downloads/base64")) {
                for (int i = 0, e; i < base64.length(); i = e) {
                    e = Math.min(base64.length(), i + 8192);
                    outFile.write(base64.substring(i, e).getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * https://www.baeldung.com/java-csv
     */
    @Test
    public void generateCsv() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        FileInputStream file = new FileInputStream("/Users/donglongcheng01/Downloads/migrate_app_template.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
        Sheet sheet = xssfWorkbook.getSheetAt(0);

        File csvOutputFile = new File("/Users/donglongcheng01/Downloads/migrate_app.csv");
        try (PrintWriter printWriter = new PrintWriter(csvOutputFile)) {
            String instruction = sheet.getRow(0).getCell(0).getStringCellValue();
            printWriter.println(instruction);
            List<String> header = Lists.newArrayList();
            for (int col = 0; col <= 10; col++) {
                header.add(sheet.getRow(1).getCell(col).getStringCellValue());
            }
            printWriter.println(String.join(",", header));
            for (int rowNo = 2; rowNo < 8000; rowNo++) {
                List<String> row = Lists.newArrayList();
                for (int col = 0; col <= 10; col++) {
                    row.add(UUID.randomUUID().toString());
                }
                printWriter.println(String.join(",", row));
            }
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

}
