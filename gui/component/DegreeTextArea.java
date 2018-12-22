package gui.component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DegreeTextArea extends JTextArea {
    public DegreeTextArea() {
        this.setSize(50, 50);
        this.append("1\n");
        this.append("2\n");
        this.append("3\n");
        this.append("4\n");
        this.append("5\n");
        this.append("6\n");
        this.append("7\n");
        this.append("8\n");
        this.append("9\n");
        this.append("1\n");

        this.setBorder(new LineBorder(Color.BLACK, 1, true));
    }
}
