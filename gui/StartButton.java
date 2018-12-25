package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends JButton {
    private static GreyGUI greyGUI;
    private DataTable dataTable;

    public StartButton(String name, DataTable dataTable) {
        super(name);
        this.dataTable = dataTable;
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

            double []degree = dataTable.getDataBag().getDegree();
            for (double x : degree) {
                System.out.println(x);
            }
        }
    }
}
