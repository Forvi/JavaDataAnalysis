package Entities;

import java.util.ArrayList;

public class TopicEntity {
    private final String title;
    private final ArrayList<TaskEntity> taskEntities;

    public TopicEntity(String title, ArrayList<TaskEntity> taskEntities) {
        this.title = title;
        this.taskEntities = taskEntities;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<TaskEntity> getTasks() {
        return taskEntities;
    }

    public int getPoints() {
        var sum = 0;
        for (var task : taskEntities) {
            sum += task.getMaxPointsCount();
        }
        return sum;
    }

    @Override
    public String toString() {
        var tasksBuilder = new StringBuilder();
        for (var task : taskEntities) {
            tasksBuilder.append("TASKS: ").append(task.toString()).append("\n");
        }
        return String.format("""
                TITLE: %s; POINTS_COUNT: %d; %s
                """, title, getPoints(), tasksBuilder.toString().trim());
    }
}
