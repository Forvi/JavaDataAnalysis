package Services;

import Entities.StudentEntity;
import Entities.TaskEntity;
import Entities.ThemeEntity;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Parser {
    public static List<StudentEntity> studentParse(String path) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(path)).withCSVParser(new CSVParserBuilder()
                .withSeparator(';')
                .build()).build()) {
            List<String[]> rows = reader.readAll();
            List<StudentEntity> students = new ArrayList<>();

            for (int i = 3; i < rows.size(); i++) {
                String[] row = rows.get(i);
                String name = row[0].trim();
                String group = row[1].trim();
                StudentEntity studentEntity = new StudentEntity(name, group);
                students.add(studentEntity);
            }

            students.forEach(a -> System.out.println(a.toString()));
            return students;
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Ошибка при попытке прочитать файл", e);
        }
    }

    public static List<ThemeEntity> themeParse(String path) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(path))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
            List<String[]> rows = reader.readAll();
            List<ThemeEntity> themes = new ArrayList<>();

            // Проверка, что данные в файле существуют
            if (rows.size() < 2) {
                throw new RuntimeException("Недостаточно данных в файле");
            }

            // Строки, содержащие темы и задачи
            String[] themeHeaders = rows.get(0); // Строка с темами
            String[] taskHeaders = rows.get(1);  // Строка с задачами

            Map<String, Integer> pointMap = new HashMap<>();
            Map<String, String[]> taskMap = new HashMap<>();

            // получение оценок за задачи
            for (int i = 3; i < rows.size(); i++) {
                String[] row = rows.get(i);
                // 196 элементов
                for (int j = 2; j < row.length; j++) {
                    var points = row[j].trim();
                }
            }


            // Обрабатываем все темы и задачи в темах
            for (int i = 0; i < themeHeaders.length; i++) {
                String themeName = themeHeaders[i].trim();
                String[] taskDescription = Arrays.copyOfRange(taskHeaders, 3, taskHeaders.length);
//                System.out.println(Arrays.toString(rows.get(i)));
                taskMap.put(themeName, taskDescription);
            }

            for (Map.Entry<String, String[]> entry : taskMap.entrySet()) {
                System.out.println(entry.getKey() + "\t" + Arrays.toString(entry.getValue()));
            }

            return themes;
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Ошибка при попытке прочитать файл", e);
        }
    }

    public static void main(String[] args) {
        var a = themeParse("Data/basicprogramming.csv");
        a.forEach(System.out::println);
    }
}
