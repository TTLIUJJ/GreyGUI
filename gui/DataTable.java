package gui;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DataTable extends JTable {
    private TableDataModel tableDataModel = new TableDataModel();

    public DataTable() {
        initDefaultTableModel();
    }

    private void initDefaultTableModel() {
        String []points = new String[] {
                "参考序列", "比较序列1", "比较序列2", "比较序列...",
        };
        DataModel dataModel = new DataModel(0, points);
        tableDataModel.setTableData(Arrays.asList(dataModel));
        setModel(tableDataModel);
    }

    public void openNewDataTable(File file) {
        tableDataModel = new TableDataModel();
        tableDataModel.setTableData(file);
        setModel(tableDataModel);
    }

    public List<DataModel> getData() {
        return tableDataModel.getList();
    }
}
