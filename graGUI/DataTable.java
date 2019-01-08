package graGUI;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;

public class DataTable extends JTable {
    private static GreyGUI greyGUI;
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

    public void laterInitListener() {
        if (greyGUI == null) {
            greyGUI = GreyGUI.getGUIComponent();
        }
    }

    public void openNewDataTable(File file) {
        tableDataModel.setTableData(file);
        setModel(tableDataModel);
    }

    public void startCompute(NondimensionType type, Model model) {
        dataBag = new DataBag(tableDataModel.getDataModelList());
        dataBag.nondimensionalization(type);
        dataBag.modelAlgorithm(model);
        greyGUI.setDataBag(dataBag);
    }
}
