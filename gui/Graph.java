package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Graph {
    private static CategoryPlot plot;

    public static JPanel getGraph() {
        StandardChartTheme theme = new StandardChartTheme("CN");
        theme.setLargeFont(new Font("黑体", Font.BOLD, 10));
        theme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 10));
        theme.setRegularFont(new Font("宋体", Font.PLAIN, 10));
        ChartFactory.setChartTheme(theme);

        CategoryDataset dataset = getDataset();
        JFreeChart chart = ChartFactory.createLineChart(
                "我的折线图",
                "横 坐 标 啊",
                "纵 坐 标 啊",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        plot = (CategoryPlot)chart.getPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setOutlinePaint(Color.BLACK);

        ChartFrame frame = new ChartFrame("一个折线图", chart);

        JPanel jPanel = frame.getChartPanel();
        jPanel.setBorder((new LineBorder(Color.BLACK, 1, true)));


        return jPanel;
    }

    private static CategoryDataset getDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        return dataset;
    }

    private static CategoryDataset update() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1, "First", "2013");
        dataset.addValue(3, "First", "2014");
        dataset.addValue(2, "First", "2015");
        dataset.addValue(6, "First", "2016");
        dataset.addValue(5, "First", "2017");
        dataset.addValue(12, "First", "2018");

        dataset.addValue(14, "Second", "2013");
        dataset.addValue(13, "Second", "2014");
        dataset.addValue(12, "Second", "2015");
        dataset.addValue(9, "Second", "2016");
        dataset.addValue(5, "Second", "2017");
        dataset.addValue(7, "Second", "2018");

        return dataset;
    }

    public static void testData() {
        System.out.println("???");
        JPanel graph = GreyGUI.getGUIComponent().getGraph();
        plot.setDataset(update());
        graph.repaint();
        graph.updateUI();
    }

}
