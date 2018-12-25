package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButton extends JPanel {
    private static GreyGUI greyGUI;

    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton rawDataButton;
    private JRadioButton standardButton;
    private JRadioButton relativeButton;
    private JRadioButton degreeButton;


    public RadioButton() {
        rawDataButton  = new JRadioButton("原始数据图",  false);
        standardButton = new JRadioButton("无量钢化图",  false);
        relativeButton = new JRadioButton("关联系数图",  false);
        degreeButton   = new JRadioButton("灰色关联度图", false);

        bg.add(rawDataButton);
        bg.add(standardButton);
        bg.add(relativeButton);
        bg.add(degreeButton);

        add(rawDataButton);
        add(standardButton);
        add(relativeButton);
        add(degreeButton);
    }

    public void laterInitListener() {
        if (greyGUI == null) {
            greyGUI = GreyGUI.getGUIComponent();
        }
        GraphChangedListener listener = new GraphChangedListener();
        rawDataButton.addActionListener(listener);
        standardButton.addActionListener(listener);
        relativeButton.addActionListener(listener);
        degreeButton.addActionListener(listener);
    }

    public class GraphChangedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("aaa");
            Graph.testData();
        }
    }
}
