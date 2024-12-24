package visualization.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScatterPlotWithRegressionLine {
    public static JFreeChart createScatterPlotWithRegression(List<Double> xData, List<Double> yData) {
        double[] xArray = listToArray(xData);
        double[] yArray = listToArray(yData);

        XYSeriesCollection dataset = createDatasetWithRegression(xArray, yArray);

        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                "График зависимости с линией регрессии",
                "X",
                "Y",
                dataset
        );

        configureRenderer(scatterPlot.getXYPlot());
        return scatterPlot;
    }

    private static double[] listToArray(List<Double> list) {
        double[] array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private static XYSeriesCollection createDatasetWithRegression(double[] xData, double[] yData) {
        XYSeries scatterSeries = new XYSeries("Scatter Data");
        for (int i = 0; i < xData.length; i++) {
            scatterSeries.add(xData[i], yData[i]);
        }

        double[] regressionParams = calculateRegressionLine(xData, yData);
        double m = regressionParams[0];
        double b = regressionParams[1];

        XYSeries regressionSeries = new XYSeries("Regression Line");
        for (double x : xData) {
            regressionSeries.add(x, m * x + b);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(scatterSeries);
        dataset.addSeries(regressionSeries);

        return dataset;
    }

    private static void configureRenderer(XYPlot plot) {
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED); // Scatter points
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesLinesVisible(0, false);

        renderer.setSeriesPaint(1, Color.BLUE); // Regression line
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesLinesVisible(1, true);

        plot.setRenderer(renderer);
    }

    private static double[] calculateRegressionLine(double[] x, double[] y) {
        int n = x.length;
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumX2 += x[i] * x[i];
        }

        double m = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double b = (sumY - m * sumX) / n;

        return new double[]{m, b};
    }

    public static void displayChart(JFreeChart chart) {
        JFrame frame = new JFrame("Scatter Plot with Regression");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
