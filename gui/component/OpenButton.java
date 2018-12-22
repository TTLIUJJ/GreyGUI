package gui.component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenButton extends JButton {
    private File file;
    private DataTable dataTable;

    public OpenButton(DataTable dataTable) {
        super("打开");
        this.dataTable = dataTable;
        addActionListener(new OpenListener());
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    class OpenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            int rVal = chooser.showSaveDialog(OpenButton.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
                System.out.println(file.getName());
                dataTable.readAndProcessData(file);
            }
        }
    }
}
