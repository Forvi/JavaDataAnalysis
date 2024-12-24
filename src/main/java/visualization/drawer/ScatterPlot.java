package visualization.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.List;

public class ScatterPlot {
    private XYSeries createSeries(List<Double> data) {
        XYSeries series = new XYSeries("Средние корреляции");
        for (int i = 0; i < data.size(); i++) {
            series.add(i + 1, data.get(i));
        }
        return series;
    }

    public JFreeChart createScatterPlot(List<Double> data) {
        XYSeries series = createSeries(data);
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return ChartFactory.createScatterPlot(
                "График зависимости",
                "Index",
                "Correlation Value",
                dataset
        );
    }

    public void showChart(JFreeChart chart) {
        JFrame frame = new JFrame("Scatter Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}