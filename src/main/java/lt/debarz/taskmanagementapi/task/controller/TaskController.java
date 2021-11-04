package lt.debarz.taskmanagementapi.task.controller;

import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.task.dto.TaskDto;
import lt.debarz.taskmanagementapi.task.model.Status;
import lt.debarz.taskmanagementapi.task.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Get all tasks
     */
    @GetMapping
    public Set<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * Save task data to DB and
     */
    @PostMapping
    public ResponseEntity<TaskDto> saveTask(@RequestBody @Valid TaskDto taskDto) throws ParseException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.saveTask(taskDto));
    }

    /**
     * Get task data
     */
    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable long id) throws ParseException {
        return taskService.getTaskById(id);
    }
    /**
     * Delete task data from DB
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable long id){
        taskService.deleteTask(id);
    }
    /**
     * Update task data
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskDto updateTask(@RequestBody @Valid TaskDto taskDto) {
        return taskService.updateTask(taskDto);
    }
    /**
     * Update part of the task data
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskDto patchTask(@RequestBody @Valid TaskDto taskDto) throws ParseException {
        return taskService.patchTask(taskDto);
    }
    /**
     * Get tasks by user
     */
    @GetMapping("/user/tasks")
    public List<TaskDto> getTasksByUser(){
        return taskService.getTasksByUserId();
    }
    /**
     * Get sub-tasks by task id
     */
    @GetMapping("subTasks/{taskId}/{pageNumber}/{pageSize}")
    public List<TaskDto> getAllSubTasksByMainTaskId(@PathVariable long taskId, @PathVariable int pageNumber,
                                                    @PathVariable int pageSize){
        return taskService.findAllSubTasksById(taskId, pageNumber, pageSize);
    }

    /**
     * Get tasks by status
     */
    @GetMapping("/status/{status}")
    public List<TaskDto> getAllTasksByStatus(@PathVariable("status") Status status){
        return taskService.findAllTasksByStatus(status);
    }
    /**
     *  Get task total completion time  with all data
     * */
    @GetMapping("/totalTime/{id}")
    public TaskDto getTaskWithTimeSpentOnTask(@PathVariable long id){
        return taskService.getTimeSpentOnTask(id);
    }
}
