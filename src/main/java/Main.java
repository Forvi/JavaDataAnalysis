import services.CorrelationAnalysis;
import services.Parser;

public class Main {
    public static void main(String[] args) {
        var file = Parser.readCSV("Data/basicprogramming.csv");
        var students = Parser.parseStudents(file);
        var topics = Parser.getStudentPracticePoints(students);
        var a = CorrelationAnalysis.processAllStudentData(topics);
        CorrelationAnalysis.printCorrelationResults(a);
    }
}
