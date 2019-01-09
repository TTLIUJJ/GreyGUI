package graGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModelBox<E> extends JComboBox<E> {
    private static GreyGUI greyGUI;
    private Model model;

    public Model getAlModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void laterInitListener() {
        if (greyGUI == null) {
            greyGUI = GreyGUI.getGUIComponent();
        }
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
            ModelBox<String> modelBox = greyGUI.getModelBox();

            switch (index) {
                case 1:
                    if (desc.equals(Model.Traditional.getDesc())) {
                        modelBox.setModel(Model.Traditional);
                    }
                    break;

                case 2:
                    if (desc.equals(Model.General.getDesc())) {
                        modelBox.setModel(Model.General);
                    }
                    break;

                case 3:
                    if (desc.equals(Model.Dynamic.getDesc())) {
                        modelBox.setModel(Model.Dynamic);
                    }
                    break;

                case 4:
                    if (desc.equals(Model.Shannon.getDesc())) {
                        modelBox.setModel(Model.Shannon);
                    }
                    break;

                default:
                    modelBox.setModel(Model.NONE);
                    break;
            }
        }
    }
}
