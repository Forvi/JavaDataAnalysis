package services.parser;

import models.*;
import com.opencsv.exceptions.CsvException;
import com.opencsv.*;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class Parser {
    public static List<String[]> readCSV(String file) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        var parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();
        try (var reader = new CSVReaderBuilder(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) // Кодировка UTF-8
                .withCSVParser(parser)
                .build()) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Student> parseStudents(List<String[]> values) {
        var students = new ArrayList<Student>();

        for (int i = 3; i < values.size(); i++) {
            var name = values.get(i)[0];
            var group = values.get(i)[1];
            var pointsCount = Integer.parseInt(values.get(i)[2])
                    + Integer.parseInt(values.get(i)[3])
                    + Integer.parseInt(values.get(i)[4]);
            var topicsForStudent = parseTopics(values, i);
            var student = new Student(name, group, pointsCount, topicsForStudent);

            students.add(student);
        }

        return students;
    }

    public static ArrayList<Topic> parseTopics(List<String[]> values, int indexStudent) {
        var tasks = new ArrayList<Task>();
        var topics = new ArrayList<Topic>();
        var len = values.get(0).length;
        var titleTopic = values.get(0)[7];

        for (int i = 7; i < len; i++) {
            var line = values.get(1)[i];

            if (!Objects.equals(values.get(0)[i], "")) {
                var topic = new Topic(titleTopic, tasks);
                topics.add(topic);
                tasks = new ArrayList<>();
                titleTopic = values.getFirst()[i];
            }

            if (line.contains("Упр:")) {
                var title = line.replace("Упр: ", "");
                var type = TasksTypes.exercise;
                var maxPointsCount = Integer.parseInt(values.get(indexStudent)[i]);
                var task = new Task(title, type, maxPointsCount);
                tasks.add(task);
            } else if (line.contains("ДЗ:")) {
                var title = line.replace("ДЗ: ", "");
                var type = TasksTypes.practice;
                var maxPointsCount = Integer.parseInt(values.get(indexStudent)[i]);
                var task = new Task(title, type, maxPointsCount);
                tasks.add(task);
            }
        }
        return topics;
    }

    public static Student findStudentByName(List<Student> students, String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Имя не может быть пустым");

        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name))
                return student;
        }
        return null;
    }

    public static Map<String, List<double[]>> getStudentPracticePoints(List<Student> students) {
        Map<String, List<double[]>> studentPracticePointsMap = new HashMap<>();

        for (Student student : students) {
            if (student.getPointsCount() < 1000)
                continue;

            List<double[]> practicePointsList = new ArrayList<>();
            List<Topic> topics = student.getTopic();

            for (Topic topic : topics) {
                double[] taskPoints = topic.getTasks().stream()
                        .mapToDouble(task -> (double) task.getMaxPointsCount())
                        .toArray();

                if (DataProcessing.isUniform(taskPoints))
                    continue;

                practicePointsList.add(taskPoints);
            }

            if (!practicePointsList.isEmpty())
                studentPracticePointsMap.put(student.getName(), practicePointsList);
        }
        return studentPracticePointsMap;
    }

    public static void main(String[] args) {
        var file = readCSV("Data/basicprogramming.csv");
        var students = parseStudents(file);
        Map<String, List<double[]>> studentPracticePoints = getStudentPracticePoints(students);

        System.out.println(String.format("В общем: %s, подходит под критерии: %d", students.size(), studentPracticePoints.size()));

//        for (Map.Entry<String, List<double[]>> entry : studentPracticePoints.entrySet()) {
//            System.out.println("Student: " + entry.getKey());
//            System.out.println("Practice Points:");
//            for (double[] practicePoints : entry.getValue()) {
//                System.out.println("  " + Arrays.toString(practicePoints));
//            }
//        }
    }
}