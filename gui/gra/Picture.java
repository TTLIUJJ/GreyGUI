package gui.gra;

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

public class Picture extends JPanel {
    private static CategoryDataset dataset = new DefaultCategoryDataset();
    private static CategoryPlot plot;
    private static JPanel picture;
    static {
        StandardChartTheme theme = new StandardChartTheme("CN");
        theme.setLargeFont(new Font("黑体", Font.BOLD, 10));
        theme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 10));
        theme.setRegularFont(new Font("宋体", Font.PLAIN, 10));
        ChartFactory.setChartTheme(theme);

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
        frame.pack();
        frame.setVisible(true);

        picture = frame.getChartPanel();
    }

    public static JPanel getPicture() {
        return picture;
    }
}
