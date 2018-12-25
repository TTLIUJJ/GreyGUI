package gui;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;

public class DataTable extends JTable {
    private TableDataModel tableDataModel = new TableDataModel();
    private DataBag dataBag;

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
        tableDataModel.setTableData(file);
        setModel(tableDataModel);

        for (DataModel model : tableDataModel.getDataModelList()) {
            System.out.println(model);
        }
    }

    public void startCompute(NondimensionType type, Model model) {
        this.dataBag = new DataBag(tableDataModel.getDataModelList());

        dataBag.nondimensionalization(type);
        dataBag.modelAlgorithm(model);
    }

    public DataBag getDataBag() {
        return dataBag;
    }

    public void setDataBag(DataBag dataBag) {
        this.dataBag = dataBag;
    }

}
