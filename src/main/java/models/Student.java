package models;

import java.util.ArrayList;

public class Student {
    private final String name;
    private final String group;
    private final int pointsCount;
    private final ArrayList<Topic> topicsForStudent;

    public Student(String name, String group, int pointsCount, ArrayList<Topic> topicsForStudent) {
        this.name = name;
        this.group = group;
        this.pointsCount = pointsCount;
        this.topicsForStudent = topicsForStudent;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public ArrayList<Topic> getTopic() {
        return topicsForStudent;
    }

    @Override
    public String toString() {
        var topicsBuilder = new StringBuilder();
        for (var topic : topicsForStudent) {
            topicsBuilder.append(topic.toString()).append("\n");
        }

        return String.format("""
                NAME: %s
                GROUP: %s
                POINTS: %d
                MODULES FOR STUDENT: %s
                """, name, group, pointsCount, topicsBuilder.toString().trim());
    }
}
