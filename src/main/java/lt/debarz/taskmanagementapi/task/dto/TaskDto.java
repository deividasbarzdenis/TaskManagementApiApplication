package lt.debarz.taskmanagementapi.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class TaskDto {

    private Long Id;
    private String name;
    private String description;
    private String taskGroup;
    private String status;


    private Set<TaskDto> subTasks  = new HashSet<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @JsonFormat(pattern = "HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date taskCompletionTime;

    public Set<TaskDto> getSubTasks() {
        return subTasks;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", taskGroup='" + taskGroup + '\'' +
                ", subTasks=" + subTasks +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
