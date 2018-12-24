package gui;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableDataModel extends AbstractTableModel {
    private List<DataModel> list = new ArrayList<>();

    public List<DataModel> getList() {
        return list;
    }

    public void setTableData(DataModel data) {
        int sequence = data.getSequence();
        if (sequence > list.size()) {
            throw new ArrayIndexOutOfBoundsException("请按照顺序插入新的数据");
        }
        else  if (sequence == list.size()){
            list.add(data);
        }
        list.set(sequence, data);
        fireTableDataChanged();
    }

    public void setTableData(List<DataModel> datas) {
        this.list = datas;
        fireTableDataChanged();
    }

    public void setTableData(File file) {
        FileInputStream stream = null;
        list = new ArrayList<>();
        try {
            stream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getLastRowNum();

            for (int i = 0; i <= rows; ++i) {
                XSSFRow rowData = sheet.getRow(i);
                String []points = new String[rowData.getLastCellNum()];

                for (int j = 0; j < rowData.getLastCellNum(); ++j) {
                    XSSFCell cell = rowData.getCell(j);
                    if (cell.getCellType() == CellType.NUMERIC) {
                        points[j]  = Double.toString(cell.getNumericCellValue());
                    }
                    else {
                        points[j]  = cell.getStringCellValue();
                    }
                }
                DataModel dataModel = new DataModel(i+1, points);
                System.out.println(dataModel);
                list.add(dataModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return DataModel.getSize() + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DataModel model = list.get(rowIndex);
        if (columnIndex == 0) {
            return model.getSequence();
        }
        else {
            return model.getPoints()[columnIndex-1];
        }
    }
}
