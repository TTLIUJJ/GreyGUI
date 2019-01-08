package graGUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButton extends JPanel {
    private static GreyGUI greyGUI;
    private DataTable dataTable;
    private CategoryPlot plot;

    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton rawDataButton;
    private JRadioButton standardButton;
    private JRadioButton relativeButton;
    private JRadioButton degreeButton;

    private JFreeChart jFreeChart;
    private JButton clearButton;

    public RadioButton(JButton clearButton) {
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

        this.clearButton = clearButton;
    }

    public void laterInitListener() {
        if (greyGUI == null) {
            greyGUI = GreyGUI.getGUIComponent();
        }
        if (dataTable == null) {
            dataTable = greyGUI.getDataTable();
        }
        rawDataButton.addActionListener(new RawDataGraph());
        standardButton.addActionListener(new StandardGraph());
        relativeButton.addActionListener(new RelativeGraph());
        degreeButton.addActionListener(new DegreeGraph());

        clearButton.addActionListener(new DefaultGraph());
    }

    public JPanel getGraphPanel() {
        StandardChartTheme theme = new StandardChartTheme("CN");
        theme.setLargeFont(new Font("黑体", Font.BOLD, 10));
        theme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 10));
        theme.setRegularFont(new Font("宋体", Font.PLAIN, 10));
        ChartFactory.setChartTheme(theme);

        CategoryDataset dataset = new DefaultCategoryDataset();
        jFreeChart = ChartFactory.createLineChart(
                "我的折线图",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        plot = (CategoryPlot)jFreeChart.getPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setOutlinePaint(Color.BLACK);

        ChartFrame frame = new ChartFrame("一个折线图", jFreeChart);
        frame.pack();

        return frame.getChartPanel();
    }

    public JFreeChart getjFreeChart() {
        return jFreeChart;
    }

    public class DefaultGraph implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CategoryDataset dataset = new DefaultCategoryDataset();
            plot.setDataset(dataset);
            bg.clearSelection();
        }
    }

    public class RawDataGraph implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DataBag dataBag = greyGUI.getDataBag();
            double [][]rawData = dataBag.getRawData();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < rawData.length; ++i) {
                String rowKey = i == 0 ? "参考序列" : "比较序列" + i;
                for (int j = 0; j < rawData[i].length; ++j) {
                    dataset.addValue(rawData[i][j], rowKey, "" + (j+1));
                }
            }

            plot.setDataset(dataset);
        }
    }

    public class StandardGraph implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DataBag dataBag = greyGUI.getDataBag();
            double [][]data = dataBag.getNondimensionData();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < data.length; ++i) {
                String rowKey = i == 0 ? "参考序列" : "比较序列" + i;
                for (int j = 0; j < data[i].length; ++j) {
                    dataset.addValue(data[i][j], rowKey, "" + (j+1));
                }
            }

            plot.setDataset(dataset);
        }
    }

    public class RelativeGraph implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DataBag dataBag = greyGUI.getDataBag();
            double [][]data = dataBag.getRatio();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 1; i < data.length; ++i) {
                String rowKey = "比较序列" + i;
                for (int j = 0; j < data[i].length; ++j) {
                    dataset.addValue(data[i][j], rowKey, "" + (j+1));
                }
            }

            plot.setDataset(dataset);
        }
    }

    public class DegreeGraph implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DataBag dataBag = greyGUI.getDataBag();
            double []data = dataBag.getDegree();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 1; i < data.length; ++i) {
                String columnKey = "比较序列" + i;
                dataset.addValue(data[i], "关联度对比", columnKey);
            }

            plot.setDataset(dataset);
        }
    }

    public class ResultListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("shit...");
        }
    }
}
