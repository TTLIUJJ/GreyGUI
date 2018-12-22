package gui.component;

import javax.swing.*;

public class RadioButton extends JPanel {
    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton rawDataButton  = new JRadioButton("原始数据图",  false);
    private JRadioButton standardButton = new JRadioButton("无量钢化图",  false);
    private JRadioButton relativeButton = new JRadioButton("关联系数图",  false);
    private JRadioButton degreeButton   = new JRadioButton("灰色关联度图", false);

    {
        bg.add(rawDataButton);
        bg.add(standardButton);
        bg.add(relativeButton);
        bg.add(degreeButton);
    }

    public RadioButton() {
        add(rawDataButton);
        add(standardButton);
        add(relativeButton);
        add(degreeButton);
    }
}
