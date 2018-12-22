package gui;

import gui.component.*;
import test.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GreyGUI extends JFrame {
    private DataTable dataTable = new DataTable();

    private JPanel  whitePanel  = new JPanel();
    private JLabel  dataLabel   = new JLabel("原始数据预览");
    private JButton openButton  = new OpenButton(dataTable);
    private JButton saveButton  = new JButton("保存");
    private JButton saveButton2 = new JButton("另存为");
    private JButton clearButton = new JButton("清零");
    private JButton startButton = new JButton("开始");

    private String []titles = {
            "参考序列", "序列1", "序列2", "序列3", "序列4", "序列5", "序列6", "序列7"
    };

    private String [][]data = {
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},
            {"1", "1.1", "1", "1.1", "1", "1.1", "1", "1.1"},

    };

//    private JTable dataTable = new JTable(data, titles);


    private String [] degreeTitles = {
            "排名", "序列编号","关联度"
    };
    private String [][]degreeData = {
            {"1", "序列2", "0.94321"},
            {"2", "序列4", "0.89102"},
            {"3", "序列1", "0.83212"},
            {"4", "序列3", "0.68224"},
            {"5", "序列5", "0.41132"}
    };
    private JTable degreeTable = new JTable(degreeData, degreeTitles);
    private JScrollPane jScrollPane2 = new JScrollPane(degreeTable);


    private JComboBox<String> comboBox1 = new JComboBox<>();
    private JComboBox<String> comboBox2 = new JComboBox<>();

    {
        dataLabel.setOpaque(true);
        dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dataLabel.setVerticalAlignment(SwingConstants.CENTER);

        comboBox1.addItem("选择无量纲化算法");
        comboBox1.addItem("标准化法");
        comboBox1.addItem("极差化法");
        comboBox1.addItem("线性比例法");
        comboBox1.addItem("归一化法");
        comboBox1.addItem("向量规范发");

        comboBox2.addItem("选择灰色关联算法");
        comboBox2.addItem("传统灰色关联算法");
        comboBox2.addItem("广义灰色关联算法");
        comboBox2.addItem("动态灰色关联算法");
        comboBox2.addItem("信息熵灰色关联算法");
    }

    private JLabel imageLabel = new JLabel("imageLabel" );
    {
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
    }

    JPanel graph = Graph.getGraph();

    private JLabel degreeLabel = new JLabel("灰色关联度及其排名");
    {
        degreeLabel.setFont(new Font("宋体", Font.BOLD, 15));
        degreeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        degreeLabel.setVerticalAlignment(SwingConstants.CENTER);
    }

    public void initComponent() {
        add(openButton,  new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(saveButton,  new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(saveButton2, new GBC(2, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(clearButton, new GBC(3, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(startButton, new GBC(4, 0, 1, 2).setFill(GBC.BOTH).setWeight(0, 0));
        add(dataLabel,   new GBC(5, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));

        add(comboBox1,   new GBC(0, 1, 2, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(comboBox2,   new GBC(2, 1, 2, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(whitePanel,  new GBC(4, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(dataTable, new GBC(5, 1, 1, 11).setFill(GBC.BOTH).setWeight(1,     0));

        add(new RadioButton(), new GBC(0, 2, 5, 1).setFill(GBC.BOTH).setWeight(1, 0));
        add(graph, new GBC(0, 3, 5, 7).setFill(GBC.BOTH).setWeight(0, 1));



        add(degreeLabel,  new GBC(0, 12, 12, 2).setFill(GBC.BOTH).setWeight(1, 0));
        add(new DegreeTextArea(),   new GBC(0, 14, 12, 2).setFill(GBC.BOTH).setWeight(1, 0));

    }



    public GreyGUI() {
        super();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });

        setLayout(new GridBagLayout());
        setBounds(300,100,800,600);
        initComponent();
    }

    public static void main(String []args) {
        SwingConsole.run(new GreyGUI(), 800, 600);
    }
}

class GBC extends GridBagConstraints {
    public GBC(int gridx, int gridy) {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    public GBC(int gridx, int grdiy, int gridwidth, int gridheight) {
        this.gridx = gridx;
        this.gridy = grdiy;
        this.gridwidth  = gridwidth;
        this.gridheight = gridheight;
    }

    public GBC setAnchor(int anchor) {
        this.anchor = anchor;
        return this;
    }

    public GBC setFill(int fill) {
        this.fill = fill;
        return this;
    }

    public GBC setWeight(int weightx, int weighty) {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    public GBC setInsets(int distance) {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    public GBC setINsets(int top, int left, int bottom, int right) {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }

    public GBC setIpad(int ipadx, int ipady) {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}
