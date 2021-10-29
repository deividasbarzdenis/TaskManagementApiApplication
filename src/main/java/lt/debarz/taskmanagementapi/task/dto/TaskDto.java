package lt.debarz.taskmanagementapi.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private List<TaskDto> subTasks  = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

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
