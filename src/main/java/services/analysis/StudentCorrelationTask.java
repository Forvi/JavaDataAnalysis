package services.analysis;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.util.*;
import java.util.concurrent.Callable;

public class StudentCorrelationTask implements Callable<Map.Entry<String, List<Double>>> {
    private final String studentName;
    private final List<double[]> data;

    public StudentCorrelationTask(String studentName, List<double[]> data) {
        this.studentName = studentName;
        this.data = data;
    }

    @Override
    public Map.Entry<String, List<Double>> call() {
        if (data == null || data.isEmpty()) {
            return new AbstractMap.SimpleEntry<>(studentName, Collections.emptyList());
        }

        int len = AverageValues.WhichAverage(data);

        List<double[]> filledData = new ArrayList<>();
        for (double[] array : data) {
            filledData.add(AverageValues.fillWithAverage(array, len));
        }

        List<Double> result = new ArrayList<>();
        for (int i = 0; i < filledData.size(); i++) {
            for (int j = i + 1; j < filledData.size(); j++) {
                PearsonsCorrelation correlation = new PearsonsCorrelation();
                double corr = correlation.correlation(filledData.get(i), filledData.get(j));
                result.add(Double.isNaN(corr) ? 0 : corr);
            }
        }

        return new AbstractMap.SimpleEntry<>(studentName, result);
    }
}

