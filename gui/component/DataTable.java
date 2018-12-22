package gui.component;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class DataTable extends JScrollPane {
    private static String []titles = {"_"};
    private static String [][] rawData = {{"1"}};
    private static JTable table = new JTable(rawData, titles);

    public DataTable() {
        super(table);
        table.addComponentListener(new TableListener());
    }

    public void readAndProcessData(File file) {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getLastRowNum();
            rawData = new String[rows+1][sheet.getRow(0).getLastCellNum()];
            titles  = new String[sheet.getRow(0).getLastCellNum()];

            for (int i = 0; i <= rows; ++i) {
                XSSFRow rowData = sheet.getRow(i);
                for (int j = 0; j < rowData.getLastCellNum(); ++j) {
                   XSSFCell cell = rowData.getCell(j);
                   if (cell.getCellType() == CellType.NUMERIC) {
                        rawData[i][j] = Double.toString(cell.getNumericCellValue());
                   }
                   else {
                        rawData[i][j] = cell.getStringCellValue();
                   }
                }
            }
            table.setVisible(true);
            setTitlesAndData();

            System.out.println("titles: " + Arrays.toString(titles));
            for (String []x : rawData) {
                System.out.println(Arrays.toString(x));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setTitlesAndData() {
        String []title = rawData[0];
        title[0] = "参考序列";
        for (int i = 1; i < title.length; ++i) {
            title[i] = "比较序列" + i;
        }
        titles = title;

        int row = 0;
        try {
            Double.parseDouble(rawData[0][0]);
        } catch (Exception e) {
            ++row;
        }

        String [][]data = new String[rawData.length-row][rawData[0].length];
        for (int i = 0 ; i < rawData.length - row; ++i) {
            for (int j = 0; j < rawData[i].length; ++j) {
                data[i][j] = rawData[i+row][j];
            }
        }
        rawData = data;
    }

    class TableListener implements ComponentListener {
        @Override
        public void componentResized(ComponentEvent e) {
            System.out.println("1");
        }

        @Override
        public void componentMoved(ComponentEvent e) {
            System.out.println("2");
        }

        @Override
        public void componentShown(ComponentEvent e) {
            System.out.println("3");
        }

        @Override
        public void componentHidden(ComponentEvent e) {
            System.out.println("4");
        }
    }
}
