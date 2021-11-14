package lt.debarz.taskmanagementapi.task.controller;

import lombok.AllArgsConstructor;
import lt.debarz.taskmanagementapi.task.dto.TaskDto;
import lt.debarz.taskmanagementapi.task.model.Status;
import lt.debarz.taskmanagementapi.task.service.TaskService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RepositoryRestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Get tasks by user
     */
    @GetMapping("/user/tasks")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<TaskDto> getTasksByUser(){
        return taskService.getTasksByUserId();
    }
    /**
     * Get sub-tasks by task id
     */
    @GetMapping("subTasks/{taskId}/{pageNumber}/{pageSize}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<TaskDto> getAllSubTasksByMainTaskId(@PathVariable long taskId, @PathVariable int pageNumber,
                                                    @PathVariable int pageSize){
        return taskService.findAllSubTasksById(taskId, pageNumber, pageSize);
    }

    /**
     * Get tasks by status
     */
    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<TaskDto> getAllTasksByStatus(@PathVariable("status") Status status){
        return taskService.findAllTasksByStatus(status);
    }
    /**
     *  Get task total completion time  with all data
     * */
    @GetMapping("/totalTime/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public TaskDto getTaskWithTimeSpentOnTask(@PathVariable long id){
        return taskService.getTimeSpentOnTask(id);
    }
}
