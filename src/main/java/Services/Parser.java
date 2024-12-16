package Services;

import Entities.*;
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

    public static ArrayList<StudentEntity> parseStudents(List<String[]> values) {
        var students = new ArrayList<StudentEntity>();

        for (int i = 3; i < values.size(); i++) {
            var name = values.get(i)[0];
            var group = values.get(i)[1];
            var pointsCount = Integer.parseInt(values.get(i)[2])
                    + Integer.parseInt(values.get(i)[3])
                    + Integer.parseInt(values.get(i)[4]);
            var topicsForStudent = parseTopics(values, i);
            var student = new StudentEntity(name, group, pointsCount, topicsForStudent);

            students.add(student);
        }

        return students;
    }

    public static ArrayList<TopicEntity> parseTopics(List<String[]> values, int indexStudent) {
        var tasks = new ArrayList<TaskEntity>();
        var topics = new ArrayList<TopicEntity>();
        var len = values.get(0).length;
        var titleTopic = values.get(0)[7];

        for (int i = 7; i < len; i++) {
            var line = values.get(1)[i];

            if (!Objects.equals(values.get(0)[i], "")) {
                var topic = new TopicEntity(titleTopic, tasks);
                topics.add(topic);
                tasks = new ArrayList<>();
                titleTopic = values.getFirst()[i];
            }

            if (line.contains("Упр:")) {
                var title = line.replace("Упр: ", "");
                var type = TasksTypes.exercise;
                var maxPointsCount = Integer.parseInt(values.get(indexStudent)[i]);
                var task = new TaskEntity(title, type, maxPointsCount);
                tasks.add(task);
            } else if (line.contains("ДЗ:")) {
                var title = line.replace("ДЗ: ", "");
                var type = TasksTypes.practice;
                var maxPointsCount = Integer.parseInt(values.get(indexStudent)[i]);
                var task = new TaskEntity(title, type, maxPointsCount);
                tasks.add(task);
            }
        }
        return topics;
    }

    public static StudentEntity findStudentByName(List<StudentEntity> studentEntities, String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Имя не может быть пустым");

        for (StudentEntity studentEntity : studentEntities) {
            if (studentEntity.getName().equalsIgnoreCase(name)) {
                return studentEntity;
            }
        }
        return null;
    }

    public static String getPoints(List<StudentEntity> studentEntities, String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }

        for (StudentEntity studentEntity : studentEntities) {
            if (studentEntity.getName().equalsIgnoreCase(name)) {
                StringBuilder pointsInfo = getStringBuilder(studentEntity);

                return !pointsInfo.isEmpty() ? pointsInfo.toString() : "У студента нет задач.";
            }
        }

        return "Студент с именем " + name + " не найден.";
    }

    private static StringBuilder getStringBuilder(StudentEntity studentEntity) {
        List<TopicEntity> topicEntities = studentEntity.getTasks();
        StringBuilder pointsInfo = new StringBuilder();

        for (TopicEntity topicEntity : topicEntities) {
            List<TaskEntity> taskEntities = topicEntity.getTasks();
            for (TaskEntity taskEntity : taskEntities) {
                String taskName = taskEntity.getTitle();
                int taskPoints = taskEntity.getMaxPointsCount();
                pointsInfo.append(String.format("%s: %d%n", taskName, taskPoints));
            }
        }
        return pointsInfo;
    }

//    public static void main(String[] args) {
//        var file = readCSV("Data/basicprogramming.csv");
//        List<StudentEntity> studentEntities = parseStudents(file);
//        String studentNameToFind = "Григорьев Алексей";
//
//        try {
//            String points = getPoints(studentEntities, studentNameToFind);
//            System.out.println(points);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}





