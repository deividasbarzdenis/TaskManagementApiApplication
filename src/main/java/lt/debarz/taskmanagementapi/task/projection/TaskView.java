package lt.debarz.taskmanagementapi.task.projection;

import lt.debarz.taskmanagementapi.task.model.Task;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "taskView", types = Task.class)
public interface TaskView {

    Long getId();
    String getName();
    String getDescription();
    String getTaskGroup();
    String getStatus();

    Set<Task> subTasks();

}
