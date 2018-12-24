package gui;

import test.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GreyGUI extends JFrame {
    private DataTable dataTable = new DataTable();
    private JScrollPane dataPane = new JScrollPane(dataTable);
    private JPanel  whitePanel  = new JPanel();
    private JLabel  dataLabel   = new JLabel("原始数据预览");
    private JButton openButton  = new OpenButton(dataTable);
    private JButton saveButton  = new JButton("保存");
    private JButton saveButton2 = new JButton("另存为");
    private JButton clearButton = new JButton("清零");
    private StartButton startButton = new StartButton("开始");

    {
        dataPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

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


    private ChooseBox<String> comboBox1 = new ChooseBox<>(startButton);
    private ChooseBox<String> comboBox2 = new ChooseBox<>(startButton);

    {
        dataLabel.setOpaque(true);
        dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dataLabel.setVerticalAlignment(SwingConstants.CENTER);
        comboBox1.addObject(new String[] {"选择无量纲化算法", "标准化法", "极差化法", "线性比例法", "归一化法", "向量规范法"});
        comboBox2.addObject(new String[] {"选择灰色关联算法", "传统灰色关联算法", "广义灰色关联算法", "动态灰色关联算法", "信息熵灰色关联算法"});
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
        add(new JScrollPane(dataTable), new GBC(5, 1, 1, 11).setFill(GBC.BOTH).setWeight(1,     0));

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


