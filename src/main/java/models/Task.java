package models;

public class Task {
    private final String title;
    private final TasksTypes type;
    private final int maxPointsCount;

    public Task(String title, TasksTypes type, int maxPointsCount) {
        this.title = title;
        this.type = type;
        this.maxPointsCount = maxPointsCount;
    }

    public String getTitle() {
        return title;
    }

    public TasksTypes getType() {
        return type;
    }

    public int getMaxPointsCount() {
        return maxPointsCount;
    }

    @Override
    public String toString() {
        return String.format("TITLE: %s; TYPE: %s; MAX POINTS: %d", title, type, maxPointsCount);
    }
}

