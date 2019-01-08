package graGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NondimensionTypeBox<E> extends JComboBox<E> {
    private static GreyGUI greyGUI;
    private NondimensionType type;

    public NondimensionType getType() {
        return type;
    }

    public void setType(NondimensionType type) {
        this.type = type;
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
            NondimensionTypeBox<String> nondimensionTypeBox = greyGUI.getNondimensionTypeBox();

            switch (index) {
                case 1:
                    if (desc.equals(NondimensionType.Standardization.getDesc())) {
                        nondimensionTypeBox.setType(NondimensionType.Standardization);
                    }
                    break;

                case 2:
                    if (desc.equals(NondimensionType.ExtremeDifference.getDesc())) {
                        nondimensionTypeBox.setType(NondimensionType.ExtremeDifference);
                    }
                    break;

                case 3:
                    if (desc.equals(NondimensionType.Linearity.getDesc())) {
                        nondimensionTypeBox.setType(NondimensionType.Linearity);
                    }
                    break;

                case 4:
                    if (desc.equals(NondimensionType.Normalization.getDesc())) {
                        nondimensionTypeBox.setType(NondimensionType.Normalization);
                    }
                    break;

                case 5:
                    if (desc.equals(NondimensionType.Vector.getDesc())) {
                        nondimensionTypeBox.setType(NondimensionType.Vector);
                    }
                    break;

                default:
                    nondimensionTypeBox.setType(NondimensionType.NONE);
                    break;
            }
        }
    }
}
