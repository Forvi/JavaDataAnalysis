import db.dao.StudentCorrelationDao;
import services.analysis.CorrelationAnalysis;
import services.parser.DataProcessing;
import services.parser.Parser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static services.parser.DataProcessing.calculateAverageCorrelations;

public class Main {
    public static void main(String[] args) {
//        var file = Parser.readCSV("Data/basicprogramming.csv");
//        var students = Parser.parseStudents(file);
//        var topics = Parser.getStudentPracticePoints(students);
//
//        int threadPoolSize = 4;
//
//        try {
//            Map<String, List<Double>> correlationResults = CorrelationAnalysis.processStudentData(topics, threadPoolSize);
//            CorrelationAnalysis.printCorrelationResults(correlationResults);
//        } catch (InterruptedException | ExecutionException e) {
//            System.err.println("Ошибка при обработке данных: " + e.getMessage());
//            e.printStackTrace();
//        }

        var averageCorrelations = DataProcessing.getAverageCorrelations();
        System.out.println("Общая корреляция: " + averageCorrelations);
    }
}

