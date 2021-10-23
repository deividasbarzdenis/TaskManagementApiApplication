package lt.debarz.taskmanagementapi.task.service;

import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.task.model.Task;
import lt.debarz.taskmanagementapi.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Get all tasks:
     */
    public List<Task> getAllClients() {
        return taskRepository
                .findAll()
                .stream()
                .collect(Collectors.toList());
    }
}
