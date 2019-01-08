package gui;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import main.SwingConsole;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

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
    private JButton resultButton;
    private JButton pictureButton;
    private JButton clearButton;
    private StartButton startButton;
    private JTable degreeTable;
    private JScrollPane jScrollPane2;
    private NondimensionTypeBox<String> nondimensionTypeBox;
    private ModelBox<String> modelBox;
    private RadioButton radioButton;
    private JLabel imageLabel;
    private JLabel degreeLabel;
    private DataBag dataBag;
    private DegreeTextArea degreeTextArea;

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
        dataTable      = new DataTable();
        degreeTextArea = new DegreeTextArea();
        dataPane       = new JScrollPane(dataTable);
        whitePanel     = new JPanel();
        dataLabel      = new JLabel("原始数据预览");
        openButton     = new OpenButton("打开", dataTable);
        startButton    = new StartButton("开始", dataTable, degreeTextArea);
        resultButton   = new JButton("保存结果");
        pictureButton  = new JButton("保存图片");
        clearButton    = new JButton("清零");
        dataPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        degreeTable    = new JTable(degreeData, degreeTitles);
        jScrollPane2   = new JScrollPane(degreeTable);
        nondimensionTypeBox = new NondimensionTypeBox<>();
        modelBox       = new ModelBox<>();
        dataLabel.setOpaque(true);
        dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dataLabel.setVerticalAlignment(SwingConstants.CENTER);
        nondimensionTypeBox.addObject(new String[] {"选择无量纲化算法", "标准化法", "极差化法", "线性比例法", "归一化法", "向量规范法"});
        modelBox.addObject(new String[] {"选择灰色关联度算法", "传统灰色关联度算法", "广义灰色关联度算法", "动态灰色关联度算法", "信息熵灰色关联度算法"});
        imageLabel     = new JLabel("imageLabel" );
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        degreeLabel    = new JLabel("比较序列的灰色关联度");
        degreeLabel.setFont(new Font("宋体", Font.BOLD, 15));
        degreeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        degreeLabel.setVerticalAlignment(SwingConstants.CENTER);
        radioButton    = new RadioButton(clearButton);

        add(openButton,    new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(resultButton,  new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(pictureButton, new GBC(2, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(clearButton,   new GBC(3, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(startButton,   new GBC(4, 0, 1, 2).setFill(GBC.BOTH).setWeight(0, 0));
        add(dataLabel,     new GBC(5, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));

        add(nondimensionTypeBox,  new GBC(0, 1, 2, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(modelBox,    new GBC(2, 1, 2, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(whitePanel,  new GBC(4, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(new JScrollPane(dataTable), new GBC(5, 1, 1, 11).setFill(GBC.BOTH).setWeight(1,     0));

        add(radioButton, new GBC(0, 2, 5, 1).setFill(GBC.BOTH).setWeight(1, 0));
        add(radioButton.getGraphPanel(), new GBC(0, 3, 5, 7).setFill(GBC.BOTH).setWeight(0, 1));

        add(degreeLabel,    new GBC(0, 12, 12, 2).setFill(GBC.BOTH).setWeight(1, 0));
        add(degreeTextArea, new GBC(0, 14, 12, 2).setFill(GBC.BOTH).setWeight(1, 0));
    }

    public void laterInitListener() {
        dataTable.laterInitListener();
        openButton.laterInitListener();
        startButton.laterInitListener();
        nondimensionTypeBox.laterInitListener();
        modelBox.laterInitListener();
        radioButton.laterInitListener();

        buttonLaterInitListener();
    }

    private void buttonLaterInitListener() {
        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = degreeTextArea.getResult();

                FileSystemView fileSystemView = FileSystemView.getFileSystemView();
                File home = fileSystemView.getHomeDirectory();
                /*
                 * 文件需要判断操作系统
                 *  mac os "/关联序"
                 * windows "\关联序"
                 */
                File file = null;
                String os = System.getProperty("os.name");
                os = os.toLowerCase();
                if (os.contains("os")) {
                    file = new File(home.getPath() + "/关联序.txt");
                }
                else {
                    file = new File(home.getPath() + "\\关联序.txt");
                }

                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file));
                    writer.write(result);
                    writer.close();
                    JOptionPane.showConfirmDialog(null, "保存成功", "灰关联序计算结果",JOptionPane.DEFAULT_OPTION);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    JOptionPane.showConfirmDialog(null, "保存失败", "灰关联序计算结果",JOptionPane.DEFAULT_OPTION);
                }
            }
        });

        pictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFreeChart jFreeChart = radioButton.getjFreeChart();
                FileSystemView fileSystemView = FileSystemView.getFileSystemView();
                File home = fileSystemView.getHomeDirectory();
                File file = new File(home.getPath() + "/折线图");
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    ChartUtilities.writeChartAsJPEG(fileOutputStream, jFreeChart, 600, 400);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    JOptionPane.showConfirmDialog(null, "保存成功", "数据折线图",JOptionPane.DEFAULT_OPTION);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    JOptionPane.showConfirmDialog(null, "保存失败", "数据折线图",JOptionPane.DEFAULT_OPTION);
                }
            }
        });

    }

    public OpenButton getOpenButton() {
        return openButton;
    }

    public JButton getResultButton() {
        return resultButton;
    }

    public JButton getPictureButton() {
        return pictureButton;
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

    public DegreeTextArea getDegreeTextArea() {
        return degreeTextArea;
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public DataBag getDataBag() {
        return dataBag;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public void setDataBag(DataBag dataBag) {
        this.dataBag = dataBag;
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

    public static void main(String []args) throws Exception {
        GreyGUI greyGUI = new GreyGUI();
        greyGUI.laterInitListener();
        SwingConsole.run(greyGUI, 800, 600);
    }
}


