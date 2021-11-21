package lt.debarz.taskmanagementapi.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import lt.debarz.taskmanagementapi.task.entity.Task;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Set;

public class TaskModel extends RepresentationModel<TaskModel> {

    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final String taskGroup;
    @Getter
    private final String status;

    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date startTime;

    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date endTime;

    @Getter
    @JsonFormat(pattern = "HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private final Date taskCompletionTime;

    @Getter
    private final Set<Task> subTasks;

    public TaskModel(Task task){
        this.name = task.getName();
        this.description = task.getDescription();
        this.taskGroup = task.getTaskGroup();
        this.status = task.getStatus().name();
        this.startTime = task.getTimeSpent().getStartTime();
        this.endTime = task.getTimeSpent().getEndTime();
        this.taskCompletionTime = task.getTimeSpent().getTaskCompletionTime();
        this.subTasks = task.getSubTasks();
    }
}
