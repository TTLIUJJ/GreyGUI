package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DegreeTextArea extends JTextArea {
    public DegreeTextArea() {
        super();

        this.setSize(50, 50);
        append("************************************      灰色关联计算软件操作流程简述      ************************************\r\n");
        append("1. 点击\"打开\"按钮，导入原始数据，在界面右侧可预览导入的数据\r\n");
        append("2. 选择合适的无量纲化算法和合适的灰色关联度算法\r\n");
        append("3. 点击\"开始\"按钮，对步骤2选择的算法进行计算，计算结果按照关联度的大小显示在界面的下方\r\n");
        append("4. 若需要查看数据变化的折线图，可以点击相应的选项（例如原始数据图），点击清零按钮可重置折线图\r\n");
        append("5. 点击\"保存结果\"或\"保存图片\"按钮可保存至电脑");
        this.setBorder(new LineBorder(Color.BLACK, 1, true));
    }

    public String getResult() {
        return this.getText();
    }
}
