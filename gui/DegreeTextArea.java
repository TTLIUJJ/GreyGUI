package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DegreeTextArea extends JTextArea {
    public DegreeTextArea() {
        super();

        this.setSize(50, 50);
        append("打开数据之后, 点击开始按钮, 查看关联度的大小");

        this.setBorder(new LineBorder(Color.BLACK, 1, true));
    }
}
