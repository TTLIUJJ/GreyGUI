package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class StartButton extends JButton {
    private static GreyGUI greyGUI;
    private DataTable dataTable;
    private DegreeTextArea degreeTextArea;

    public StartButton(String name, DataTable dataTable, DegreeTextArea degreeTextArea) {
        super(name);
        this.dataTable = dataTable;
        this.degreeTextArea = degreeTextArea;
    }


    public void laterInitListener() {
        if (greyGUI == null) {
            greyGUI = GreyGUI.getGUIComponent();
        }
        addActionListener(new StartListener());
    }

    class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            NondimensionTypeBox nondimensionTypeBox1 = greyGUI.getNondimensionTypeBox();
            ModelBox modelBox = greyGUI.getModelBox();

            dataTable.startCompute(nondimensionTypeBox1.getType(), modelBox.getAlModel());

            DataBag dataBag = greyGUI.getDataBag();
            double []degree = dataBag.getDegree();
            TreeMap<Double, Integer> treeMap = new TreeMap<>();
            for (int i = 1; i < degree.length; ++i) {
                treeMap.put(degree[i], i);
            }

            degreeTextArea.setText("");
            while (!treeMap.isEmpty()) {
                Map.Entry<Double, Integer> entry = treeMap.pollFirstEntry();
                degreeTextArea.append("比较序列" + entry.getValue() + "=" + entry.getKey() + "\r\n");
            }

            degreeTextArea.updateUI();
        }
    }
}
