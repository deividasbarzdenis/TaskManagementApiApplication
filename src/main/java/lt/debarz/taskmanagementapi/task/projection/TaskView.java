package lt.debarz.taskmanagementapi.task.projection;

import lt.debarz.taskmanagementapi.task.entity.Task;
import lt.debarz.taskmanagementapi.task.entity.TimeSpent;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "taskView", types = Task.class)
public interface TaskView {
    Long getId();
    String getName();
    String getDescription();
    String getTaskGroup();
    String getStatus();
    TimeSpent getTimeSpent();
    Set<Task> getSubTasks();
}
