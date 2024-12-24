import org.jfree.chart.JFreeChart;
import services.parser.DataProcessing;
import visualization.drawer.CircleDiagramm;
import visualization.drawer.ScatterPlot;

import java.util.List;

import static visualization.drawer.ScatterPlotWithRegressionLine.createScatterPlotWithRegression;
import static visualization.drawer.ScatterPlotWithRegressionLine.displayChart;

public class Main {
    public static void main(String[] args) {
        var averageCorrelations = DataProcessing.getAverageCorrelations();

        int mid = averageCorrelations.size() / 2;
        List<Double> xData =  averageCorrelations.subList(0, mid);
        List<Double> yData = averageCorrelations.subList(mid, averageCorrelations.size());

        // График зависимости с линией регрессии
        JFreeChart chart = createScatterPlotWithRegression(xData, yData);
        displayChart(chart);

        // График зависимости
        ScatterPlot sp = new ScatterPlot();
        JFreeChart scatterPlot = sp.createScatterPlot(averageCorrelations);
        sp.showChart(scatterPlot);

        // Круговая диаграмма
        CircleDiagramm circleDiagramm = new CircleDiagramm();
        CircleDiagramm.createPieDiagram();
    }
}

