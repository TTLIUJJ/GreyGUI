package gui;

import test.combobox.ComboBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ChooseBox<E> extends JComboBox<E> {
    private StartButton startButton;

    public ChooseBox() {

    }

    public ChooseBox(StartButton startButton) {
        this.startButton = startButton;
        addActionListener(new ChooseListener());
    }

    public void addObject(E []strings) {
        for (E item : strings) {
            addItem(item);
        }
    }

    class ChooseListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int index    = ((JComboBox)e.getSource()).getSelectedIndex();
            String desc  = (String)((JComboBox)e.getSource()).getSelectedItem();

            switch (index) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }

            System.out.println(desc);
        }
    }
}
