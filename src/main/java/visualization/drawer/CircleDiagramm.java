package visualization.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.JFreeChart;
import javax.swing.*;
import java.util.List;
import java.util.Map;

import static services.parser.Parser.*;

public class CircleDiagramm {
    public static void createPieDiagram() {
        var file = readCSV("Data/basicprogramming.csv");
        var students = parseStudents(file);
        Map<String, List<double[]>> studentPracticePoints = getStudentPracticePoints(students);

        DefaultPieDataset dataset = createDataset(students.size(), studentPracticePoints.size());

        JFreeChart chart = ChartFactory.createPieChart(
                "Соотношение общего количества к подходящему под анализ",
                dataset,
                true,
                true,
                false
        );

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(560, 370));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

    }

    private static DefaultPieDataset createDataset(int mainValue, int resultValue) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("Общее", mainValue);
        dataset.setValue("Итоговое", resultValue);

        return dataset;
    }
}
