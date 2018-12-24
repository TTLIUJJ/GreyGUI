package gui;

import javax.swing.*;

public class StartButton extends JButton {
    private NondimensionType type;
    private Model model;

    public StartButton(String name) {
        super(name);
        type  = NondimensionType.NONE;
        model = Model.NONE;
    }

    public void setNondimensionType(NondimensionType type) {
        this.type = type;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
