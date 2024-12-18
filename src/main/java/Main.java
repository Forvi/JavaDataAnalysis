import db.dao.StudentCorrelationDao;
import db.entities.StudentCorrelationEntity;
import services.CorrelationAnalysis;
import services.Parser;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        var file = Parser.readCSV("Data/basicprogramming.csv");
        var students = Parser.parseStudents(file);
        var topics = Parser.getStudentPracticePoints(students);
        var studentCorrelation = CorrelationAnalysis.processAllStudentData(topics);

        StudentCorrelationDao studentDao = new StudentCorrelationDao();

        // сохранение студентов в бд
        for (Map.Entry<String, List<Double>> entry : studentCorrelation.entrySet()) {
            String studentName = entry.getKey();
            List<Double> correlationValues = entry.getValue();
            StudentCorrelationEntity studentEntity = new StudentCorrelationEntity(studentName, correlationValues);
            studentDao.save(studentEntity);
        }
    }
}
