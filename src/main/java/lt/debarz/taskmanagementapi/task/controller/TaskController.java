package lt.debarz.taskmanagementapi.task.controller;

import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.task.model.Task;
import lt.debarz.taskmanagementapi.task.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Get all clients in the queue
     */
    @GetMapping
    public List<Task> getAllClientsInQueue() {
        return taskService.getAllClients();
    }

}
