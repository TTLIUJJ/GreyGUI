package gui;

import test.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GreyGUI extends JFrame {
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

    private volatile static GreyGUI greyGUI = null;
    private DataTable dataTable;
    private JScrollPane dataPane;
    private JPanel  whitePanel;
    private JLabel  dataLabel;
    private OpenButton openButton;
    private JButton saveButton;
    private JButton saveButton2;
    private JButton clearButton;
    private StartButton startButton;
    private JTable degreeTable;
    private JScrollPane jScrollPane2;
    private NondimensionTypeBox<String> nondimensionTypeBox;
    private ModelBox<String> modelBox;
    private RadioButton radioButton;
    private JLabel imageLabel;
    private JPanel graph;
    private JLabel degreeLabel;

    private  GreyGUI() {
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

    public void initComponent() {
        dataTable = new DataTable();
        dataPane = new JScrollPane(dataTable);
        whitePanel  = new JPanel();
        dataLabel   = new JLabel("原始数据预览");
        openButton  = new OpenButton("打开", dataTable);
        startButton = new StartButton("开始", dataTable);
        saveButton  = new JButton("保存");
        saveButton2 = new JButton("另存为");
        clearButton = new JButton("清零");
        dataPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        degreeTable = new JTable(degreeData, degreeTitles);
        jScrollPane2 = new JScrollPane(degreeTable);
        nondimensionTypeBox = new NondimensionTypeBox<>();
        modelBox = new ModelBox<>();
        dataLabel.setOpaque(true);
        dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dataLabel.setVerticalAlignment(SwingConstants.CENTER);
        nondimensionTypeBox.addObject(new String[] {"选择无量纲化算法", "标准化法", "极差化法", "线性比例法", "归一化法", "向量规范法"});
        modelBox.addObject(new String[] {"选择灰色关联算法", "传统灰色关联算法", "广义灰色关联算法", "动态灰色关联算法", "信息熵灰色关联算法"});
        imageLabel = new JLabel("imageLabel" );
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        degreeLabel = new JLabel("灰色关联度及其排名");
        degreeLabel.setFont(new Font("宋体", Font.BOLD, 15));
        degreeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        degreeLabel.setVerticalAlignment(SwingConstants.CENTER);
        graph = Graph.getGraph();
        radioButton = new RadioButton();


        add(openButton,  new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(saveButton,  new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(saveButton2, new GBC(2, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(clearButton, new GBC(3, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(startButton, new GBC(4, 0, 1, 2).setFill(GBC.BOTH).setWeight(0, 0));
        add(dataLabel,   new GBC(5, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));

        add(nondimensionTypeBox,   new GBC(0, 1, 2, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(modelBox,   new GBC(2, 1, 2, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(whitePanel,  new GBC(4, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(new JScrollPane(dataTable), new GBC(5, 1, 1, 11).setFill(GBC.BOTH).setWeight(1,     0));

        add(radioButton, new GBC(0, 2, 5, 1).setFill(GBC.BOTH).setWeight(1, 0));
        add(graph, new GBC(0, 3, 5, 7).setFill(GBC.BOTH).setWeight(0, 1));

        add(degreeLabel,  new GBC(0, 12, 12, 2).setFill(GBC.BOTH).setWeight(1, 0));
        add(new DegreeTextArea(),   new GBC(0, 14, 12, 2).setFill(GBC.BOTH).setWeight(1, 0));

    }

    public void laterInitListener() {
        openButton.laterInitListener();
        startButton.laterInitListener();
        nondimensionTypeBox.laterInitListener();
        modelBox.laterInitListener();
        radioButton.laterInitListener();
    }


    public OpenButton getOpenButton() {
        return openButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getSaveButton2() {
        return saveButton2;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public StartButton getStartButton() {
        return startButton;
    }

    public NondimensionTypeBox<String> getNondimensionTypeBox() {
        return nondimensionTypeBox;
    }

    public ModelBox<String> getModelBox() {
        return modelBox;
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public JPanel getGraph() {
        return graph;
    }

    public static GreyGUI getGUIComponent() {
        if (greyGUI == null) {
            synchronized (GreyGUI.class) {
                if (greyGUI == null) {
                    greyGUI = new GreyGUI();
                }
            }
        }

        return greyGUI;
    }

    public static void main(String []args) {
        GreyGUI greyGUI = new GreyGUI();
        greyGUI.laterInitListener();
        SwingConsole.run(greyGUI, 800, 600);

    }
}


