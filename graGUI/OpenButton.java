package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenButton extends JButton {
    private static GreyGUI greyGUI;
    private DataTable dataTable;

    public OpenButton(String name, DataTable dataTable) {
        super(name);
        this.dataTable = dataTable;
    }

    public void laterInitListener() {
        if (greyGUI == null) {
            greyGUI = GreyGUI.getGUIComponent();
        }
        addActionListener(new OpenListener());
    }

    class OpenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            int rVal = chooser.showSaveDialog(OpenButton.this);

            if (rVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                dataTable.openNewDataTable(file);
            }

        }
    }

}
