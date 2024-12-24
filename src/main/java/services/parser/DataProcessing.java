package services.parser;

import db.dao.StudentCorrelationDao;

import java.util.ArrayList;
import java.util.List;

public class DataProcessing {
    public static boolean isUniform(double[] array) {
        if (array == null || array.length == 0) {
            return false;
        }

        double firstValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] != firstValue) {
                return false;
            }
        }
        return true;
    }

    public static List<Double> calculateAverageCorrelations(List<List<Double>> studentCorrelations) {
        if (studentCorrelations.isEmpty()) return new ArrayList<>();

        int size = studentCorrelations.getFirst().size();
        List<Double> averages = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            double sum = 0;
            int count = 0;

            for (List<Double> correlations : studentCorrelations) {
                if (i < correlations.size()) {
                    sum += correlations.get(i);
                    count++;
                }
            }

            averages.add(sum / count);
        }

        return averages;
    }

    public static List<Double> getAverageCorrelations() {
        StudentCorrelationDao db = new StudentCorrelationDao();
        List<List<Double>> studentCorrelations = new ArrayList<>();
        var students = db.getAll();

        for (var student : students)
            studentCorrelations.add(student.getCorrelationsData());

        return calculateAverageCorrelations(studentCorrelations);
    }
}


