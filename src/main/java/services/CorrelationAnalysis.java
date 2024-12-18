package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class CorrelationAnalysis {
    public static Map<String, List<Double>> processAllStudentData(Map<String, List<double[]>> data) {
        Map<String, List<Double>> studentCorrelationResults = new HashMap<>();

        for (Map.Entry<String, List<double[]>> entry : data.entrySet()) {
            String studentName = entry.getKey();
            List<double[]> arr = entry.getValue();

            if (arr == null || arr.isEmpty()) {
                continue;
            }

            int len = AverageValues.WhichAverage(arr);

            List<double[]> newArr = new ArrayList<>();
            for (double[] array : arr) {
                newArr.add(AverageValues.fillWithAverage(array, len));
            }

            List<Double> result = new ArrayList<>();

            for (int i = 0; i < newArr.size(); i++) {
                for (int j = i + 1; j < newArr.size(); j++) {
                    PearsonsCorrelation correlation = new PearsonsCorrelation();
                    double corr = correlation.correlation(newArr.get(i), newArr.get(j));
                    if (Double.isNaN(corr)) corr = 0;
                    result.add(corr);
                }
            }

            studentCorrelationResults.put(studentName, result);
        }

        return studentCorrelationResults;
    }

    public static void printCorrelationResults(Map<String, List<Double>> correlationData) {
        for (Map.Entry<String, List<Double>> entry : correlationData.entrySet()) {
            String studentName = entry.getKey();
            List<Double> correlations = entry.getValue();

            System.out.println("\nСтудент: " + studentName);
            System.out.println("Корреляции между темами:");
            for (int i = 0; i < correlations.size(); i++) {
                System.out.printf(String.format("%s\n", correlations.get(i)));
            }
        }
    }
}
