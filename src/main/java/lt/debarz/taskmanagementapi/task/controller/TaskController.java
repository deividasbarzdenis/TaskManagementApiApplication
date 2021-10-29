package lt.debarz.taskmanagementapi.task.controller;

import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.task.dto.TaskDto;
import lt.debarz.taskmanagementapi.task.model.Task;
import lt.debarz.taskmanagementapi.task.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Get all tasks
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    /**
     * Save task data to DB and
     */
    @PostMapping
    public ResponseEntity<TaskDto> addClientToQueue(@RequestBody @Valid TaskDto taskDto) throws ParseException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.saveTask(taskDto));
    }

}
